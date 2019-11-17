package com.star.project.weixin.api.tuling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class TulingUtil {
	
	private static int count=0;
	
	public String getMes(String reqMes) {
		String requestUrl="http://openapi.tuling123.com/openapi/api/v2";
		TulingBean tulingBean=new TulingBean();
		tulingBean.setReqType(0);
		
		Perception perception=new Perception();
		
		InputText inputText=new InputText();
		
		inputText.setText(reqMes);
		perception.setInputText(inputText);
		tulingBean.setPerception(perception);
		UserInfo userInfo=new UserInfo();
		List<String> list=new ArrayList<String>();
		list.add("1896a791efbd4c338f36aa8c4636fbbc");
		list.add("768493348f674b59bd33532af96163f0");
		list.add("bfe05586db914cc196017efbd927be28");
		list.add("eb6cdf1f7a974bc7bdd6b35c39e60c7a");
		list.add("a285d643902a4a00a546ded3495af4bf");
		String apiKey=null;
		if(count==0) {
			apiKey=list.get(count);
			count++;
		}else if(count==1){
			apiKey=list.get(count);
			count++;
		}else if(count==2){
			apiKey=list.get(count);
			count++;
		}else if(count==3) {
			apiKey=list.get(count);
			count++;
		}else if(count==4) {
			apiKey=list.get(count);
			count=0;
		}
		userInfo.setApiKey(apiKey);
		String userId="mikoduo";
		userInfo.setUserId(userId);
		tulingBean.setUserInfo(userInfo);
		JSONObject j=JSONObject.fromObject(tulingBean);
		JSONObject resp=WeixinUtil.httpRequest(requestUrl, "POST", j.toString());
		
		//解析返回的json串
		JSONArray array=(JSONArray) resp.get("results");
		JSONObject jo1=(JSONObject) array.get(0);
		JSONObject jo2=(JSONObject) jo1.get("values");
		String resptext=jo2.getString("text");
		return resptext;
		
	}

}
