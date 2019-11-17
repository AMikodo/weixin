package com.star.project.meeting.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.star.project.meeting.bean.Manager;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Manager manager);

    int insertSelective(Manager manager);

    Manager selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Manager manager);

    int updateByPrimaryKey(Manager manager);
    
    @Select("select * from manager where uname=#{uname} and upass=#{upass} and status=1")
	Manager selectByUnameAndUpass(@Param("uname")String uname,@Param("upass")String upass);
}