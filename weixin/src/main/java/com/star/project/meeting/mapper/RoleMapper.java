package com.star.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.star.project.meeting.bean.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role role);

    int insertSelective(Role role);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role role);

    int updateByPrimaryKey(Role role);
   
    List<Role> selectList(Role role);
    
    int deleteBatch(int[] ids);
    @Update("update role set status=#{arg0} where rid=#{arg1}")
    int updateStatusByPrimarykey(Integer status,Integer rid);
}