package com.star.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.star.project.meeting.bean.UserSigninStatistics;

public interface UserSigninStatisticsMapper {
    int deleteByPrimaryKey(String sid);

    int insert(UserSigninStatistics signinStatistics);

    int insertSelective(UserSigninStatistics record);

    UserSigninStatistics selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(UserSigninStatistics  signinStatistics);

    int updateByPrimaryKey(UserSigninStatistics record);
    @Select("select * from usersigninstatistics where uid=#{uid}")
    UserSigninStatistics selectByPrimaryUid(Integer uid);
    @Select("select DATEDIFF(NOW(),(SELECT lastsignindate FROM usersigninstatistics WHERE uid=#{arg0})) AS days FROM usersigninstatistics where uid=#{arg0} ")
    int dateDifferent(Integer uid);
    @Select("SELECT count(*) FROM usersigninstatistics")
    int countUserSigninStatistics();
    @Select("select SUM(integral >(SELECT integral  from usersigninstatistics WHERE uid=#{uid})) as aa "
    		+ "FROM usersigninstatistics ")
    int countBeyondUser(Integer uid);
  
}