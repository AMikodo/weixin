package com.star.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.star.project.meeting.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    @Select("select * from user where wid=#{wid}")
    User selectByWid(Integer wid);
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);
    @Update("update user set wid=#{arg1} where email=#{arg0}")
    int updateWidByEmail(String email,Integer wid);
    
    List<User> selectUserList(User user);
    
    int deleteBatch(int[] ids);
    @Update("update user set status=#{arg0} where uid=#{arg1}")
    int updateStatusByUid(Integer status,Integer uid);
}