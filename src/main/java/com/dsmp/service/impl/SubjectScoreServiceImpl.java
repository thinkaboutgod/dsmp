package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbSubjectscoreMapper;
import com.dsmp.pojo.TbSubjectscore;
import com.dsmp.service.SubjectScoreService;
@Service
public class SubjectScoreServiceImpl implements SubjectScoreService {
	@Autowired
	private TbSubjectscoreMapper subjectscoreMapper;
	@Override
	public boolean addSubject1Score(Integer stuId, Integer subId, Integer totalScore) {
		boolean flag = false;
		int res = subjectscoreMapper.addSubject1Score(stuId,subId,totalScore);
		if(res==1) {
			flag = true;
		}
		return flag;
	}
	/**查出该学员的目前所有科目成绩等信息
	 * @param stuId 学员id
	 * @return
	 */
	@Override
	public List<TbSubjectscore> findSubjectScore(Integer stuId) {
		List<TbSubjectscore> list = subjectscoreMapper.findSubjectScore(stuId);
/*		for (TbSubjectscore tbSubjectscore : list) {
			
			System.out.println("科目："+tbSubjectscore.getTbSubject().getSubName());
			System.out.println("学员："+tbSubjectscore.getTbStudent().getStuName());
		}*/
		return list;
	}

}
