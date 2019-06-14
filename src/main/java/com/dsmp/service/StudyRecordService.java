package com.dsmp.service;

import java.util.Map;

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
	/**计算当前总学时
	 * @param stuId
	 * @param subId
	 * @return
	 */
	public Integer sumTimeLength(Integer stuId,Integer subId);
}
