package com.star.project.meeting.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.Meetingpub;
import com.star.project.meeting.bean.User;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.mapper.MeetingpubMapper;
import com.star.project.meeting.service.MeetingpubService;
import com.star.project.meeting.util.DateUtil;
@Service
public class MeetingpubServiceImpl implements MeetingpubService{
	
	@Autowired
	private MeetingpubMapper meetingpubMapper;
	@Override
	public int insert(Meetingpub meetingpub) {
		meetingpub.setPid(UUID.randomUUID().toString());
		meetingpub.setStatus((short)1);
		meetingpub.setCreatedate(new Date());
		meetingpub.setPcode(this.getPcode(meetingpub.getPtime()));
		meetingpub.setPtime(meetingpub.getPtime().replace("T", " "));
		return meetingpubMapper.insert(meetingpub);
	}

	@Override
	public List<Meetingpub> selectMeetingpubList(Integer uid) {
		
		return meetingpubMapper.selectMeetingpubList(uid);
	}

	@Override
	public String selectMaxPcodeByTime(String time) {
		
		return meetingpubMapper.selectMaxPcodeByTime(time);
	}
	
	public String getPcode(String ptime) {
		String time=ptime.substring(0, 10).replace("-", "");
		String pcode=this.selectMaxPcodeByTime(time);
		if(StringUtils.isEmpty(pcode)) {
			pcode=time+"001";
		}else {
			Long newPcode=Long.parseLong(pcode)+1;
			pcode=newPcode.toString();
		}
		return pcode;
	}

	@Override
	public Meetingpub selectByPrimaryKey(String pid) {
		
		return meetingpubMapper.selectByPrimaryKey(pid);
	}

	@Override
	public List<Meetingpub> selectListByZoneAndStatus(Integer uid,String tname) {
		
		return meetingpubMapper.selectListByZoneAndStatus(uid,tname);
	}

	@Override
	public List<Meetingpub> selectMeetingpubListBySelective(Meetingpub meetingpub) {
		
		return meetingpubMapper.selectMeetingpubListBySelective(meetingpub);
	}

	@Override
	public int deleteByPrimaryKey(String pid) {
		
		return meetingpubMapper.deleteByPrimaryKey(pid);
	}

	@Override
	public int deletebatch(String[] ids) {
		
		return meetingpubMapper.deletebatch(ids);
	}

	@Override
	public int updateByPrimaryKeySelective(Meetingpub meetingpub) {
		
		return meetingpubMapper.updateByPrimaryKeySelective(meetingpub);
	}

	@Override
	public int updateStatusByPid(Integer status, String pid) {
		
		return meetingpubMapper.updateStatusByPid(status, pid);
	}

	@Override
	public String logInfo() {
		StringBuffer buffer2=new StringBuffer();
		int sumCount=0;
		List<Meetingpub> list=meetingpubMapper.selectTodayMeetingpubCount();
		for (Meetingpub l : list) {
			sumCount+=l.getUid();
			buffer2.append(l.getTname()+"可抢单:"+l.getUid()+"场\n");
		}
		StringBuffer buffer=new StringBuffer();
		buffer.append("     每日日报(今日"+DateUtil.getToday()+"日)\n");
		buffer.append("1、昨天("+DateUtil.getYesterday()+")发单数量"+meetingpubMapper.selectYesterdayCountMeetingPub()+"场\n");
		buffer.append("2、今日可抢单场数"+sumCount+"\n");
		buffer.append(buffer2);
		buffer.append("3、总结\n");
		buffer.append("昨天成功匹配:"+meetingpubMapper.selectYesterdayMatchCountMeetingPub()+"场\n");
		buffer.append("昨天成功执行:"+meetingpubMapper.selectYesterdayExcCount()+"场\n");
		return buffer.toString();
	}

	@Override
	public List<Weiuser> selectMeetingpubForRank() {
		List<Weiuser> list=meetingpubMapper.selectMeetingpubForRank();
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
