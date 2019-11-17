package com.star.project.weixin.bean.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * 音乐消息
 */
@Getter
@Setter
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;


}
