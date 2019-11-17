package com.star.project.weixin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.service.WeiuserInfoService;
import com.star.project.weixin.api.accesstoken.AccessTokenThread;
import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;
@Service
public class WeiuserService {
	@Autowired
	private WeiuserInfoService  weiuserInfoService;
	
	private static final Logger log=LoggerFactory.getLogger(WeiuserService.class);
	private static final String get_userinfo_url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public void getWeiuserInfoService(String openid) {
		Weiuser weiuser=this.selectWeiuser(openid);
		
		if(weiuser==null) {
			JSONObject jsonObject=this.getWeiuserInfo(openid);
			Weiuser w=this.Transformation(jsonObject);
			int num=this.insertWeiuserInfo(w);
			System.out.println(num);
		}
	}
	
	
	public JSONObject getWeiuserInfo(String openid) {
		String requestUrl=get_userinfo_url.replace("ACCESS_TOKEN", AccessTokenThread.accesstoken).replace("OPENID", openid);
		JSONObject jsonObject=  WeixinUtil.httpRequest(requestUrl, "GET", null);
		
		return jsonObject;
	}
	
	public Weiuser Transformation(JSONObject jsonObject) {
		ObjectMapper mapper=new ObjectMapper();
		Weiuser weiuser=null;
		try {
			weiuser=mapper.readValue(jsonObject.toString(),Weiuser.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} 
		return weiuser;
	}
	
	public int insertWeiuserInfo(Weiuser weiuser) {
		
		return weiuserInfoService.insert(weiuser);
	}
	
	public Weiuser selectWeiuser(String openid) {
		return weiuserInfoService.selectByPrimaryKey(openid);
	}
}
