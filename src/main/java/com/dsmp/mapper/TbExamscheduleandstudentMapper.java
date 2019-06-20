package com.dsmp.mapper;

import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbExamscheduleandstudent;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbExamscheduleandstudentMapper {
	/**查询学员目前所处科目的安排表(最新的那个安排表)）
	 * @param stuId 学员id
	 * @param subId 科目id
	 * @return
	 */
	public List<TbExamscheduleandstudent> findExamScheduleByStuIdAndSubId(Integer stuId,Integer subId);
}