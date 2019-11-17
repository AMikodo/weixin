package com.star.project.weixin.bean.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * 文本消息
 */
@Getter
@Setter
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

}
