package com.star.project.meeting.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.star.project.meeting.bean.Meetingpub;
import com.star.project.meeting.bean.User;
import com.star.project.meeting.bean.Weiuser;

public interface MeetingpubService {
	
	int insert(Meetingpub record);
	
	List<Meetingpub> selectMeetingpubList(Integer uid);
	
	String selectMaxPcodeByTime(String time);
	
	 Meetingpub selectByPrimaryKey(String pid);
	 
	 List<Meetingpub> selectListByZoneAndStatus(Integer uid,String tname);
	 
	 List<Meetingpub> selectMeetingpubListBySelective(Meetingpub meetingpub);
	 
	 int deleteByPrimaryKey(String pid);
	 
	 int deletebatch(String[] ids);
	 
	 int updateByPrimaryKeySelective(Meetingpub meetingpub);
	 
	 int updateStatusByPid(Integer status,String pid);
	 
	/**
	 * 每日日报
	 */
	 
	String logInfo();
	
	List<Weiuser> selectMeetingpubForRank();
}
