package com.star.project.weixin.api.yiyan;

import org.springframework.stereotype.Service;

import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;
@Service
public class YiyanUtil {
	public String getMsg() {
		String requestUrl="https://v1.hitokoto.cn/";
		JSONObject jsonObject=WeixinUtil.httpRequest(requestUrl, "GET", null);
		String hitokoto=jsonObject.getString("hitokoto");
		String from=jsonObject.getString("from");
		String resptext=hitokoto+"\t"+"--"+from;
		return resptext;
	}
}
