package com.dsmp.mapper;

import java.util.Date;
import java.util.List;

import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.Count;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbSubjectscore;

public interface TbSchoolMapper {
	

	//所有驾校页面集合
	public List<TbSchool> selectAllSchoolBySignUpStatus(String schSignupstatus);
	
	//搜索驾校页面结果集合
	public List<TbSchool> selectSchoolByName(String schName);
	
	public List<TbStudent> selectStus();
	
	public List<TbCoach> selectCoas();
	
	public List<TbCar> selectCars();
	
	//驾校登录验证
	public TbSchool getSchool(TbSchool tbSchool);
	
	//驾校入驻信息录入
	public int insertSchoolInfo(TbSchool tbSchool);
	public TbSchool getSchoolByCreditcode(TbSchool tbSchool);
	
	//驾校按条件查找
	public List<TbSchool> selectSchool(String schName,String schAccount,String schBossname);
	//禁止、恢复学员预约 
	public void updateSignupstatus(String schSignupstatus,String schId);
	//禁止，恢复驾校运营状态
	public void updateOperativestatus(String schOperativestatus,String schId);
	//查找未审核驾校
	public List<TbSchool> selectNotauditSchool();
	//更改驾校审核状态
	public void updateAudit(String schid);
	//驾校申诉内容插入
	public int insertThecomplaintcontent(String schid,String content,Date appTime);
	//查询申诉内容
	public List<TbAppeal> selectReply(Integer schid);
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
	
	
	//查询允许报名和运营的所有驾校
	public List<TbSchool> selectAllSchoolForAdvertise();

	/**
	 * 	根据教练id统计名下学员
	 * @param coaId
	 * @return
	 */
	public List<Count> countStudentByCoach(String coaId);

	/**
	 * 	根据教练id统计名下学员
	 * @param coaId
	 * @return
	 */
	public List<Count> countStudentByMonth(String coaId);

	public List<Count> searchDate();

	/**
	 * 	统计驾校名下各个教练的名下学员
	 * @param month
	 * @param schId
	 * @return
	 */
	public List<Count> countAllStudentByDate(String month,String schId);

	/**
	 * 	统计驾校名下各个教练的名下学员
	 * @param month
	 * @param schId
	 * @return
	 */
	public List<Count> countStudentByDate(String month,String schId);
}