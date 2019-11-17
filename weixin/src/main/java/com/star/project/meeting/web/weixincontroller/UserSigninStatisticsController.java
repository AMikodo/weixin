package com.star.project.meeting.web.weixincontroller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.star.project.meeting.bean.SigninDate;
import com.star.project.meeting.bean.UserSigninStatistics;
import com.star.project.meeting.bean.WeiUserOA;
import com.star.project.meeting.service.SigninDateService;
import com.star.project.meeting.service.UserSigninStatisticsService;
import com.star.project.meeting.service.WeiuserInfoService;

@Controller
@RequestMapping("userSigninStatistics")
public class UserSigninStatisticsController {
	@Autowired
	private UserSigninStatisticsService userSigninStatisticsService;
	@Autowired
	private SigninDateService signinDateService;
	@Autowired
	private WeiuserInfoService weiuserInfoService;
	
	@ResponseBody
	@RequestMapping("getInfo")
	public UserSigninStatistics userSigninStatisticsService(@RequestParam("uid")Integer uid) {
		UserSigninStatistics userSigninStatistics=userSigninStatisticsService.selectByPrimaryUid(uid);
		if(userSigninStatistics==null) {
			UserSigninStatistics newUserSigninStatistics=new UserSigninStatistics();
			newUserSigninStatistics.setSid(UUID.randomUUID().toString());
			newUserSigninStatistics.setContinutitycount(1);
			newUserSigninStatistics.setIntegral(1);
			newUserSigninStatistics.setLastsignindate(new Date());
			newUserSigninStatistics.setUid(uid);
			newUserSigninStatistics.setSignincount(1);
			userSigninStatisticsService.insert(newUserSigninStatistics);
			return newUserSigninStatistics;
		}
		//两次登录时间时间差
		int days=userSigninStatisticsService.dateDifferent(uid);
		if(days==0) {
			return userSigninStatistics;
		}
		userSigninStatistics.setLastsignindate(new Date());
		userSigninStatistics.setContinutitycount(userSigninStatistics.getContinutitycount()+1);
		if(days==1) {
			//连续登录天数加1
			userSigninStatistics.setSignincount(userSigninStatistics.getSignincount()+1);
			//积分加2
			userSigninStatistics.setIntegral(userSigninStatistics.getIntegral()+2);
		}else {
			//连续登录天数设为1
			userSigninStatistics.setSignincount(1);
			//积分加1
			userSigninStatistics.setIntegral(userSigninStatistics.getIntegral()+1);
		}
		userSigninStatisticsService.updateByPrimaryKeySelective(userSigninStatistics);
		
		return userSigninStatistics;
	}
	@RequestMapping("getSigninDateInfo")
	@ResponseBody
	public String getSigninDateInfo(@RequestParam("uid") Integer uid) {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(new Date()).substring(0, 7);
		List<SigninDate> list=signinDateService.selectListByUid(uid, time);
		StringBuilder builder=new StringBuilder();
		for (SigninDate l : list) {
			builder.append(l.getSignindate().toString());
			builder.append(",");
		}
		return builder.toString();
	}
	@RequestMapping("getSigninProportion")
	@ResponseBody
	public String getSigninProportion(@RequestParam("uid") Integer uid) {
		int count=userSigninStatisticsService.countUserSigninStatistics();
		int beyoundUser=userSigninStatisticsService.countBeyondUser(uid);
		int proportion=(count-beyoundUser)*100/count;
		return proportion+"%";
	}
	@RequestMapping("rank")
	public ModelAndView selectWeiuserRank(){
		ModelAndView model=new ModelAndView();
		List<WeiUserOA> list=weiuserInfoService.selectWeiuserRank();
		model.addObject("rank", list);
		model.setViewName("/pages/weixin/signin/sign-rank.jsp");
		return model;
	}
}
