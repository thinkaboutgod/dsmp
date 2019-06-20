package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbExamscheduleandstudent;

/**
 * 考试安排
 *
 */
public interface ExamScheduleService {
	/**查询学员目前所处科目的安排表(最新的那个安排表)）
	 * @param stuId 学员id
	 * @param subId 科目id
	 * @return
	 */
	public List<TbExamscheduleandstudent> findExamScheduleByStuIdAndSubId(Integer stuId,Integer subId);
}
