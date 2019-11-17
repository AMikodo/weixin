package com.star.project.meeting.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.star.project.meeting.bean.User;
import com.star.project.meeting.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("list")
	public ModelAndView selectUserList(User user) {
		ModelAndView model=new ModelAndView();
		List<User> list=userService.selectUserList(user);
		model.addObject("userList", list);
		model.addObject("userFrom", user);
		model.setViewName("/pages/admin/user/user-list.jsp");
		return model;
		
	}
	@ResponseBody
	@RequestMapping(value="delete/{uid}")
	public String delete(@PathVariable("uid") Integer uid) {
		
		return""+userService.deleteByPrimaryKey(uid);
	}
	
	@RequestMapping(value="deletebatch/{ids}")
	@ResponseBody
	public String deletebatch(@PathVariable("ids")String ids) {
		int[] uids= new int[ids.length()];
		String[] s=ids.split(",");
		for (int i = 0; i < s.length; i++) {
			uids[i]=Integer.parseInt(s[i]);
		}
		
		return ""+userService.deleteBatch(uids);
	}
	@RequestMapping("select")
	public ModelAndView selectByPrimarykey(@RequestParam("uid")Integer uid) {
		ModelAndView model= new ModelAndView();
		User user=userService.selectByPrimaryKey(uid);
		model.addObject("user", user);
		model.setViewName("/pages/admin/user/user-edit.jsp");
		return model;
	}
	@RequestMapping("update")
	public String update(User user) {
		userService.updateByPrimaryKeySelective(user);
		
		return "redirect:/user/list";
	}
	@ResponseBody
	@RequestMapping("updateStatus")
	public String updateStatusByUid(@RequestParam("status")Integer status,
			 						@RequestParam("uid")Integer uid) {
		
		
		return ""+userService.updateStatusByUid(status, uid);
	}
	@RequestMapping("insert")
	public String insert(User user) {
		user.setCreatedate(new Date());
		userService.insert(user);
		return "redirect:/user/list";
	}
}
