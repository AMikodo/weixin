package com.star.project.weixin.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * view类型的菜单
 */
@Setter
@Getter
public class ViewButton extends Button {
	private String type;
	private String url;
}