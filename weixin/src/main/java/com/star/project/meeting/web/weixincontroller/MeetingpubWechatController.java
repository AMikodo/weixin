package com.star.project.meeting.web.weixincontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.star.project.meeting.bean.Meetingpub;
import com.star.project.meeting.bean.User;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.service.MeetingpubService;
import com.star.project.meeting.service.UserService;

@Controller
@RequestMapping("meetingpub")
public class MeetingpubWechatController {
	@Autowired
	private MeetingpubService meetingpubService;
	@Autowired
	private UserService userService;
	@RequestMapping("insert")
	@ResponseBody
	public Integer insert( Meetingpub meetingpub ) {
		
		return meetingpubService.insert(meetingpub);
	}
	@ResponseBody
	@RequestMapping("list/{uid}")
	public List<Meetingpub> selectList(@PathVariable("uid") Integer uid){
		List<Meetingpub> list=meetingpubService.selectMeetingpubList(uid);
		return list;
	}
	@RequestMapping("meetingpubInfo")
	public ModelAndView selectMeetingpubAndUserInfo(@RequestParam("pid") String pid) {
		ModelAndView model= new ModelAndView();
		Meetingpub meetingpub=meetingpubService.selectByPrimaryKey(pid);
		User user=userService.selectByPrimaryKey(meetingpub.getUid());
		model.addObject("meetingpub", meetingpub);
		model.addObject("user", user);
		model.setViewName("/pages/weixin/meetingpub/MeetingpubInfo.jsp");
		return model;
		
	}
	@ResponseBody
	@RequestMapping("selectListByZoneAndStatus")
	public List<Meetingpub> selectListByZoneAndStatus(
			@RequestParam("uid")Integer uid,
			@RequestParam("tname") String tname){
		List<Meetingpub> list=meetingpubService.selectListByZoneAndStatus(uid,tname);
		return list;
	}
	@RequestMapping("rank")
	public ModelAndView selectMeetingpubForRank() {
		ModelAndView model=new ModelAndView();
		List<Weiuser> list=meetingpubService.selectMeetingpubForRank();
		model.addObject("rank", list);
		model.setViewName("/pages/weixin/meetingpub/meetingpub-rank.jsp");
		return model;
	
	}
}
