package com.star.project.weixin.bean.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 音频消息
 */
@Setter
@Getter
public class VoiceMessage extends BaseMessage {
	// 媒体ID
	private String MediaId;
	// 语音格式
	private String Format;

	
}
