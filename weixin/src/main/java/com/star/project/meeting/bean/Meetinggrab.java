package com.star.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class Meetinggrab {
    private String gid;

    private String remark;

    private String pid;

    private Integer uid;

    private Short grabstatus;

    private Date createdate;

    private Date grabdate;
    
    private Meetingpub meetingpub;
    
    private User user;
    
}