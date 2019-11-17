package com.star.project.meeting.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.project.meeting.bean.WeiUserOA;
import com.star.project.meeting.bean.Weiuser;
import com.star.project.meeting.mapper.WeiuserMapper;
import com.star.project.meeting.service.WeiuserInfoService;

@Service
public class WeiuserInfoServiceImpl implements WeiuserInfoService{
	@Autowired
	WeiuserMapper weiuserMapper;
	
	@Override
	public int insert(Weiuser record) {
		try {
			String nickName=URLEncoder.encode(record.getNickname(), "utf-8");
			record.setNickname(nickName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return weiuserMapper.insert(record);
	}

	@Override
	public Weiuser selectByPrimaryKey(String openid) {
		Weiuser weiuser= weiuserMapper.selectByOPenid(openid);
		if(weiuser!=null) {
			String nickname=null;
			try {
				nickname = URLDecoder.decode(weiuser.getNickname(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			weiuser.setNickname(nickname);
		}
		
		return weiuser ;
	}

	@Override
	public List<Weiuser> selectWeiuserToSendMessage() {
		
		return weiuserMapper.selectWeiuserToSendMessage();
	}

	@Override
	public List<WeiUserOA> selectWeiuserRank() {
		List<WeiUserOA>	list=weiuserMapper.selectWeiuserRank();
		for (WeiUserOA weiUserOA : list) {
			if(weiUserOA.getNickname()!=null) {
				String newName=null;
				try {
					newName = URLDecoder.decode(weiUserOA.getNickname(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				weiUserOA.setNickname(newName);
			}
		}
		return list ;
	}

	

}
