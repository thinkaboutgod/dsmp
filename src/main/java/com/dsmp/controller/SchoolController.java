package com.dsmp.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.TbCoach;
import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.mapper.TbSchoolMapper;

import com.dsmp.pojo.TbExamschedule;

import com.dsmp.pojo.TbSchool;
import com.dsmp.service.CoachService;
import com.dsmp.service.PlateformService;
import com.dsmp.service.SchoolService;
import com.dsmp.utils.GsonUtils;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
@RequestMapping("school")
public class SchoolController {

	@Autowired private SchoolService schoolService;		

	@Autowired private CoachService coachService;

	@Autowired private PlateformService plateformServiceImpl;

	@Autowired
	private TbParameterMapper tbParameterMapper;

	@Autowired private TbSchoolMapper tbSchoolMapper;

	@RequestMapping("/selectCoach")
	public @ResponseBody List<TbCoach> selectCoach(Integer selectSchool){
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
	//主页跳转驾校页面
	@RequestMapping("/allSchoolPage")
	public ModelAndView getAllCoachPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/allschool");
		return mav;
	}
	
	//获取驾校集合
	@RequestMapping("/selectAllSchool")
	public @ResponseBody List<TbSchool> getSchoolByStauts(){
		List<TbSchool> schList = tbSchoolMapper.selectAllSchoolBySignUpStatus("允许报名");
		return schList;
	}
	
	//搜索驾校
	@RequestMapping("/selectSchoolByNamePage")
	public ModelAndView getSchoolByNamePage(String q) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("schName",q);
		mav.setViewName("client/school");
		return mav;
	}	
	//获取搜索结果集合
	@RequestMapping("/selectSchoolByName")
	public @ResponseBody List<TbSchool> getSchoolByName(String schName){
		List<TbSchool> schList = tbSchoolMapper.selectSchoolByName(schName);
		return schList;
	}	
	//驾校入驻手机验证码验证
	@RequestMapping("/verifyCode")
	public @ResponseBody MyResult getVerifyCode(HttpServletRequest request,String phone,String verifyCode) {
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
			String filePath = tbParameterMapper.selectParamter("系统文件存储路径");//获取系统文件储存路径
			// 上传文件路径
			String path = filePath+"/images/school/";
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
			result = schoolService.insertSchoolInfo(phone, password, sch_creditcode, sch_name, sch_type, sch_address, sch_bossname, sch_registerCapital, sch_introduce, sch_charge, fileName);
		} else {
			result.setMyresult("fileErr");
		}
		
		return result;
	}

	
	//驾校信息页
	@RequestMapping("/schoolInfo")
	public ModelAndView getSchoolInfoPage(Integer schId) {
		ModelAndView mav = new ModelAndView();		
		TbSchool tbSchool = tbSchoolMapper.findSchoolBySchId(schId);
		List<TbCoach> coaList = coachService.selectCoach(schId);
		mav.addObject("tbSchool",tbSchool);
		mav.addObject("coaList",coaList);
		mav.setViewName("client/school_info");
		return mav;
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


	/**
	 * 	进入考试安排界面
	 * @param role_id
	 * @return
	 */
	@RequestMapping(value = "toschool_examarrangement")
	public String toExamArrangement() {
		return "back/school_examarrangement";
	}

	/**
	 * 	驾校的所有考试安排，按照时间顺序排序
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selectExamschedule.action")
	public @ResponseBody Map<String, List<TbExamschedule>> selectExamschedule(HttpServletRequest request) {
		
		Map<String, List<TbExamschedule>> examscheduleMap = new HashMap<>();
		List<TbExamschedule> examschedule = schoolService.selectExamschedule(request);// 驾校id从session域中的教练信息中拿
		examscheduleMap.put("data", examschedule);

		return examscheduleMap;
	}

	/**
	 * 	新增考试安排
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addExamschedule")
	public @ResponseBody MyResult addExamschedule(HttpServletRequest request) {
		return schoolService.addExamschedule(request);
	}
	
	/**
	 * 	进入成绩管理界面
	 * @param role_id
	 * @return
	 */
	@RequestMapping(value = "toschool_studentscore")
	public String toStudentScore() {
		return "back/school_studentscore";
	}
	
	/**
	 * 	录入学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addScore")
	public @ResponseBody MyResult addScore(HttpServletRequest request) {
		return  schoolService.addScore(request);
	}
	
	/**
	 * 	修改学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateScore")
	public @ResponseBody MyResult updateScore(HttpServletRequest request) {
		return  schoolService.updateScore(request);
	}
	
	/**
	 * 	跳转到学员统计页面
	 * @return
	 */
	@RequestMapping(value = "toschool_StudentCount.action")
	public ModelAndView toStudentCount(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Integer schId =  (Integer) request.getSession().getAttribute("schId");
		List<TbCoach> listCoach = schoolService.selectCoachBySchId(schId);// 查询所有驾校
		List<Count> dateList = schoolService.searchDate();// 查询近六个月月份
		mav.addObject("coaList", listCoach);
		mav.addObject("dateList", dateList);
		mav.setViewName("back/school_studentcount");
		return mav;
	}
	
	// 按照驾校统计近半年报名学员人数
	@RequestMapping(value = "countStudentByCoach.action")
	public @ResponseBody List<Count> countStudentByCoach(String coaId, String dateId) {
		
		List<Count> cList = schoolService.countStudent(coaId, dateId);

		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).getData() == null) {
				cList.get(i).setData("0");
			}
		}
		return cList;
	}
	
	// 按照驾校查询某一个月有人报名的驾校的报名人数
	@RequestMapping(value = "countStudentByDate.action")
	public @ResponseBody Map<String, List<Count>> countStudentByDate(String month) {
		List<Count> cList = schoolService.countStudentByDate(month);
		Map<String, List<Count>> map = new HashMap<>();
		map.put("data", cList);
		return map;
	}

	// 
	@RequestMapping(value = "countAllStudentByDate.action")
	public @ResponseBody List<Count> countAllStudentByDate(String month,String schId) {
		List<Count> cList = schoolService.countAllStudentByDate(month,schId);
		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).getData() == null) {
				cList.get(i).setData("0");
			}
		}
		return cList;
	}
	
	
	
}


