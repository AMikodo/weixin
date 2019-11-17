package test;
import com.star.project.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

public class apitest {
	public static void main(String[] args) {
		String url = "https://v1.hitokoto.cn/?c=c";
		JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
		System.out.println(json);
	}
	
}
