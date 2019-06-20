package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbSubjectscore;

/**
 * 科目成绩
 *
 */
public interface SubjectScoreService {
	/**把科目一的分数加入成绩表里
	 * @param stuId
	 * @param subId
	 * @param totalScore
	 * @return
	 */
	public boolean addSubject1Score(Integer stuId,Integer subId,Integer totalScore);
	/**查出该学员的目前所有科目成绩等信息
	 * @param stuId 学员id
	 * @return
	 */
	public List<TbSubjectscore> findSubjectScore(Integer stuId);
}
