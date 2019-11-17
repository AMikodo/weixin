package com.star.project.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.User;
import com.star.project.meeting.mapper.UserMapper;
import com.star.project.meeting.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer uid) {
		
		return userMapper.deleteByPrimaryKey(uid);
	}

	@Override
	public int insert(User user) {
		
		return userMapper.insert(user);
	}

	@Override
	public int insertSelective(User user) {
		
		return userMapper.insertSelective(user);
	}

	@Override
	public User selectByPrimaryKey(Integer uid) {
		
		return userMapper.selectByPrimaryKey(uid);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {
		
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateByPrimaryKey(User user) {
		
		return userMapper.updateByPrimaryKey(user);
	}

	@Override
	public User selectByWid(Integer wid) {
		
		return userMapper.selectByWid(wid);
	}

	@Override
	public User selectByEmail(String email) {
		
		return userMapper.selectByEmail(email);
	}

	@Override
	public int updateWidByEmail(String email, Integer wid) {
		
		return userMapper.updateWidByEmail(email, wid);
	}

	@Override
	public List<User> selectUserList(User user) {
		
		return userMapper.selectUserList(user);
	}

	@Override
	public int deleteBatch(int[] ids) {
		
		return userMapper.deleteBatch(ids);
	}

	@Override
	public int updateStatusByUid(Integer status, Integer uid) {
		
		return userMapper.updateStatusByUid(status, uid);
	}

	

	

}
