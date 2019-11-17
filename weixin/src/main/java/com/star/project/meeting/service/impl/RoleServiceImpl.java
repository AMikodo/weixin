package com.star.project.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.Role;
import com.star.project.meeting.mapper.RoleMapper;
import com.star.project.meeting.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> selectList(Role role) {
		
		return roleMapper.selectList(role);
	}
	@Override
	public int deleteByPrimaryKey(Integer rid) {
		
		return roleMapper.deleteByPrimaryKey(rid);
	}
	@Override
	public int deleteBatch(int[] ids) {
		
		return roleMapper.deleteBatch(ids);
	}
	@Override
	public Role selectByPrimaryKey(Integer rid) {
		
		return roleMapper.selectByPrimaryKey(rid);
	}
	@Override
	public int updateByPrimaryKey(Role role) {
		
		return roleMapper.updateByPrimaryKey(role);
	}
	@Override
	public int updateStatusByPrimarykey(Integer status, Integer rid) {
		
		return roleMapper.updateStatusByPrimarykey(status, rid);
	}
	@Override
	public int insert(Role role) {
		
		return roleMapper.insert(role);
	}

}
