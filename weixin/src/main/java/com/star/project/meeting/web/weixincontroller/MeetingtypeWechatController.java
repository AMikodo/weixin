package com.star.project.meeting.web.weixincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.project.meeting.bean.Meetingtype;
import com.star.project.meeting.service.MeetingtypeService;

@Controller
@RequestMapping("meetingtype")
public class MeetingtypeWechatController {
	@Autowired
	private MeetingtypeService meetingtypeService;
	@ResponseBody
	@RequestMapping("list")
	public List<Meetingtype> getMeetingtypeList() {
		
		return meetingtypeService.selectByStatus();
	}
}
