package com.star.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.star.project.meeting.bean.SigninDate;

public interface SigninDateMapper {
    int deleteByPrimaryKey(String id);

    int insert(SigninDate signinDate);

    int insertSelective(SigninDate signinDate);

    SigninDate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SigninDate signinDate);

    int updateByPrimaryKey(SigninDate record);
    
    @Select("select * from signindate where uid=#{arg0} and LEFT(signindate,7) = #{arg1}")
    List<SigninDate> selectListByUid(Integer uid,String time);
}