package com.star.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.star.project.meeting.bean.WeiUserOA;
import com.star.project.meeting.bean.Weiuser;

public interface WeiuserMapper {
    int deleteByPrimaryKey(Integer wid);

    int insert(Weiuser record);

    int insertSelective(Weiuser record);

    Weiuser selectByPrimaryKey(Integer wid);

    int updateByPrimaryKeySelective(Weiuser record);

    int updateByPrimaryKey(Weiuser record);
    @Select("select * from weiuser where openid=#{openid}")
    Weiuser selectByOPenid(String openid);
    @Select("SELECT wei.* from weiuser wei"
    		+ " LEFT JOIN `user` u ON wei.wid=u.wid "
    		+ "WHERE uid IN "
    		+ "(select uid from usersigninstatistics "
    		+ "WHERE DATEDIFF(NOW(),lastsignindate)=1 "
    		+ "OR (DATEDIFF(NOW(),lastsignindate)=0 "
    		+ "AND signincount>1 ))")
    List<Weiuser> selectWeiuserToSendMessage();
    
    List<WeiUserOA> selectWeiuserRank();
}