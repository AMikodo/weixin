package com.star.project.weixin.bean.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 文本消息
 */
@Setter
@Getter
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	
}