package com.dsmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbSubjectMapper;
import com.dsmp.service.SubjectService;
@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private TbSubjectMapper subjectMapper;
    /**查出该科目总需要学时
     * @param subId 科目id
     * @return
     */
	@Override
	public Double findNeedStudyTime(Integer subId) {
		Double needStyTime = subjectMapper.findNeedStudyTime(subId);
//		System.out.println("needStyTime-service:"+needStyTime);
//		 int i = (needStyTime*60*60).intValue();
//		Integer needTime = Integer.parseInt(new java.text.DecimalFormat("0").format(needStyTime*60*60));
		needStyTime=needStyTime*60*60;
		return needStyTime;
	}

}
