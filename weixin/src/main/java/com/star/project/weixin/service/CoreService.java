package com.star.project.weixin.service;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.weixin.main.MenuManager;
import com.star.project.weixin.pojo.AccessToken;
import com.star.project.weixin.util.MessageUtil;
import com.star.project.weixin.util.WeixinUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;

import com.star.project.meeting.bean.User;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.service.UserService;
import com.star.project.meeting.service.WeiuserInfoService;
import com.star.project.weixin.api.tuling.TulingUtil;
import com.star.project.weixin.api.yiyan.YiyanUtil;
import com.star.project.weixin.bean.resp.Article;
import com.star.project.weixin.bean.resp.MusicMessage;
import com.star.project.weixin.bean.resp.NewsMessage;
import com.star.project.weixin.bean.resp.TextMessage;

@Service
public class CoreService {
	@Autowired
	private TulingUtil tulingUtil;
	@Autowired
	private YiyanUtil yiyanUtil;
	@Autowired
	private WeiuserService weiuserSerivice;
	@Autowired
	private WeiuserInfoService weiUserInfoService;
	@Autowired
	private UserService userService;
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息 组装要返回的文本消息对象；
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			// textMessage.setContent("欢迎访问<a
			// href=\"http://www.baidu.com/index.php?tn=site888_pg\">百度</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			/**
			 * 演示了如何接收微信发送的各类型的消息，根据MsgType判断属于哪种类型的消息；
			 */

			// 接收用户发送的文本消息内容
			String content = requestMap.get("Content");
			

			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				System.out.println("以下是客户发送的文本消息");
				System.out.println(content);
				if("王志强".equals(content)) {
					respContent="沙雕王志强";
				}else if ("方明星".equals(content)){
					respContent="来自方明星的鄙视→_→";
				}else if(content.contains("你是")||content.contains("你叫")){
					respContent="我是樱花庄的布偶酱,  喵~";
				}else {
					//一言
					//respContent =yiyanUtil.getMsg();
					//图灵机器人
					respContent = tulingUtil.getMes(content);
					
					System.out.println(respContent);
					
				}
				
				
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			//视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					weiuserSerivice.getWeiuserInfoService(fromUserName);
					respContent="欢迎关注";
					//respContent = weiuserSerivice.selectWeiuser(fromUserName).toString();
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}else if(eventType.equals(MessageUtil.EVENT_TYPE__LOCATION)) {
					System.out.println("以下用户发送地理信息:"+fromUserName);
					System.out.println("纬度"+requestMap.get("Latitude"));
					System.out.println("经度"+requestMap.get("Longitude"));
					System.out.println("精度"+requestMap.get("Precision"));
					respContent="";
				}
				
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("33")) {
						respContent = yiyanUtil.getMsg();

					}else if(eventKey.equals("10")) {
						//首先查看用户有没有绑定
						Weiuser weiuser=weiuserSerivice.selectWeiuser(fromUserName);
						User user=userService.selectByWid(weiuser.getWid());
						
						List<Article> articleList = new ArrayList<Article>();
						Article article = new Article();
						if(user==null) {
							article.setTitle("您还未绑定,请前往登录界面进行绑定...");
							article.setDescription("绑定流程如下: 前往登录界面,输入邮箱即可完成绑定");
							article.setPicUrl(
									"http://pic215.nipic.com/file/20190512/9355665_205619936088_2.jpg");
							article.setUrl(MenuManager.REAL_URL+"pages/weixin/login.jsp?wid="+weiuser.getWid());
						}else if(user.getRid()!=1) {
							article.setTitle("您不拥有抢单权限");
							article.setDescription("您不属于抢单组");
							article.setPicUrl(
									"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561643896638&di=d223948ff006db3bbce9376d12796454&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic3%2Fcover%2F03%2F06%2F56%2F5b3457b1a66d5_610.jpg");
							article.setUrl(MenuManager.REAL_URL+"pages/weixin/Jurisdiction.jsp");
						}else {
							article.setTitle(weiuser.getNickname()+" 欢迎您,尊敬的抢单用户");
							article.setDescription("点击查看今天可抢发单");
							article.setPicUrl(
									"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561644522525&di=f642d80defaacab0c2b09b84df4987b8&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01b7045aa0eabfa801206d96571ae5.jpg%402o.jpg");
							article.setUrl(MenuManager.REAL_URL+"pages/weixin/meetinggrab/meetinggrab.jsp?uid="+user.getUid());
						}
						articleList.add(article);						
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}else if(eventKey.equals("20")) {
						Weiuser weiuser=weiuserSerivice.selectWeiuser(fromUserName);
						User user=userService.selectByWid(weiuser.getWid());
						List<Article> articleList = new ArrayList<Article>();
						Article article = new Article();
						if(user==null) {
							article.setTitle("您还未绑定,请前往登录界面进行绑定...");
							article.setDescription("绑定流程如下: 前往登录界面,输入邮箱即可完成绑定");
							article.setPicUrl(
									"http://pic215.nipic.com/file/20190512/9355665_205619936088_2.jpg");
							article.setUrl(MenuManager.REAL_URL+"pages/weixin/login.jsp?wid="+weiuser.getWid());
						}else {
							article.setTitle("请点击签到");
							article.setDescription("点击即可完成签到");
							article.setPicUrl(
									"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561984641929&di=6682b986a09a58c03df68f099966ffb4&imgtype=0&src=http%3A%2F%2Fku.90sjimg.com%2Felement_origin_min_pic%2F01%2F34%2F64%2F47573bba9a22716.jpg");
							article.setUrl(MenuManager.REAL_URL+"pages/weixin/signin/signin.jsp?uid="+user.getUid());
						}
						articleList.add(article);						
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}else if(eventKey.equals("30")) {
						respContent="如有需要请联系我们，邮箱：1500152337@qq.com";
					}else if(eventKey.equals("31")) {
						respContent="当前版本为测试版α";
					}
					else if (eventKey.equals("70")) {

						List<Article> articleList = new ArrayList<Article>();
						
						// 单图文消息
						Article article = new Article();
						article.setTitle("布偶猫");
						article.setDescription("这是个吸猫平台");
						article.setPicUrl(
								"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560937390127&di=2d9872d2461ad9c1fd925f0477f575be&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201706%2F29%2F20170629190714_VtZP5.thumb.700_0.jpeg");
						article.setUrl("https://hitokoto.cn/");

						
						articleList.add(article);						
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}
					
				}

			}

			// 组装要返回的文本消息对象；
			textMessage.setContent(respContent);
			// 调用消息工具类MessageUtil将要返回的文本消息对象TextMessage转化成xml格式的字符串；
			respMessage = MessageUtil.textMessageToXml(textMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
