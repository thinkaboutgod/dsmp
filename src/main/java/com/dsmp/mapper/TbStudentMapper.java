package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.Count;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbStudent;

import com.dsmp.pojo.TbStudent;

public interface TbStudentMapper {

	public List<Count> countStudentBySchool(String schId);//根据驾校查询近半年报名人数
	public List<Count> searchDate();//查询近六个月日期
	public List<Count> countStudentByDate(String month);//查询某一个月有人报名的驾校的人数
	public List<Count> countAllStudentByDate(String month);//查询某一个月所有驾校报名人数，没有的置零
	public List<Count> countStudentByMonth(String schId);//查询驾校近30天报名人数

	public List<TbStudent> selectStudentByCoachIdAndSubject(String coaId);//查询教练底下的科目二三学员
	
	//查询学员
	public TbStudent findStudentByAccountPwd(TbStudent tbStudent);
	//更新学员输错密码状态
	public void updateStudent(TbStudent tbStudent);
	//学员注册
	public void insertStudent(TbStudent tbStudent);
	//学员忘记密码
	public int updateStudentPwd(TbStudent tbStudent);
	//学员报名驾校信息录入
	public int updateStudentInfo(TbStudent tbStudent);
	
	public int changeStudentStateLock(int stuId,String state);
	public String findStudentImgByStuId(int stuId);//根据id查学员照片路径
	
	/**
	 * 	查询驾校名下的所有学员
	 * @param sBean
	 * @return
	 */
	public List<TbStudent> searchAllstudent(SearchBean sBean);
	
	/**更改学员当前科目状态
	 * @param stuId 学员id
	 * @param status （学员当前科目的状态）字段要更改成的状态
	 * @return
	 */
	public int updateSubjectStatus(Integer stuId, String status);
	/**科目一正式考试通过，把学员的科目预约状态由"已预约"改成"未预约",科目1改成2
	 * @param stuId 学员id
	 * @param subId 科目id
	 * @return
	 */
	public int updateSubjectStatusAndSubId(Integer stuId,String status,Integer subId);
	/**根据学员id查学员
	 * @param stuId 学员id
	 * @return
	 */
	public TbStudent findStuById(Integer stuId);
	/**根据学员id查学员(包括驾校，科目，教练等详细信息)
	 * @param stuId 学员id
	 * @return
	 */
	public TbStudent findStuDetailById(Integer stuId);
	/**
	 * 	修改学员状态
	 * @param stuId
	 * @param state
	 * @return
	 */
	public int changeStudentState(int stuId,String state);//修改学员账号状态
	
	/**
	 * 	驾校添加学员
	 * @param student
	 * @return
	 */
	public int addStudent(TbStudent student);
	
	/**
	 *	 审核学员
	 * @param student
	 * @return
	 */
	public int checkStudent(TbStudent student);
	
	/**
	 * 	查询学生成绩
	 * @param schId
	 */
	public List<TbStudent> selectStudentScore(int schId);
	
	/**
	 * 	根据成绩变更学生的状态
	 * @param student
	 * @return
	 */
	public int updateStudentWithScore(TbStudent student);
	
	/**
	 *	 统计教练名下的学员数量
	 * @param coaId
	 * @return
	 */
	public List<Count> countStudentByCoach(String coaId);
	
}