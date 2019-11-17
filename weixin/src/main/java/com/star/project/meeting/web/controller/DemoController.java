package com.star.project.meeting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.project.meeting.bean.Student;
import com.star.project.meeting.service.StudentService;

@Controller
@RequestMapping("student")
public class DemoController {
	
	private static final Logger log=LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private StudentService studentService;
	@ResponseBody
	@RequestMapping("{id}")  //  demo/1
	public Student selectById(@PathVariable("id")final Integer id){
		
		log.info("根据请求的路径查询信息 demo/"+id);
		log.error("错误的日志级别");
		return studentService.selectByPrimaryKey(id);
	}
}
