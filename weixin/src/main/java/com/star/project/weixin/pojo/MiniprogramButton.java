package com.star.project.weixin.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MiniprogramButton extends Button{
	
	private String type;
	private String url;
	private String appid;
	private String pagepath;
}
