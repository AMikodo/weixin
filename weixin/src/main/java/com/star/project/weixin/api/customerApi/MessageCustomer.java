package com.star.project.weixin.api.customerApi;

import org.springframework.stereotype.Service;

import com.star.project.weixin.api.accesstoken.AccessTokenThread;
import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;
@Service
public class MessageCustomer {
	
	private static final String message_url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	
	public void sendMessage(JSONObject jsonObject) {
		String request=message_url.replace("ACCESS_TOKEN", AccessTokenThread.accesstoken);
		
		WeixinUtil.httpRequest(request, "POST", jsonObject.toString());
	
	}
}
