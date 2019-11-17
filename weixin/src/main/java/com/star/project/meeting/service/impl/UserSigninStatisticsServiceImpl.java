package com.star.project.meeting.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.star.project.meeting.bean.SigninDate;
import com.star.project.meeting.bean.UserSigninStatistics;
import com.star.project.meeting.exception.MyException;
import com.star.project.meeting.mapper.UserSigninStatisticsMapper;
import com.star.project.meeting.service.SigninDateService;
import com.star.project.meeting.service.UserSigninStatisticsService;
@Service
public class UserSigninStatisticsServiceImpl implements UserSigninStatisticsService{
	@Autowired
	private UserSigninStatisticsMapper userSigninStatisticsMapper;
	@Autowired
	private  SigninDateService signinDateService;
	
	public  int insertSigninDate(UserSigninStatistics signinStatistics) {
		SigninDate signinDate=new SigninDate();
		signinDate.setId(UUID.randomUUID().toString());
		signinDate.setUid(signinStatistics.getUid());
		signinDate.setSignindate(signinStatistics.getLastsignindate());
		return signinDateService.insert(signinDate);
	}
	
	
	@Override
	@Transactional
	public int insert(UserSigninStatistics signinStatistics) {
		int num=insertSigninDate(signinStatistics);
		if(num<0) {
			throw new MyException("插入异常");
		}
		int num1=userSigninStatisticsMapper.insert(signinStatistics);
		if(num1<0) {
			throw new MyException("插入异常");
		}
		return num1;
	}

	@Override
	public UserSigninStatistics selectByPrimaryUid(Integer uid) {
		
		return userSigninStatisticsMapper.selectByPrimaryUid(uid);
	}

	@Override
	@Transactional
	public int updateByPrimaryKeySelective(UserSigninStatistics signinStatistics) {
		int num=insertSigninDate(signinStatistics);
		if(num<0) {
			throw new MyException("更新异常");
		}
		int num1=userSigninStatisticsMapper.updateByPrimaryKeySelective(signinStatistics);
		if(num1<0) {
			throw new MyException("更新异常");
		}
		return num1;
	}

	@Override
	public int dateDifferent(Integer uid) {
		
		return userSigninStatisticsMapper.dateDifferent(uid);
	}


	@Override
	public int countUserSigninStatistics() {
		
		return userSigninStatisticsMapper.countUserSigninStatistics();
	}


	@Override
	public int countBeyondUser(Integer uid) {
		
		return userSigninStatisticsMapper.countBeyondUser(uid);
	}

}
