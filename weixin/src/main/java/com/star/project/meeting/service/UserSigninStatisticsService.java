package com.star.project.meeting.service;

import com.star.project.meeting.bean.UserSigninStatistics;

public interface UserSigninStatisticsService {
	
	int insert(UserSigninStatistics signinStatistics);
	
	UserSigninStatistics selectByPrimaryUid(Integer uid);
	
	int updateByPrimaryKeySelective(UserSigninStatistics  signinStatistics);

	int dateDifferent(Integer uid);
	 
	int countUserSigninStatistics();
	
	int countBeyondUser(Integer uid);
}
