package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbExamschedule;

public interface ExamScheduleService {
	public List<TbExamschedule> findExamScheduleByStuId(Integer stuId);
}
