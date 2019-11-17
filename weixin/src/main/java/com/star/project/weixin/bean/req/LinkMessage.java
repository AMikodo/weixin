package com.star.project.weixin.bean.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 链接消息
 */
@Setter
@Getter
public class LinkMessage extends BaseMessage {
	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;


}
