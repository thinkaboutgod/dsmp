package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.Count;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbSubjectscore;

public interface TbSchoolMapper {
	
	
	//驾校登录验证
	public TbSchool getSchool(TbSchool tbSchool);
	
	public List<TbSchool> selectAllSchoolBySignUpStatus(String string);
	//驾校入驻信息录入
	public int insertSchoolInfo(TbSchool tbSchool);
	public TbSchool getSchoolByCreditcode(TbSchool tbSchool);
	
	//驾校排行
	public List<Count> getSchoolRanking();
	
	public List<TbSchool> selectAllSchool();

	public TbSchool findSchoolBySchId(Integer schId);


	/**
	 * 	查询考试记录
	 * @param schId
	 * @param subId
	 * @return
	 */
	public List<TbExamschedule> selectExamschedule(String schId, String subId);

	/**
	 * 	新增考试
	 * @param examschedule
	 * @return
	 */
	public int addExamschedule(TbExamschedule examschedule);

	/**
	 * 	录入学员科目二和科目三的成绩
	 * @param score
	 * @return
	 */
	public int addScore(TbSubjectscore score);

	/**
	 * 	修改学员科目二和科目三的成绩
	 * @param score
	 * @return
	 */
	public int updateScore(TbSubjectscore tbScore);
	
	
	
	
	
}