package com.dsmp.mapper;

import com.dsmp.pojo.TbSubjectscore;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSubjectscoreMapper {
	public int addSubject1Score(Integer stuId,Integer subId,Integer totalScore);
	/**查出该学员的目前所有科目成绩等信息
	 * @param stuId 学员id
	 * @return
	 */
	public List<TbSubjectscore> findSubjectScore(Integer stuId);
}