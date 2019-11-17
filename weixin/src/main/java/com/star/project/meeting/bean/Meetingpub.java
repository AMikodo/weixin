package com.star.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class Meetingpub {
    private String pid;

    private String pcode;

    private String ptime;

    private String tname;

    private String ptitle;

    private String zone;

    private String remark;

    private Date createdate;

    private Short status;

    private Integer uid;
    
    private Meetinggrab meetinggrab;
    
    private User user;
 
}