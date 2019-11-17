package com.star.project.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.Meetingtype;
import com.star.project.meeting.mapper.MeetingtypeMapper;
import com.star.project.meeting.service.MeetingtypeService;
@Service
public class MeetingtypeServiceImpl implements MeetingtypeService{
	@Autowired
	private MeetingtypeMapper meetingtypeMapper;
	@Override
	public List<Meetingtype> selectByStatus() {
		// TODO Auto-generated method stub
		return meetingtypeMapper.selectByStatus();
	}

}
