package com.dsmp.service;

public interface SubjectService {
    /**查出该科目总需要学时
     * @param subId 科目id
     * @return
     */
	public Double findNeedStudyTime(Integer subId);
}
