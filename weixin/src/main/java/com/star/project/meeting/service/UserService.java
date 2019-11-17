package com.star.project.meeting.service;

import java.util.List;

import com.star.project.meeting.bean.User;

public interface UserService {
	
	  int deleteByPrimaryKey(Integer uid);

	    int insert(User user);

	    int insertSelective(User user);

	    User selectByPrimaryKey(Integer uid);

	    int updateByPrimaryKeySelective(User user);

	    int updateByPrimaryKey(User user);
	    
	    User selectByWid(Integer wid);
	    
	    User selectByEmail(String email);
	    
	    int updateWidByEmail(String email,Integer wid);
	    
	    List<User> selectUserList(User user);
	    
	    int deleteBatch(int[] ids);
	    
	    int updateStatusByUid(Integer status,Integer uid);
}
