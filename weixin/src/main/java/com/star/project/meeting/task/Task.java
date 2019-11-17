package com.star.project.meeting.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.service.MeetingpubService;
import com.star.project.meeting.service.WeiuserInfoService;
import com.star.project.weixin.api.customerApi.Message;
import com.star.project.weixin.api.customerApi.MessageCustomer;
import com.star.project.weixin.api.customerApi.MessageText;

import net.sf.json.JSONObject;

@Service
public class Task {
	@Autowired
	private MeetingpubService meetingpubService;
	@Autowired
	private WeiuserInfoService weiuserInfoService;
	
	@Autowired
	private MessageCustomer messageCustomer;
	
	/*
	@Scheduled(cron="0/3 * * * * *")
	public void test() {
		System.out.println("定时任务开启");
	}
	
	*/
	@Scheduled(cron="0 0 8 * * ?  ")
	public void task() {
		Message message=new Message();
		message.setMsgtype("text");
		MessageText text=new MessageText();
		List<Weiuser> list=weiuserInfoService.selectWeiuserToSendMessage();
		for (Weiuser weiuser : list) {
			message.setTouser(weiuser.getOpenid());
			text.setContent(meetingpubService.logInfo());
			//text.setContent("测试几个人");
			message.setText(text);
			JSONObject jsonObject=JSONObject.fromObject(message);
			messageCustomer.sendMessage(jsonObject);
		}
		
	}
	
}
