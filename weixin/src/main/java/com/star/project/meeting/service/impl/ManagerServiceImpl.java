package com.star.project.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.Manager;
import com.star.project.meeting.mapper.ManagerMapper;
import com.star.project.meeting.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	private ManagerMapper managerMapper;
	@Override
	public Manager selectByUnameAndUpass(String uname, String upass) {
		
		return managerMapper.selectByUnameAndUpass(uname, upass) ;
	}
	@Override
	public int updateByPrimaryKeySelective(Manager manager) {
		// TODO Auto-generated method stub
		return managerMapper.updateByPrimaryKeySelective(manager);
	}
	
}
