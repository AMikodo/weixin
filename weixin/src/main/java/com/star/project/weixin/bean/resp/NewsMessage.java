package com.star.project.weixin.bean.resp;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * 文本消息
 */
@Getter
@Setter
public class NewsMessage extends BaseMessage {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;


}