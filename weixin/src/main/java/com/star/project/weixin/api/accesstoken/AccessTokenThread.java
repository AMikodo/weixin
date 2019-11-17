package com.star.project.weixin.api.accesstoken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.project.weixin.main.MenuManager;
import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

public class AccessTokenThread extends Thread{
	private static final Logger log=LoggerFactory.getLogger(AccessTokenThread.class);
	private static final  String access_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static  String accesstoken;
	@Override
	public void run() {
		while(true) {
			accesstoken=getAccessToken();
			log.info("获取到accesstoken"+accesstoken);
			try {
				Thread.sleep(6000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private static String getAccessToken() {
		String requestUrl =access_token_url.replace("APPID", MenuManager.appId).replace("APPSECRET", MenuManager.appSecret);
		
		JSONObject jsonObject=WeixinUtil.httpRequest(requestUrl, "GET", null);
		if(jsonObject.get("access_token")!=null) {
			
			log.info("成功获得access_token");
			System.out.println(jsonObject.getString("access_token"));
			return jsonObject.getString("access_token");
		}else {
			System.out.println("获取失败");
			log.error("获取access_token失败");
		}
		return null;
	}
}
