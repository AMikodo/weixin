package com.star.project.weixin.api.customerApi;

import lombok.Data;

@Data
public class Message {
	
	private String touser;
	
	private String msgtype;
	
	private MessageText text;
	
}
