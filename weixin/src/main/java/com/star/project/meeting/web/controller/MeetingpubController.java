package com.star.project.meeting.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.star.project.meeting.bean.Meetingpub;
import com.star.project.meeting.service.MeetingpubService;

@Controller
@RequestMapping("meetingpubManager")
public class MeetingpubController {
	@Autowired
	private MeetingpubService meetingpubService;
	
	
	@RequestMapping("list")
	public ModelAndView selectMeetingpubListBySelective(Meetingpub meetingpub){
		ModelAndView model=new ModelAndView();
		List<Meetingpub> list=meetingpubService.selectMeetingpubListBySelective(meetingpub);
		model.addObject("meetingpubList", list);
		model.addObject("meetingpubForm", meetingpub);
		model.setViewName("/pages/admin/meetingpub/meetingpub-list.jsp");
		return model;
	}
	@ResponseBody
	@RequestMapping("delete/{pid}")
	public Integer deleteByPrimaryKey(@PathVariable("pid")String pid) {
		int num=meetingpubService.deleteByPrimaryKey(pid);
		return num;
	}
	@ResponseBody
	@RequestMapping("deletebatch/{ids}")
	public Integer deletebatch(@PathVariable("ids") String ids) {
		String[] newIds=ids.split(",");
		return meetingpubService.deletebatch(newIds);
		
	}
	@RequestMapping("select")
	public ModelAndView selectMeetingpubByPid(@RequestParam("pid")String pid) {
		Meetingpub meetingpub=meetingpubService.selectByPrimaryKey(pid);
		ModelAndView model=new ModelAndView();
		model.addObject("meetingpub", meetingpub);
		model.setViewName("/pages/admin/meetingpub/meetingpub-edit.jsp");
		return model;
	}
	@RequestMapping("updateTo")
	public String updateByPrimaryKeySelective(Meetingpub meetingpub) {
		meetingpubService.updateByPrimaryKeySelective(meetingpub);
		return "redirect:/meetingpubManager/list";
	}
	@ResponseBody
	@RequestMapping("updateStatus")
	public Integer updateStatusByPid(@RequestParam("status") Integer status,
									@RequestParam("pid") String pid){
		
		return meetingpubService.updateStatusByPid(status, pid);
	} 
	
	
}
