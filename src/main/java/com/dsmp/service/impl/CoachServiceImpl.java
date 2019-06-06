package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.CoachMapper;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.CoachService;
@Service 
public class CoachServiceImpl implements CoachService {
	@Autowired private CoachMapper coacmapper;
	@Override
	public List<TbStudent> belongtococh(int stuid) {
		List <TbStudent> stuentlist=coacmapper.belongtocoach(stuid);
		return stuentlist;
	}
	@Override
	public TbCoach selectcoach(int coaid) {
		TbCoach bbCoach=coacmapper.selectCoach();
		return null;
	}

}
