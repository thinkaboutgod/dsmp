package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.CoachService;

@Service
public class CoachServiceImpl implements CoachService {

	@Autowired private TbCoachMapper tbCoachMapper;
	
	@Override
	public List<TbCoach> selectCoas() {
		
		return tbCoachMapper.selectCoas();

	}
	
	@Override
	public List<TbStudent> belongtococh(int stuid) {
		List <TbStudent> stuentlist=tbCoachMapper.belongtocoach(stuid);
		return stuentlist;
	}
	
	@Override
	public TbCoach selectcoach(int coaid) {
		TbCoach bbCoach=tbCoachMapper.selectCoach();
		return null;

	}
}
