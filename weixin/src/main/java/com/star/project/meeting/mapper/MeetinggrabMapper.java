package com.star.project.meeting.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.star.project.meeting.bean.Meetinggrab;
import com.star.project.meeting.bean.Weiuser;

public interface MeetinggrabMapper {
    int deleteByPrimaryKey(String gid);

    int insert(Meetinggrab meetinggrab);

    int insertSelective(Meetinggrab record);

    Meetinggrab selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(Meetinggrab record);

    int updateByPrimaryKey(Meetinggrab record);
    /*根据uid查改用户抢过哪些单子*/
    List<Meetinggrab> selectMeetinggrabListByuid(Integer uid);
    /*根据pid 查询 有多少人抢了这些单子*/
    List<Meetinggrab> selectMeetinggrabListByPid(String pid);
    /*修改确定人选的抢单状态*/
    @Update("update meetinggrab set grabstatus=1 , grabdate=#{param3} where uid=#{param1} and pid=#{param2}")
    int updateGrabstatusChooseByUid(Integer uid,String pid,Date date);
    /*修改没有被选择人的状态*/
    @Update("update meetinggrab set grabstatus=3 where uid!=#{param1} and pid=#{param2}")
    int updateGrabstatusUnchooseByUid(Integer uid,String pid);
    
    Meetinggrab selectGrabUserInfo(String pid);
    
    List<Meetinggrab> selectMeetinggrabListByselective(Meetinggrab meetinggrab);

    int deletebatch(String[] ids);
    @Update("update meetinggrab set grabstatus=0 where pid=#{pid}")
    int updateGrabstatusByPid(String pid);
    @Select("SELECT wei.nickname,wei.headimgurl,"
    		+ "SUM(CASE WHEN grab.grabstatus=1 THEN 1 ELSE 0 END)"
    		+ " AS city ,COUNT(*)AS province  FROM meetinggrab grab "
    		+ "LEFT JOIN `user` u ON grab.uid=u.uid "
    		+ "LEFT JOIN weiuser wei ON u.wid=wei.wid "
    		+ "GROUP BY grab.uid  ORDER BY province DESC ")
    List<Weiuser> selectMeetinggrabForRank();
}