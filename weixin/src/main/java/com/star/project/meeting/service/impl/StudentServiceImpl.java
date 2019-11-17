package com.star.project.meeting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.Student;
import com.star.project.meeting.mapper.StudentMapper;
import com.star.project.meeting.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentMapper studentMapper;
	@Override
	public Student selectByPrimaryKey(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

}
