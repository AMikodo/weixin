package com.star.project.meeting.bean;

import java.util.Date;

import lombok.Data;
@Data
public class User {
    private Integer uid;

    private String name;

    private String email;

    private String telphone;

    private String province;

    private String city;

    private String zone;

    private Integer rid;

    private Short status;

    private Date createdate;

    private Integer wid;

 
}