package com.star.project.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.SigninDate;
import com.star.project.meeting.mapper.SigninDateMapper;
import com.star.project.meeting.service.SigninDateService;

@Service
public class SigninDateServiceImpl implements SigninDateService {
	@Autowired
	private SigninDateMapper signinDateMapper;
	
	@Override
	public int insert(SigninDate signinDate) {
		
		return signinDateMapper.insert(signinDate);
	}

	@Override
	public List<SigninDate> selectListByUid(Integer uid, String time) {
		
		return signinDateMapper.selectListByUid(uid, time);
	}

}
