package com.star.project.weixin.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 复杂按钮（父按钮）
 * 
 */
@Setter
@Getter
public class ComplexButton extends Button {
	private Button[] sub_button;

	
}