package com.star.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.star.project.meeting.bean.Meetinggrab;
import com.star.project.meeting.bean.Meetingpub;
import com.star.project.meeting.bean.User;
import com.star.project.meeting.service.MeetinggrabService;
import com.star.project.meeting.service.MeetingpubService;

public class MeetinggrabServiceTest {
	@Test
	public void t1() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
	
	}
	@Test
	public void t2() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring_core.xml");
		MeetinggrabService service=ctx.getBean(MeetinggrabService.class);
//		User user=new User();
//		user.setName("古一");
//		Meetingpub meetingpub=new Meetingpub();
//		meetingpub.setTname("H5");
		Meetinggrab meetinggrab =new Meetinggrab();
		//meetinggrab.setMeetingpub(meetingpub);
		//meetinggrab.setUser(user);
		List<Meetinggrab> list=service.selectMeetinggrabListByselective(meetinggrab);
		for (Meetinggrab l : list) {
			System.out.println(l);
		}
	}

}
