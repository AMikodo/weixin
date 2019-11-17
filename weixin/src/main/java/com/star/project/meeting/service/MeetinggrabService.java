package com.star.project.meeting.service;

import java.util.Date;
import java.util.List;


import com.star.project.meeting.bean.Meetinggrab;
import com.star.project.meeting.bean.Weiuser;

public interface MeetinggrabService {

	List<Meetinggrab> selectMeetinggrabListByuid(Integer uid);
	
	int insert(Meetinggrab meetinggrab);
	
	 List<Meetinggrab> selectMeetinggrabListByPid(String pid);
	 
	 int updateGrabstatusChooseByUid(Integer uid,String pid,Date date);
	    
	 int updateGrabstatusUnchooseByUid(Integer uid,String pid);
	 
	 Meetinggrab selectGrabUserInfo(String pid);
	 
	 List<Meetinggrab> selectMeetinggrabListByselective(Meetinggrab meetinggrab);

	 int deleteByPrimaryKey(String gid);
	 
	 int deletebatch(String[] ids);
	 
	 int updateGrabstatusByPid(String pid);
	 
	 List<Weiuser> selectMeetinggrabForRank();
}
