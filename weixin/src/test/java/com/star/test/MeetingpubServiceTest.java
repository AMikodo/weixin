package com.star.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.star.project.meeting.bean.Meetinggrab;
import com.star.project.meeting.bean.Meetingpub;
import com.star.project.meeting.bean.User;
import com.star.project.meeting.service.MeetinggrabService;
import com.star.project.meeting.service.MeetingpubService;

public class MeetingpubServiceTest {
	@Test
	public void t1() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
		
		MeetinggrabService service=ctx.getBean(MeetinggrabService.class);
		List<Meetinggrab> list=service.selectMeetinggrabListByuid(10);
		for (Meetinggrab l : list) {
			System.out.println(l);
		}
	}
	@Test
	public void t2() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
		
		MeetinggrabService service=ctx.getBean(MeetinggrabService.class);
		List<Meetinggrab> list=service.selectMeetinggrabListByPid("44dfc572-3a9d-4522-8aea-1839cccdbf6b");
		for (Meetinggrab l : list) {
			System.out.println(l);
		}
	}
	@Test
	public void t3() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
		MeetingpubService service=ctx.getBean(MeetingpubService.class);
		Meetingpub meetingpub=new Meetingpub();
//		User u=new User();
//		u.setName("测试");
//		meetingpub.setPcode("20190629001");
//		meetingpub.setTname("java");
//		meetingpub.setUser(u);
		List<Meetingpub> list=service.selectMeetingpubListBySelective(meetingpub);
		for (Meetingpub l : list) {
			System.out.println(l);
		}
	}
	@Test
	public void t4() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
		MeetingpubService service=ctx.getBean(MeetingpubService.class);
		Meetinggrab meetinggrab=new Meetinggrab();
		meetinggrab.setGrabstatus((short)-1);
		Meetingpub meetingpub=new Meetingpub();
		meetingpub.setMeetinggrab(meetinggrab);
		List<Meetingpub> list=service.selectMeetingpubListBySelective(meetingpub);
		for (Meetingpub l : list) {
			System.out.println(l);
		}
	}
	
	@Test
	public void t5() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
		MeetingpubService service=ctx.getBean(MeetingpubService.class);
		System.out.println(service.logInfo());
	}
}
