package com.star.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class UserSigninStatistics {
	/*主键ID*/
    private String sid;
    /*用户ID*/
    private Integer uid;
    /*上次登录时间*/
    private Date lastsignindate;
    /*连续登录次数*/
    private Integer signincount;
    /*总共登录次数*/
    private Integer continutitycount;
    /*总积分*/
    private Integer integral;
}