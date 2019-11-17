package com.star.project.meeting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.star.project.meeting.bean.Meetingpub;
import com.star.project.meeting.bean.User;
import com.star.project.meeting.bean.Weiuser;

public interface MeetingpubMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Meetingpub record);

    int insertSelective(Meetingpub record);

    Meetingpub selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Meetingpub meetingpub);

    int updateByPrimaryKey(Meetingpub record);
    
    
    List<Meetingpub> selectMeetingpubList(Integer uid);
    @Select("SELECT max(pcode) FROM meetingpub  WHERE LEFT(pcode,8) = #{time}")
    String selectMaxPcodeByTime(String time);
    
    List<Meetingpub> selectListByZoneAndStatus(Integer uid,String tname);
    
    List<Meetingpub> selectMeetingpubListBySelective(Meetingpub meetingpub);
    
    int deletebatch(String[] ids);
    
    @Update("update meetingpub set status=#{arg0} where pid=#{arg1}")
    int updateStatusByPid(Integer status,String pid);
    /**
     * 昨日日报
     */
    //昨日发单数量
    @Select("select count(*) from meetingpub where DATEDIFF(NOW(),createdate)=1")
    int selectYesterdayCountMeetingPub();
    //今日可抢单数量
    @Select("select tname,count(*) as uid from meetingpub where pid not in (select pid from meetinggrab where grabstatus=1) group by tname order by uid desc")
    List<Meetingpub> selectTodayMeetingpubCount();
    //昨天成功匹配多少单子
    @Select("select count(*) from meetinggrab where grabstatus=1 and DATEDIFF(NOW(),grabdate)=0 ")
    int selectYesterdayMatchCountMeetingPub();
    @Select("select count(*) from meetingpub where pid in (select pid from meetinggrab where grabstatus=1) and DATEDIFF(NOW(),ptime)=0")
    int selectYesterdayExcCount();
    @Select("SELECT  wei.nickname,wei.headimgurl,"
    		+ "SUM(CASE WHEN grab.grabstatus=1 THEN 1 ELSE 0 END) "
    		+ "AS city , COUNT(*)AS province FROM meetingpub pub "
    		+ "LEFT JOIN meetinggrab grab ON pub.pid=grab.pid AND "
    		+ "grab.grabstatus=1 LEFT JOIN `user` u "
    		+ "ON u.uid=pub.uid LEFT JOIN weiuser wei "
    		+ "ON wei.wid=u.wid GROuP BY pub.uid ORDER BY province DESC")
    List<Weiuser> selectMeetingpubForRank();
}
