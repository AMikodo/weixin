package com.star.project.meeting.web.weixincontroller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.star.project.meeting.bean.Meetinggrab;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.service.MeetinggrabService;
@RequestMapping("meetinggrab")
@Controller
public class MeetinggrabWechatController {
	@Autowired
	private MeetinggrabService meetinggrabService;
	@RequestMapping("mylist/{uid}")
	@ResponseBody
	public List<Meetinggrab> selectMeetinggrabListByuid(@PathVariable("uid") Integer uid){
		List<Meetinggrab> list=meetinggrabService.selectMeetinggrabListByuid(uid);
		return list;
		
	}
	@RequestMapping("tomeetinggrab")
	public String toMeetinggrab(@RequestParam("uid")Integer uid,
								@RequestParam("pid")String pid,
								Map<String, Object> map) {
		 map.put("uid", uid);
		 map.put("pid", pid);
		return "/pages/weixin/meetinggrab/meetinggrab-add.jsp";
	}
	@ResponseBody
	@RequestMapping("insert")
	public Integer insertMeetinggrab(Meetinggrab meetinggrab) {
		meetinggrab.setGid(UUID.randomUUID().toString());
		meetinggrab.setCreatedate(new Date());
		meetinggrab.setGrabstatus((short)0);
		return meetinggrabService.insert(meetinggrab);
	}
	@ResponseBody
	@RequestMapping("selectMeetinggrabListByPid")
	public List<Meetinggrab> selectMeetinggrabListByPid(@RequestParam("pid")String pid) {
		List<Meetinggrab> list=meetinggrabService.selectMeetinggrabListByPid(pid);
		return list;
	}
	@ResponseBody
	@RequestMapping("updateGrabstatus")
	public int updateGrabstatusByuid(@RequestParam("uid")Integer uid,
									@RequestParam("pid") String pid) {
		Date date =new Date();
		meetinggrabService.updateGrabstatusChooseByUid(uid, pid,date);
		meetinggrabService.updateGrabstatusUnchooseByUid(uid, pid);
		return 1;
	}
	@RequestMapping("selectGrabUserInfo")
	@ResponseBody
	 public Meetinggrab selectGrabUserInfo(@RequestParam("pid")String pid) {
		 return meetinggrabService.selectGrabUserInfo(pid);
	 }
	@RequestMapping("rank")
	public ModelAndView selectMeetinggrabForRank() {
		ModelAndView model=new ModelAndView();
		List<Weiuser> list=meetinggrabService.selectMeetinggrabForRank();
		model.addObject("rank", list);
		model.setViewName("/pages/weixin/meetinggrab/meetinggrab-rank.jsp");
		return model;
	}
}
