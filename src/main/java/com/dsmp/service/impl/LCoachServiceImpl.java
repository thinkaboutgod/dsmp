package com.dsmp.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.LCoachMapper;
import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.mapper.TbStudyrecordMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.service.LCoachService;

@Service
public class LCoachServiceImpl implements LCoachService {
	@Autowired private HttpSession session;
	@Autowired private LCoachMapper lCoachMapper;
	@Autowired private TbStudentMapper tbStudentMapper;
	@Autowired private TbStudyrecordMapper tbStudyrecordMapper;
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

	//查询教练底下科目二三学员
	@Override
	public List<TbStudent> searchSubjectStudent() {
//		String caoId = ((TbCoach)session.getAttribute("coach")).getCoaId().toString();
		return tbStudentMapper.selectStudentByCoachIdAndSubject("1");
	}

	@Override
	public MyResult countTimeByStuIdAndSubject(String stuId,String subId) {
		Double sum  = tbStudyrecordMapper.countTimeByStuIdAndSubject(stuId,subId);
		MyResult myResult = new MyResult();
		if (null==sum ) {
			sum=0.0;
		}
		myResult.setSum(sum);
		return myResult;
	}
}
