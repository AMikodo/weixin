package com.star.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.star.project.meeting.bean.WeiUserOA;
import com.star.project.meeting.service.WeiuserInfoService;

public class WeiUserTest {
	
	@Test
	public void t1() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
		WeiuserInfoService service=ctx.getBean(WeiuserInfoService.class);
		List<WeiUserOA> list=service.selectWeiuserRank();
		for (WeiUserOA l : list) {
			System.out.println(l);
		}
	}
}
