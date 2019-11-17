package com.star.project.meeting.web.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.star.project.weixin.main.MenuManager;
import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;
@RequestMapping("weixin")
@Controller
public class WeixinOauth {
	
	@RequestMapping("oauth")
	public void oauth(HttpServletResponse resp) throws IOException {
		String url=MenuManager.REAL_URL+"weixin/invoke";
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
		System.out.println("code为"+code+"state为"+state);
		String path="https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid="+MenuManager.appId
				+ "&secret="+MenuManager.appSecret
				+ "&code="+code
				+ "&grant_type=authorization_code";
		
		
		JSONObject jsonObject=WeixinUtil.httpRequest(path, "POST", null);
		System.out.println("accessToken"+jsonObject);
		String access_token=jsonObject.getString("access_token");
		String openid=jsonObject.getString("openid");
		String url="https://api.weixin.qq.com/sns/userinfo?"
				+ "access_token="+access_token
				+ "&openid="+openid
				+ "&lang=zh_CN";
		JSONObject userInfo=WeixinUtil.httpRequest(url, "POST", null);
		req.setAttribute("userInfo", userInfo);
		return "/pages/oauth/oauth.jsp";
	}
}
