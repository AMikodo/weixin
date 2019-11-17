package com.star.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.star.project.meeting.bean.Meetingtype;

public interface MeetingtypeMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Meetingtype record);

    int insertSelective(Meetingtype record);

    Meetingtype selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Meetingtype record);

    int updateByPrimaryKey(Meetingtype record);
    @Select("SELECT * from meetingtype where status=1  ORDER BY sortnum")
    List<Meetingtype> selectByStatus();
}