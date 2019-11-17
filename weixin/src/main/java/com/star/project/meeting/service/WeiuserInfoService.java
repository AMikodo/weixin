package com.star.project.meeting.service;

import java.util.List;

import com.star.project.meeting.bean.WeiUserOA;
import com.star.project.meeting.bean.Weiuser;

public interface WeiuserInfoService {
	
	 int insert(Weiuser record);
	
	 Weiuser selectByPrimaryKey(String openid);
	 
	 List<Weiuser> selectWeiuserToSendMessage();
	 
	 List<WeiUserOA> selectWeiuserRank();
	 
}
