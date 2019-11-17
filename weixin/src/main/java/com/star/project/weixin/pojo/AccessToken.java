package com.star.project.weixin.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信通用接口凭证
 */
@Getter
@Setter
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;

}