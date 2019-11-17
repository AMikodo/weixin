package com.star.project.meeting.service;


import com.star.project.meeting.bean.Manager;

public interface ManagerService {
	
	
	Manager selectByUnameAndUpass(String uname,String upass);
	
	 int updateByPrimaryKeySelective(Manager manager);
}
