package com.star.project.meeting.service;

import java.util.List;

import com.star.project.meeting.bean.SigninDate;

public interface SigninDateService {
	
	
	int insert(SigninDate signinDate);
	
	List<SigninDate> selectListByUid(Integer uid,String time);
}
