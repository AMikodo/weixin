package com.star.project.meeting.web.weixincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.project.meeting.bean.User;
import com.star.project.meeting.service.UserService;

@Controller
@RequestMapping("userWechat")
public class UserWechatController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	@ResponseBody
	public String login(@RequestParam("email")String email,
						@RequestParam("wid") Integer wid) {
		User user=userService.selectByEmail(email);
		if(user==null) {
			return ""+1; 
		}else {
			if(user.getWid()!=null) {
				return ""+2;
			}
		}
		userService.updateWidByEmail(email, wid);
		return ""+3;
	}
	@ResponseBody
	@RequestMapping("update")
	public int update(User user) {
		
		return userService.updateByPrimaryKeySelective(user);
	}
}
