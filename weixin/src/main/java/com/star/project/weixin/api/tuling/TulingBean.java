package com.star.project.weixin.api.tuling;

import lombok.Data;

@Data
public class TulingBean {
	
	private Integer reqType=0;
	
	private Perception perception;
	
	private UserInfo userInfo;
}
