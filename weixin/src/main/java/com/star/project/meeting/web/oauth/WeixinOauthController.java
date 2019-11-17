package com.star.project.meeting.web.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.star.project.meeting.bean.User;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.service.UserService;
import com.star.project.meeting.service.WeiuserInfoService;
import com.star.project.weixin.main.MenuManager;
import com.star.project.weixin.service.WeiuserService;
import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("wx")
public class WeixinOauthController {
	@Autowired
	private UserService userService;
	@Autowired
	private WeiuserInfoService  weiuserInfoService;
	@Autowired
	private WeiuserService weiuserService;
	
	@RequestMapping("oauth")
	public void oauth(HttpServletResponse resp) throws IOException {
		String url=MenuManager.REAL_URL+"wx/invoke";
		String path=null;
		try {
			path=URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
		String codeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+MenuManager.appId
				+ "&redirect_uri="+path
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"
				+ "&state=ys"
				+ "#wechat_redirect";
		
		resp.sendRedirect(codeUrl);
	}
	@RequestMapping("invoke")
	public String topage(HttpServletRequest req) {
		String code=req.getParameter("code");
		String state=req.getParameter("state");
		System.out.println("code为"+code+"\t"+"state为"+state);
		String path="https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid="+MenuManager.appId
				+ "&secret="+MenuManager.appSecret
				+ "&code="+code
				+ "&grant_type=authorization_code";

		
		JSONObject jsonObject=WeixinUtil.httpRequest(path, "POST", null);
		System.out.println("accessToken"+jsonObject);
		String access_token=jsonObject.getString("access_token");
		String openid=jsonObject.getString("openid");
		//确认用户是否存在weiuser表中
		Weiuser weiuser=weiuserInfoService.selectByPrimaryKey(openid);
		//不存在获取用户信息插入weiuser中
		if(weiuser==null) {
			String url="https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+access_token
					+ "&openid="+openid
					+ "&lang=zh_CN";
			JSONObject userInfo=WeixinUtil.httpRequest(url, "POST", null);
			weiuser=weiuserService.Transformation(userInfo);
			weiuserInfoService.insert(weiuser);
		}
		//检查用户是否绑定微信
		User user=userService.selectByWid(weiuser.getWid());
		if(user==null) {
			return"/pages/weixin/login.jsp?wid="+weiuser.getWid();
		}else {
			req.setAttribute("user", user);
			return"/pages/weixin/userInfo.jsp";
		}
		
	}
	@RequestMapping("MeetingpubOauth")
	public void MeetingpubOauth(HttpServletResponse resp) throws IOException {
		String url=MenuManager.REAL_URL+"wx/MeetingpubInvoke";
		String path=null;
		try {
			path=URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
		String codeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+MenuManager.appId
				+ "&redirect_uri="+path
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"
				+ "&state=ys"
				+ "#wechat_redirect";
		
		resp.sendRedirect(codeUrl);
	}
	@RequestMapping("MeetingpubInvoke")
	public String MeetingpubInvoke(HttpServletRequest req) {
		String code=req.getParameter("code");
		String state=req.getParameter("state");
		System.out.println("code为"+code+"\t"+"state为"+state);
		String path="https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid="+MenuManager.appId
				+ "&secret="+MenuManager.appSecret
				+ "&code="+code
				+ "&grant_type=authorization_code";

		
		JSONObject jsonObject=WeixinUtil.httpRequest(path, "POST", null);
		System.out.println("accessToken"+jsonObject);
		String access_token=jsonObject.getString("access_token");
		String openid=jsonObject.getString("openid");
		//确认用户是否存在weiuser表中
		Weiuser weiuser=weiuserInfoService.selectByPrimaryKey(openid);
		//不存在获取用户信息插入weiuser中
		if(weiuser==null) {
			String url="https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+access_token
					+ "&openid="+openid
					+ "&lang=zh_CN";
			JSONObject userInfo=WeixinUtil.httpRequest(url, "POST", null);
			weiuser=weiuserService.Transformation(userInfo);
			weiuserInfoService.insert(weiuser);
		}
		//检查用户是否绑定微信
		User user=userService.selectByWid(weiuser.getWid());
		if(user==null) {
			return"/pages/weixin/login.jsp?wid="+weiuser.getWid();
		}else {
			if(user.getRid()!=2) {
				return"/pages/weixin/Jurisdiction.jsp";
			}
			return"/pages/weixin/meetingpub/meetingpub.jsp?uid="+user.getUid();
		}
	}
}
