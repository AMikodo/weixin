package com.star.project.meeting.service;

import java.util.List;

import com.star.project.meeting.bean.Meetingtype;

public interface MeetingtypeService {
	
	List<Meetingtype> selectByStatus();
}
