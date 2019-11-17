package com.star.project.meeting.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.star.project.meeting.bean.Role;
import com.star.project.meeting.service.RoleService;
@RequestMapping("role")
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@RequestMapping("list")
	public ModelAndView selectList(Role role) {
		ModelAndView model= new ModelAndView();
		List<Role> list= roleService.selectList(role);
		model.addObject("rolelist", list);
		model.addObject("roleFrom", role);
		model.setViewName("/pages/admin/role-list.jsp");
		return model;
		
	}
	@ResponseBody
	@RequestMapping(value="{rid}",method=RequestMethod.POST)
	public String deleteByPrimarykey(@PathVariable("rid") Integer rid) {
		
		return ""+roleService.deleteByPrimaryKey(rid);
	}
	@ResponseBody
	@RequestMapping(value="deletebatch/{ids}")
	public String deleteBatch(@PathVariable("ids")String ids) {
		int[] rids= new int[ids.length()];
		String[] s=ids.split(",");
		for (int i = 0; i < s.length; i++) {
			rids[i]=Integer.parseInt(s[i]);
		}
		
		return ""+roleService.deleteBatch(rids);
	}
	@RequestMapping("select")
	public ModelAndView selectByPrimarykey(@RequestParam("rid")Integer rid) {
		ModelAndView model=new ModelAndView();
		Role role=roleService.selectByPrimaryKey(rid);
		model.addObject("role", role);
		model.setViewName("/pages/admin/role-edit.jsp");
		return model;
		
	}
	@RequestMapping("updateto")
	public ModelAndView updateByPrimarykey(Role role) {
		ModelAndView model= new ModelAndView();
		roleService.updateByPrimaryKey(role);
		model.setViewName("redirect:/role/list");
		return model;
	}
	@ResponseBody
	@RequestMapping("updateStatus")
	public String updateStatusByPrimarykey(@RequestParam("status") Integer status,
											@RequestParam("rid")Integer rid) {
		
		
		return ""+roleService.updateStatusByPrimarykey(status, rid);
	}
	@RequestMapping("insert")
	public String insert(Role role) {
		roleService.insert(role);
		return "redirect:/role/list";
	}
}
