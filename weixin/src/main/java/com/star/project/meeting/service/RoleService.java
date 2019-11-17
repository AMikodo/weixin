package com.star.project.meeting.service;

import java.util.List;

import com.star.project.meeting.bean.Role;

public interface RoleService {
	
	 List<Role> selectList(Role role);
	 
	 int deleteByPrimaryKey(Integer rid);
	 
	 int deleteBatch(int[] ids);
	 
	 Role selectByPrimaryKey(Integer rid);
	 
	 int updateByPrimaryKey(Role role);
	 
	 int updateStatusByPrimarykey(Integer status,Integer rid);
	 
	 int insert(Role role);
}
