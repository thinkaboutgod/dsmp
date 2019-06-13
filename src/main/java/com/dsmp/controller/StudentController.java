package com.dsmp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.StudentService;
import com.dsmp.utils.GsonUtils;
import com.zhenzi.sms.ZhenziSmsClient;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired private MyResult myResult;
	
	//主页跳登录页
	@RequestMapping("/login")
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/login");
		return mav;
	}
	//主页跳到注册页
	@RequestMapping("/register")
	public ModelAndView getRegisterPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/register");
		return mav;
	}
	
	
	@RequestMapping(value="toschool_student")
	public String toSchoolCoach(HttpSession session) {
		session.setAttribute("schId", 1);
		return "back/school_student";
	}
	
	/**
	 *	 查询所有学生
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchAllStudent.action")
	public @ResponseBody Map<String, List<TbStudent>> searchAllstudent(HttpServletRequest request) {
		List<TbStudent> list = studentService.searchAllstudent(request);
		Map<String, List<TbStudent>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}
	
	
	
	//用户登录判断
	@RequestMapping("/studentLogin")
	public @ResponseBody MyResult studentLogin(HttpSession session ,String account,String password,String role) {
		System.out.println("账号："+account);
		System.out.println("密码："+password);
		System.out.println("角色ID："+role);
		MyResult result = null;
		switch(role){
		case "5":
			result = studentService.studentLogin(session, account, password,role);	
			break;
		case "4":
			result = studentService.coachLogin(session, account, password, role);
			break;
		case "3":
			result = studentService.schoolLogin(session, account, password, role);
			break;
		}
		return result;
	}
	
	//获取手机验证码
	@RequestMapping(value="/verificationCode.action")
	public @ResponseBody MyResult registerCode(HttpServletRequest request,HttpServletResponse response,String mobile) {
		MyResult myResult = null;
		try {
			System.out.println("验证的手机号："+mobile);
			//生成4位验证码
			String verifyCode = String.valueOf(new Random().nextInt(8999) + 1000);
			//发送短信
			ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "101707",
					"dcff2073-d368-4c5a-9244-33ef7902dbf9");
			String result = client.send(mobile, "您的验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次!");			
		    myResult = GsonUtils.fromJson(result, MyResult.class);	
		    System.out.println("验证码："+verifyCode);
//		    verifyCode = "1234";
//			myResult.setCode(0);
			System.out.println(myResult.getCode());
			//将验证码存到session中,同时存入创建时间
			Map<String, String> map = new HashMap<>();
			map.put("mobile", mobile);
			map.put("verifyCode", verifyCode);
			map.put("createTime",String.valueOf(System.currentTimeMillis()) );
			// 将验证码存入SESSION
			request.getSession().setAttribute("verifyCode", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myResult;		
	}
	
	//用户登录判断
	@RequestMapping("/studentRegister")
	public @ResponseBody MyResult studentRegister(HttpServletRequest request,String phone,String pwd,String verifyCode) {
		System.out.println("账号："+phone);
		System.out.println("密码："+pwd);
		MyResult result = studentService.studentRegister(request, phone, pwd, verifyCode);		
		System.out.println(result.getMyresult());
		return result;
	}
	
	
	@RequestMapping(value = "changeStudentState.action")
	public @ResponseBody MyResult changeStudentState(HttpServletRequest request) {
		myResult = studentService.changeStudentState(request, myResult);
		return myResult;
	}
	
}
