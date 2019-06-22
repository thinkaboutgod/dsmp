package com.dsmp.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.ResultMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.CoachService;
import com.dsmp.service.PlateformService;
import com.dsmp.service.CoachService;
import com.dsmp.service.SchoolService;
import com.dsmp.utils.GsonUtils;
import com.dsmp.utils.Md5Tools;

@Controller
@RequestMapping("school")
public class SchoolController {

	@Autowired private SchoolService schoolService;		
	@Autowired private CoachService coachService;
	@Autowired private PlateformService plateformServiceImpl;

	@RequestMapping("/selectCoach")
	public @ResponseBody List<TbCoach> selectCoach(Integer selectSchool){
		System.out.println(selectSchool);
		List<TbCoach> coaList = coachService.selectCoach(selectSchool);
		return coaList;
	}
	//主页跳转驾校入驻页面
	@RequestMapping("/schoolEnterPage")
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/schoolenter");
		return mav;
	}	
	//驾校入驻手机验证码验证
	@RequestMapping("/verifyCode")
	public @ResponseBody MyResult getVerifyCode(HttpServletRequest request,String phone,String verifyCode) {
		System.out.println("入驻输入手机号：" + phone);
		System.out.println("入驻输入的验证码：" + verifyCode);
		MyResult result = new MyResult();
		Map<String, String> map = (Map<String, String>) request.getSession().getAttribute("verifyCode");
		if (map == null) {
			result.setMyresult("codeErr");
		}
		if (!map.get("mobile").equals(phone)) {
			result.setMyresult("phoneErr");
		}
		if (!map.get("verifyCode").equals(verifyCode)) {
			result.setMyresult("codeErr");
		}
		if ((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) > 1000 * 60 * 5) {
			result.setMyresult("pastDue");
		}
		if (map != null && map.get("mobile").equals(phone) && map.get("verifyCode").equals(verifyCode)
				&& ((System.currentTimeMillis() - Long.valueOf(map.get("createTime"))) < 1000 * 60 * 5)) {
			
		  result.setMyresult("success");
		}
		return result;
	}
	
	//驾校入驻信息录入
	@RequestMapping(value="/schoolEnter",method = RequestMethod.POST)
	public @ResponseBody MyResult getHomePage(HttpServletRequest request,MultipartFile file,
			String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge) throws IllegalStateException, IOException{
		MyResult result = null;
		if (!file.isEmpty()) {
			// 上传文件路径
			String path = request.getServletContext().getRealPath("/images/school/");
			System.out.println(path);
			// 上传文件名
			String filename = file.getOriginalFilename();
			String fileTyle=filename.substring(filename.lastIndexOf("."),filename.length());
			String fileName = phone+fileTyle;
			File filepath = new File(path, fileName);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中
			file.transferTo(new File(path + File.separator + fileName));
			// 输出文件上传最终的路径 测试查看
			System.out.println(path + File.separator + fileName);
			result = schoolService.insertSchoolInfo(phone, password, sch_creditcode, sch_name, sch_type, sch_address, sch_bossname, sch_registerCapital, sch_introduce, sch_charge, fileName);
		} else {
			result.setMyresult("fileErr");
		}
		System.out.println("最终返回的结果："+result.getMyresult());
		return result;
	}
	//申诉渠道界面
	@RequestMapping("/schoolThecomplaint.action")
	public ModelAndView getThecomplaintinterface(HttpServletRequest request) {
//		String schId=(String) request.getSession().getAttribute("schId");
		TbSchool schoolmsg=schoolService.selectSchoolByid(1);
		String schSignupstatus=schoolmsg.getSchSignupstatus();
		if(schSignupstatus.equals("允许报名")) {
			schSignupstatus="正常运营";
		}else if(schSignupstatus.equals("禁止报名")) {
			schSignupstatus="学员报名被禁";
		}
		request.setAttribute("schAccount",schoolmsg.getSchAccount());
		request.setAttribute("schName", schoolmsg.getSchName());
		request.setAttribute("schBossname", schoolmsg.getSchBossname());
		request.setAttribute("schSignupstatus", schSignupstatus);
		request.setAttribute("schHeadimg", schoolmsg.getSchHeadimg());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("back/school_complaint");
		return mav;
	}
	@RequestMapping(value="/school/thecomplaintcontent",method = RequestMethod.POST)
	public @ResponseBody String theComplaintcontent(HttpServletRequest request) {
		int insertrusult=schoolService.insertThecomplaintcontent(request);
		String res="";
		if(insertrusult==1) {
			res="success";
		}else {
			res="fail";
		}
		String result=GsonUtils.toJson(res);
		return result;		
	}
	@RequestMapping(value="/school/activistreply",method = RequestMethod.POST)
	public @ResponseBody Map<String,List<TbAppeal>> getActivistreply(HttpServletRequest request){
		List<TbAppeal> appeallist=schoolService.selectReply(request);
		Map<String,List<TbAppeal>> appealmap=new HashMap<>();
		appealmap.put("data", appeallist);		
		return appealmap;		
	}
 }

