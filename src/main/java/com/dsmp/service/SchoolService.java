package com.dsmp.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbSchool;


public interface SchoolService {

	
	//驾校入驻信息录入
	public MyResult insertSchoolInfo(String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge,String fileName);

	/**
	 * 	查看驾校的考场记录
	 * @param request
	 * @return
	 */
	public List<TbExamschedule> selectExamschedule(HttpServletRequest request);


	/**
	 *	 新增考场
	 * @param request
	 * @return
	 */
	public MyResult addExamschedule(HttpServletRequest request);

	/**
	 * 	录入学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	public MyResult addScore(HttpServletRequest request);

	/**
	 * 	修改学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	public MyResult updateScore(HttpServletRequest request);

	//驾校条件查找
	public List<TbSchool> searchSchool(HttpServletRequest request);
	//禁止、恢复学员预约 
	public void updateSignupstatus(HttpServletRequest request);
	//禁止恢复驾校运营状态
	public void updateOperativestatus(HttpServletRequest request);
	//查找未审核驾校
	public List<TbSchool> findNotauditSchool();
	//驾校审核状态更改
	public String updateAudit(HttpServletRequest request); 
	//驾校头像
	public String selectHeadportrait(HttpServletRequest request);
	//驾校具体信息
	public TbSchool selectSchoolByid(int schid);
	//驾校申诉内容插入
	public int  insertThecomplaintcontent(HttpServletRequest request);
	//查找申诉内容
	public List<TbAppeal> selectReply(HttpServletRequest request);


	//查询允许报名和运营的所有驾校
	public List<TbSchool> selectAllSchoolForAdvertise();

	

	public List<Count> searchDate();

	public List<Count> countStudentByDate(String month, String schId);

	public List<Count> countStudent(String coaId, String dateId);

	List<Count> countAllStudentByDate(String month, String schId);

	public List<TbCoach> selectCoachBySchId(Integer schId);

}
