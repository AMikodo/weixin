package com.star.project.meeting.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.Meetinggrab;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.mapper.MeetinggrabMapper;
import com.star.project.meeting.service.MeetinggrabService;
@Service
public class MeetinggrabServiceImpl implements MeetinggrabService{
	@Autowired
	private MeetinggrabMapper meetinggrabMapper;
	
	@Override
	public List<Meetinggrab> selectMeetinggrabListByuid(Integer uid) {
		
		return meetinggrabMapper.selectMeetinggrabListByuid(uid);
	}

	@Override
	public int insert(Meetinggrab meetinggrab) {
		
		return meetinggrabMapper.insert(meetinggrab);
	}

	@Override
	public List<Meetinggrab> selectMeetinggrabListByPid(String pid) {
		
		return meetinggrabMapper.selectMeetinggrabListByPid(pid);
	}

	@Override
	public int updateGrabstatusChooseByUid(Integer uid, String pid,Date date) {
		
		return meetinggrabMapper.updateGrabstatusChooseByUid(uid, pid,date);
	}

	@Override
	public int updateGrabstatusUnchooseByUid(Integer uid, String pid) {
		
		return meetinggrabMapper.updateGrabstatusUnchooseByUid(uid, pid);
	}

	@Override
	public Meetinggrab selectGrabUserInfo(String pid) {
		
		return meetinggrabMapper.selectGrabUserInfo(pid);
	}

	@Override
	public List<Meetinggrab> selectMeetinggrabListByselective(Meetinggrab meetinggrab) {
		
		return meetinggrabMapper.selectMeetinggrabListByselective(meetinggrab);
	}

	@Override
	public int deleteByPrimaryKey(String gid) {
		
		return meetinggrabMapper.deleteByPrimaryKey(gid);
	}

	@Override
	public int deletebatch(String[] ids) {
		
		return meetinggrabMapper.deletebatch(ids);
	}

	@Override
	public int updateGrabstatusByPid(String pid) {
		
		return meetinggrabMapper.updateGrabstatusByPid(pid);
	}

	@Override
	public List<Weiuser> selectMeetinggrabForRank() {
		List<Weiuser> list=meetinggrabMapper.selectMeetinggrabForRank();
		for (Weiuser weiuser : list) {
			if(weiuser.getNickname()!=null) {
				String newNickname=null;
				try {
					newNickname=URLDecoder.decode(weiuser.getNickname(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				weiuser.setNickname(newNickname);
			}
		}
		return list;
	}

}
