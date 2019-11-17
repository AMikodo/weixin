package com.star.project.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.project.weixin.pojo.AccessToken;
import com.star.project.weixin.pojo.Button;
import com.star.project.weixin.pojo.CommonButton;
import com.star.project.weixin.pojo.ComplexButton;
import com.star.project.weixin.pojo.Menu;
import com.star.project.weixin.pojo.ViewButton;
import com.star.project.weixin.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
/***
 * 自定义菜单的创建步骤
	1、找到AppId和AppSecret。自定义菜单申请成功后，在“高级功能”-“开发模式”-“接口配置信息”的最后两项就是；
	2、根据AppId和AppSecret，以https get方式获取访问特殊接口所必须的凭证access_token；
	3、根据access_token，将json格式的菜单数据通过https post方式提交。

 */
	
	public final static String REAL_URL="http://bilibilii.natapp1.cc/nj-1901-pro-stu/"; //个人花生壳
	//public final static String REAL_URL = "http://wxmobsa.yidatec.com/weixin/"; //正式号服务器	
	
	public final static String appId="wx7554d0199ec9e4f4";
	public final static String appSecret = "25b6e0ab62f0f5cf917b24f136e2a689";
	
	public static void resultMenu(){
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}
	
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = MenuManager.appId;
		// 第三方用户唯一凭证密钥
		String appSecret = MenuManager.appSecret;
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(),at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {

		
		CommonButton btn10=new CommonButton();
		btn10.setName("会议抢单");
		btn10.setType("click");
		btn10.setKey("10");
		
		
		ViewButton btn11 = new ViewButton();
		btn11.setName("会议发单");
		btn11.setType("view");
		btn11.setUrl(REAL_URL+"wx/MeetingpubOauth");
		
		
//-------------------------------------------------------
		CommonButton btn20 = new CommonButton();
		btn20.setName("每日签到");
		btn20.setType("click");
		btn20.setKey("20");
		
		ViewButton btn21=new ViewButton();
		btn21.setName("发单排行榜");
		btn21.setType("view");
		btn21.setUrl(REAL_URL+"meetingpub/rank");
		
		ViewButton btn22=new ViewButton();
		btn22.setName("抢单排行榜");
		btn22.setType("view");
		btn22.setUrl(REAL_URL+"meetinggrab/rank");
		
		
//------------------------------------------------------------		
		/*CommonButton btn31 = new CommonButton(); //返回图文消息
		btn31.setName("项目简介");
		btn31.setType("click");
		btn31.setKey("70");
		*/
		CommonButton btn30=new CommonButton();
		btn30.setName("联系我们");
		btn30.setType("click");
		btn30.setKey("30");
		
		CommonButton btn31=new CommonButton();
		btn31.setName("版本信息");
		btn31.setType("click");
		btn31.setKey("31");
		
		ViewButton btn32=new ViewButton();
		btn32.setName("个人中心");
		btn32.setType("view");
		//btn32.setUrl("https://2heng.xin/");
		btn32.setUrl(REAL_URL+"wx/oauth");
		
		CommonButton btn33=new CommonButton();
		btn33.setName("每日一言");
		btn33.setType("click");
		btn33.setKey("33");
		
		//###############################################一级子菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("会议");
		mainBtn1.setSub_button(new Button[] { btn10,btn11});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("公告板");  // 
		mainBtn2.setSub_button(new Button[] { btn20,btn21,btn22});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("系统消息");// btn31, btn32, btn33,
		mainBtn3.setSub_button(new Button[] {btn30,btn31,btn32,btn33});

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2,mainBtn3 });

		return menu;
	}
}
