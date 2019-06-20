package com.dsmp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.TbStudent;

import com.dsmp.pojo.MyResult;

public interface StudentService {
	//学员登录验证
	public MyResult studentLogin(HttpSession session,String account,String password,String role); 
	//教练登录验证
	public MyResult coachLogin(HttpSession session,String account,String password,String role); 
	//驾校登录验证
	public MyResult schoolLogin(HttpSession session,String account,String password,String role); 
	//学员注册
	public MyResult studentRegister(HttpServletRequest request,String stuAccount,String stuPassword,String verifyCode);

	//学员在线报名
	public MyResult studentApply(HttpServletRequest request,HttpSession session,String filename,String name,
			String idCard,String address,String sex,Integer school,Integer coach,
			String code,String phone);
	//学员忘记密码
	public MyResult changePwd(HttpServletRequest request,String newPassword,String phone,String code);
	/**
	 * 	查询所有学员
	 * @param request
	 * @return
	 */
	public List<TbStudent> searchAllstudent(HttpServletRequest request);
	
	/**
	 * 	修改学员状态
	 * @param request
	 * @param myResult
	 * @return
	 */
	public MyResult changeStudentState(HttpServletRequest request, MyResult myResult);
	
	/**更改学员当前科目状态
	 * @param stuId 学员id
	 * @param status （学员当前科目的状态）字段要更改成的状态
	 * @return
	 */
	public boolean updateSubjectStatus(Integer stuId,String status);
	/**科目一正式考试通过，把学员的科目预约状态由"已预约"改成"未预约",科目1改成2
	 * @param stuId 学员id
	 * @param subId 科目id
	 * @return
	 */
	public boolean updateSubjectStatusAndSubId(Integer stuId,String status,Integer subId);
	
	/**根据学员id查学员
	 * @param stuId 学员id
	 * @return
	 */
	public TbStudent findStuById(Integer stuId);
}
