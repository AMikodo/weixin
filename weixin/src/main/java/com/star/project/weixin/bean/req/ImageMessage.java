package com.star.project.weixin.bean.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 图片消息
 */
@Setter
@Getter
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	
}
