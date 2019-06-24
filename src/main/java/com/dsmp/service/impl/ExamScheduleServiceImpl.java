package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbExamscheduleandstudentMapper;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbExamscheduleandstudent;
import com.dsmp.service.ExamScheduleService;
@Service
public class ExamScheduleServiceImpl implements ExamScheduleService {
	@Autowired
	private TbExamscheduleandstudentMapper examscheduleandstudentMapper;
	
	/**查询学员目前所处科目的安排表(最新的那个安排表)）
	 * @param stuId 学员id
	 * @param subId 科目id
	 * @return
	 */
	@Override
	public List<TbExamscheduleandstudent> findExamScheduleByStuIdAndSubId(Integer stuId,Integer subId) {
		List<TbExamscheduleandstudent> list = examscheduleandstudentMapper.findExamScheduleByStuIdAndSubId(stuId, subId);
		return list;
	}

}
