package com.dsmp.service;

import java.util.List;
import java.util.Map;

import com.dsmp.pojo.TbStudyrecord;

/**
 * 学习记录：插入开始学习时间点，计算学习时长等
 *
 */
public interface StudyRecordService {
	/**插入开始学习的时间点
	 * @param stuId 学员id
	 * @param subId 科目id
	 * @return true-插入成功，false-插入失败
	 */
	public boolean addStudyBeginTime(Integer stuId,Integer subId);
	/**插入结束学习的时间点
	 * @param stuId 学员id
	 * @param subId 科目id
	 * @return true-插入成功，false-插入失败
	 */
	public Map<String,Integer> addStudyEndTime(Integer stuId,Integer subId);
	/**计算当前总学时(某学员某科目当前完成总学时)
	 * @param stuId
	 * @param subId
	 * @return
	 */
	public int sumTimeLength(Integer stuId,Integer subId);
	/** 通过学员id和科目名称查询科目所有的学习记录
	 * @param stuId 学员id
	 * @param subName 科目名称
	 * @return 该科目所有的学习记录
	 */
	public List<TbStudyrecord> findStudyRecord(Integer stuId,String subName);
}
