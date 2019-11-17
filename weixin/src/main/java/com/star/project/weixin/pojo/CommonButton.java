package com.star.project.weixin.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 普通按钮（子按钮）
 */
@Setter
@Getter
public class CommonButton extends Button {
	private String type;
	private String key;

	
}
