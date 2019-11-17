package com.star.project.meeting.bean;

import lombok.Data;

@Data
public class Weiuser {
    private Integer wid;

    private String openid;

    private String nickname;

    private Short sex;

    private String country;

    private String province;

    private String city;

    private String headimgurl;

    private Short subscribe;

    private String language;

    private String remark;
    
    //以下为微信所需字段
    
    private String subscribe_time;
    private String unionid;
    private String groupid;
    private String[] tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
    
    //网页access_token获取的信息
    private String[] privilege;
    
    
}