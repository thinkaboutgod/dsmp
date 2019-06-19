package com.dsmp.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.config.AlipayConfig;
import com.dsmp.mapper.TbCapitalrecordMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCapitalrecord;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.StudentService;
import com.dsmp.utils.GsonUtils;
import com.zhenzi.sms.ZhenziSmsClient;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private TbSchoolMapper tbMapper;
	@Autowired
	private TbStudentMapper tbStudentMapper;
	@Autowired
	private TbCapitalrecordMapper tbCapitalrecordMapper;	
	@Autowired private MyResult myResult;
	//主页跳登录页
	@RequestMapping("/login")
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/login");
		return mav;
	}
	//付款页跳转付款登录页
	@RequestMapping("/payment")
	public ModelAndView getPaymentPage(Integer schId) {
		System.out.println("报名的驾校ID"+schId);
		ModelAndView mav = new ModelAndView();
		TbSchool school = tbMapper.findSchoolBySchId(schId);
		mav.addObject("school",school);
		mav.setViewName("client/payment");
		return mav;
	}		
	//付款页跳转付款登录页
	@RequestMapping("/alipay")
	public ModelAndView getPayPage() {
		System.out.println("进来了");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/alipay.trade.page.pay");
		return mav;
	}	
	//付款成功跳回主页
	@RequestMapping("/main")
	public ModelAndView getMainPage(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		System.out.println("付款成功了");
		ModelAndView mav = new ModelAndView();
		
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			System.out.println("订单号："+out_trade_no);
			System.out.println("付款金额："+total_amount);
			
			TbStudent student = (TbStudent)request.getSession().getAttribute("students");
			System.out.println("学员ID："+student.getStuId());
			System.out.println("付款学员："+student.getStuName());
			System.out.println("地址："+student.getStuAddress());
			int res = tbStudentMapper.updateStudentInfo(student);
			if(res==1) {
				System.out.println("更新学员成功！");
				TbCapitalrecord tbCapitalrecord = new TbCapitalrecord();
				tbCapitalrecord.setSchId(student.getSchId());
				tbCapitalrecord.setStuId(student.getStuId());
				tbCapitalrecord.setCapMoney(Double.valueOf(total_amount));
				tbCapitalrecord.setCapFeetype("报名");
				tbCapitalrecord.setCapOrderNumber(out_trade_no);
				int res1 = tbCapitalrecordMapper.insertTbCapitalrecord(tbCapitalrecord);
				if(res1 == 1) {
					System.out.println("插入资金表成功");
				}else {
					System.out.println("插入资金表失败");
				}
			}else {
				System.out.println("更新学员失败！");
			}
		}else {
			System.out.println("失败了");
		}
		
		mav.setViewName("client/home");
		return mav;
	}		

	//登录跳转忘记密码页
	@RequestMapping("/changePwdPage")
	public ModelAndView getchangePwdPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/changepwd");
		return mav;
	}	
	//主页跳到注册页
	@RequestMapping("/register")
	public ModelAndView getRegisterPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/register");
		return mav;
	}
	//主页跳到注册页
	@RequestMapping("/apply")
	public ModelAndView getApplyPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/apply");
		String signUpStatus = "允许报名";
		List<TbSchool> schList = tbMapper.selectAllSchoolBySignUpStatus(signUpStatus);
		mav.addObject("schList",schList);
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
		System.out.println("登录最终结果："+result.getStauts());
		return result;
	}
	
	//获取手机验证码
	@RequestMapping(value="/verificationCode.action")
	public @ResponseBody MyResult registerCode(HttpServletRequest request,HttpServletResponse response,String mobile) {
		MyResult myResult = new MyResult();
		try {
			System.out.println("验证的手机号："+mobile);
			//生成4位验证码
			String verifyCode = String.valueOf(new Random().nextInt(8999) + 1000);
			//发送短信
//			ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "101707",
//					"dcff2073-d368-4c5a-9244-33ef7902dbf9");
//			String result = client.send(mobile, "您的验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次!");			
//		    myResult = GsonUtils.fromJson(result, MyResult.class);	
		    System.out.println("验证码："+verifyCode);
		    verifyCode = "1234";
			myResult.setCode(0);
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
	
	//用户注册判断
	@RequestMapping("/studentRegister")
	public @ResponseBody MyResult studentRegister(HttpServletRequest request,String phone,String pwd,String verifyCode) {
		System.out.println("账号："+phone);
		System.out.println("密码："+pwd);
		MyResult result = studentService.studentRegister(request, phone, pwd, verifyCode);		
		System.out.println(result.getMyresult());
		return result;
	}
	

	//用户在线报名判断
	@RequestMapping("/studentApply")
	public @ResponseBody MyResult studentApply(HttpServletRequest request,HttpSession session,
			@RequestParam("the_file")MultipartFile file,String filename,String name,
			String idCard,String address,String sex,Integer school,Integer coach,
			String code,String phone) throws IllegalStateException, IOException {		
		System.out.println(file.getOriginalFilename());
		System.out.println("驾校ID"+school);
		MyResult result = null;
		if (!file.isEmpty()) {
			// 上传文件路径
			String path = request.getServletContext().getRealPath("/images/student/");
			System.out.println(path);
			// 上传文件名
//			String filename = file.getOriginalFilename();
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中
			file.transferTo(new File(path + File.separator + filename));
			// 输出文件上传最终的路径 测试查看
			System.out.println(path + File.separator + filename);
			result = studentService.studentApply(request, session, filename, name, idCard, address, sex, school, coach, code, phone);
		} else {
			result.setMyresult("failed");
		}
		System.out.println("最终返回的结果："+result);
		return result;
	}
	
	//学员忘记密码
	@RequestMapping("/changePwd")
	public @ResponseBody MyResult changePwd(HttpServletRequest request,String newPassword,String phone,String verifyCode) {
		MyResult result = new MyResult();
		result = studentService.changePwd(request,newPassword, phone,verifyCode);
		return result;
	}
	
	@RequestMapping(value = "changeStudentState.action")
	public @ResponseBody MyResult changeStudentState(HttpServletRequest request) {
		myResult = studentService.changeStudentState(request, myResult);
		return myResult;
	}
	
}
