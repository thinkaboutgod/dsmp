package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.LCoachMapper;
import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.LCoachService;

@Service
public class LCoachServiceImpl implements LCoachService {

	@Autowired private LCoachMapper lCoachMapper;
	
	@Override
	public List<TbCoach> selectCoas() {
		
		return lCoachMapper.selectCoas();

	}
	
	@Override
	public List<TbStudent> belongtococh(int stuid) {
		List <TbStudent> stuentlist=lCoachMapper.belongtocoach(stuid);
		return stuentlist;
	}
	
	@Override
	public TbCoach selectcoach(int coaid) {
		TbCoach bbCoach=lCoachMapper.selectCoach();
		return null;
	}
}
