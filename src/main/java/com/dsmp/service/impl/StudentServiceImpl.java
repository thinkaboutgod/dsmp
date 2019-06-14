package com.dsmp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.StudentService;
import com.dsmp.utils.Md5Tools;

@Service
public class StudentServiceImpl implements StudentService {
	

	@Autowired private TbStudentMapper tbStudentMapper;
	@Autowired private TbCoachMapper tbCoachMapper;
	@Autowired private TbSchoolMapper tbSchoolMapper;
	//学员登录验证	
	@Override
	public MyResult studentLogin(HttpSession session, String account, String password,String role) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stuErrtime1 = sdf1.format(new java.util.Date());
		String md5Password = Md5Tools.getMd5(password);
		System.out.println("MD5加密后的密码："+md5Password);
		System.out.println("当前系统时间："+stuErrtime1);
		MyResult result = new MyResult();
		TbStudent student = new  TbStudent();
		student.setStuAccount(account);
		TbStudent tbStudent = tbStudentMapper.findStudentByAccountPwd(student);
		
		if(tbStudent != null) {
			if(null!=tbStudent.getStuErrtime() && !tbStudent.getStuErrtime().equals("")) {
				try {
					Date now = sdf1.parse(stuErrtime1);
					Date old = tbStudent.getStuErrtime();
					long time1 = now.getTime();  
			        long time2 = old.getTime(); 
			        long time3 = time1-time2;
			        System.out.println("距离解锁还有;"+(300000-time3));
					if(time3>=300000) {
						System.out.println("满足要求解绑");
						tbStudent.setStuErrcount(0);
						tbStudent.setStuErrtime(null);
						tbStudent.setStuStatus("启用");
						tbStudentMapper.updateStudent(tbStudent);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(tbStudent.getStuPassword().equals(md5Password)) {
				if(tbStudent.getStuStatus().equals("启用")) {
					session.setAttribute("student", tbStudent);
					tbStudent.setStuErrcount(0);
					tbStudent.setStuErrtime(null);
					tbStudent.setStuStatus("启用");
					tbStudentMapper.updateStudent(tbStudent);
					System.out.println("用户名："+student.getStuName());
					result.setMyresult("success");
				}else if(tbStudent.getStuStatus().equals("锁定")){
					result.setMyresult("lock");
				}else {								
					result.setMyresult("forbidden");					
				}
			}else {
				if(tbStudent.getStuStatus().equals("锁定")) {
					result.setMyresult("lock");
				}else {
					int stuErrcount = tbStudent.getStuErrcount()+1;
					System.out.println("错误次数："+stuErrcount);
					if(stuErrcount >= 3) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String stuErrtime2 = sdf.format(new java.util.Date());
						Date date;
						try {
							date = sdf.parse(stuErrtime2);
							tbStudent.setStuErrcount(stuErrcount);
							tbStudent.setStuErrtime(date);
							tbStudent.setStuStatus("锁定");
							tbStudentMapper.updateStudent(tbStudent);
							result.setMyresult("lock");
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else {						
						tbStudent.setStuErrcount(stuErrcount);
						tbStudent.setStuErrtime(null);
						tbStudent.setStuStatus("启用");
						tbStudentMapper.updateStudent(tbStudent);
						result.setMyresult("pwdError");
						result.setErrCount(3-stuErrcount);
					}
				}
								
			}
			
		}else {
			result.setMyresult("failed");
		}
		return result;
	}
	//学员注册（//手机验证码验证）
	@Override
	public MyResult studentRegister(HttpServletRequest request, String stuAccount, String stuPassword,
			String verifyCode) {
		System.out.println("注册手机号："+stuAccount);
		System.out.println("注册密码："+stuPassword);
		MyResult result = new MyResult();
		Map<String, String> map= (Map<String, String>) request.getSession().getAttribute("verifyCode");
		if(map == null){		
			result.setMyresult("codeErr");
		}
		if(!map.get("mobile").equals(stuAccount)){
			result.setMyresult("phoneErr");
		}
		if(!map.get("verifyCode").equals(verifyCode)){
			result.setMyresult("codeErr");
		}
		if((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) > 1000 * 60 * 5){
			result.setMyresult("pastDue");
		}
		if(map != null && map.get("mobile").equals(stuAccount) && map.get("verifyCode").equals(verifyCode) && ((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) < 1000 * 60 * 5)) {
			TbStudent student = new  TbStudent();
			student.setStuAccount(stuAccount);
			TbStudent tbStudent = tbStudentMapper.findStudentByAccountPwd(student);
			if(tbStudent != null) {
				result.setMyresult("already");
			}else {
				String md5Password = Md5Tools.getMd5(stuPassword);
				student.setStuPassword(md5Password);
				student.setStuStatus("启用");
				tbStudentMapper.insertStudent(student);
				result.setMyresult("success");
			}			
		}
		return result;
	}
	//教练登陆
	@Override
	public MyResult coachLogin(HttpSession session, String account, String password, String role) {
		MyResult result = new MyResult();
		String md5Password = Md5Tools.getMd5(password);
		System.out.println("MD5加密后的密码："+md5Password);
		TbCoach coach = new TbCoach();
		coach.setCoaAccount(account);
		TbCoach tbCoach = tbCoachMapper.getCoach(coach);
		if(tbCoach != null) {
			if(tbCoach.getCoaPassword().equals(md5Password)) {
				session.setAttribute("coach", tbCoach);
				result.setMyresult("success");
			}else {
				result.setMyresult("passErr");
			}
		}else {
			result.setMyresult("failed");
		}
		return result;
	}
	//驾校登录
	@Override
	public MyResult schoolLogin(HttpSession session, String account, String password, String role) {
		MyResult result = new MyResult();
		String md5Password = Md5Tools.getMd5(password);
		System.out.println("MD5加密后的密码："+md5Password);
		TbSchool school = new TbSchool();
		school.setSchAccount(account);
		TbSchool tbSchool = tbSchoolMapper.getSchool(school);
		if(tbSchool != null) {
			if(tbSchool.getSchPassword().equals(md5Password)) {
				session.setAttribute("school", tbSchool);
				result.setMyresult("success");
			}else {
				result.setMyresult("passErr");
			}
		}else {
			result.setMyresult("failed");
		}
		return result;
	}
	
	//学员在线报名
	
	
	@Override
	public List<TbStudent> selectStusByCoaId(Integer coaId) {
		
		return null;
	}

	@Override
	public List<TbStudent> selectStusBySchId(Integer schId) {
		
		return null;
	}

	@Override
	public List<TbStudent> searchAllstudent(HttpServletRequest request) {
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String schId = request.getParameter("schId");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		if (account.trim().equals("")) {
			account = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if (schId.trim().equals("")) {
			schId = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}

		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		SearchBean sBean = new SearchBean();
		sBean.setAccount(account);
		sBean.setName(name);
		sBean.setSchId(schId);
		sBean.setBeginTime(beginTime);
		sBean.setEndTime(endTime);
		return tbStudentMapper.searchAllstudent(sBean);
	}
	
	//学员在线报名实现类
	@Override
	public MyResult studentApply(HttpServletRequest request,HttpSession session,String filename, String name, String idCard, String address, String sex,
			Integer school, Integer coach, String code, String phone) {
		System.out.println("报名手机号："+phone);
		System.out.println("输入验证码："+code);
		MyResult result = new MyResult();
		Map<String, String> map= (Map<String, String>) request.getSession().getAttribute("verifyCode");
		if(map == null){		
			result.setMyresult("codeErr");
		}
		if(!map.get("mobile").equals(phone)){
			result.setMyresult("phoneErr");
		}
		if(!map.get("verifyCode").equals(code)){
			result.setMyresult("codeErr");
		}
		if((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) > 1000 * 60 * 5){
			result.setMyresult("pastDue");
		}
		if(map != null && map.get("mobile").equals(phone) && map.get("verifyCode").equals(code) && ((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) < 1000 * 60 * 5)) {
			TbStudent student = new TbStudent();
			student.setStuAccount(phone);
			TbStudent tbStudent = tbStudentMapper.findStudentByAccountPwd(student);
			TbStudent tStudent =(TbStudent) request.getSession().getAttribute("student");
			System.out.println("session学员ID："+tStudent.getStuId());
			if(tbStudent.getSchId() != null) {
				result.setMyresult("already");
			}else {
				System.out.println("学员照片："+filename);
				System.out.println("地址："+address);
				student.setStuId(tStudent.getStuId());
				student.setCoaId(coach);
				student.setSchId(school);	
				student.setStuName(name);
				student.setStuSex(sex);
				student.setStuAddress(address);
				student.setStuIdcard(idCard);
				student.setStuImg(filename);
				student.setStuErrcount(0);
				student.setStuStatus("启用");
				student.setStuVerifystatus("未审核");
				System.out.println("用户地址："+student.getStuAddress());
				session.setAttribute("students", student);
				result.setMyresult("success");
			}						
		}
		return result;

	}
	
	//学员忘记密码
	@Override
	public MyResult changePwd(HttpServletRequest request,String newPassword, String phone,String code) {
		System.out.println("报名手机号："+phone);
		System.out.println("输入验证码："+code);
		MyResult result = new MyResult();
		Map<String, String> map= (Map<String, String>) request.getSession().getAttribute("verifyCode");
		if(map == null){		
			result.setMyresult("codeErr");
		}
		if(!map.get("mobile").equals(phone)){
			result.setMyresult("phoneErr");
		}
		if(!map.get("verifyCode").equals(code)){
			result.setMyresult("codeErr");
		}
		if((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) > 1000 * 60 * 5){
			result.setMyresult("pastDue");
		}
		if(map != null && map.get("mobile").equals(phone) && map.get("verifyCode").equals(code) && ((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) < 1000 * 60 * 5)) {
			TbStudent student = new TbStudent();
			String md5Password = Md5Tools.getMd5(newPassword);
			student.setStuAccount(phone);
			student.setStuPassword(md5Password);
			int res = tbStudentMapper.updateStudentPwd(student);
			System.err.println("修改结果："+res);
			if(res==1) {
				result.setMyresult("success");
			}else {
				result.setMyresult("failed");
			}	
		}		
		return result;
	}

	
}
