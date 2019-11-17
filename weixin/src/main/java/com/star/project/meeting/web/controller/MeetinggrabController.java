package com.star.project.meeting.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.star.project.meeting.bean.Meetinggrab;
import com.star.project.meeting.service.MeetinggrabService;

@Controller
@RequestMapping("meetinggrabmManager")
public class MeetinggrabController {
	@Autowired
	private MeetinggrabService meetinggrabService;
	@RequestMapping("list")
	public ModelAndView selectMeetinggrabListByselective(Meetinggrab meetinggrab) {
		ModelAndView model=new ModelAndView();
		List<Meetinggrab> list=meetinggrabService.selectMeetinggrabListByselective(meetinggrab);
		model.addObject("meetinggrabList", list);
		model.addObject("meetinggrabForm", meetinggrab);
		model.setViewName("/pages/admin/meetinggrab/meetinggrab-list.jsp");
		return model;
	}
	@ResponseBody
	@RequestMapping("delete/{gid}")
	public Integer deleteByPrimaryKey(@PathVariable("gid")String gid) {
		int num=meetinggrabService.deleteByPrimaryKey(gid);
		return num;
	}
	
	@ResponseBody
	@RequestMapping("deletebatch/{ids}")
	public Integer deletebatch(@PathVariable("ids") String ids) {
		String[] newIds=ids.split(",");
		return meetinggrabService.deletebatch(newIds);
		
	}
	@RequestMapping("updateGrabstatus")
	@ResponseBody
	public Integer updateGrabstatusByPid(@RequestParam("pid") String pid) {
		
		return meetinggrabService.updateGrabstatusByPid(pid);
	}
}
