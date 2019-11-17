package com.star.project.meeting.web.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.star.project.meeting.bean.Manager;
import com.star.project.meeting.service.ManagerService;
@RequestMapping("adminUser")
@Controller
public class AdminUserController {
	@Autowired
	private ManagerService managerService;
	@RequestMapping("login")
	public String login(@RequestParam("uname") final String uname,
			            @RequestParam("upass") final String upass,
			            @RequestParam("validatecode") final String validatecode,
			            HttpServletRequest request,
			            HttpSession session,
			            Map<String, Object> map) {
		String codeimg=(String) session.getAttribute(ValidateColorServlet.CHECK_CODE_KEY);
		if(!codeimg.equalsIgnoreCase(validatecode)) {
			return "redirect:/pages/manager/login.jsp?uname="+uname+"&tips=1";
		}else {
			Manager manager=managerService.selectByUnameAndUpass(uname, upass);
			if(manager==null) {
				return "redirect:/pages/manager/login.jsp?uname="+uname+"&tips=2";
			}else {
				String ip=request.getLocalAddr();
				int count=manager.getCount();
				if(count==0) {
					manager.setLastip(ip);
					manager.setCount(count+1);
					manager.setLastdate(new Date());
					managerService.updateByPrimaryKeySelective(manager);
					session.setAttribute("adminUser", manager);
				}else {
					Manager m=new Manager();
					manager.setCount(count+1);
					session.setAttribute("adminUser", manager);
					try {
						BeanUtils.copyProperties(m, manager);
						m.setLastip(ip);
						m.setLastdate(new Date());
						map.put("adminUser", manager);
						managerService.updateByPrimaryKeySelective(m);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		
		return "redirect:/pages/manager/index.jsp";
	}
}
