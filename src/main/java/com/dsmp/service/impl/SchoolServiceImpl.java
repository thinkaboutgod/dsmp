package com.dsmp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;

import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbSubjectscore;
import com.dsmp.service.SchoolService;

import com.dsmp.utils.GsonUtils;
import com.dsmp.utils.Md5Tools;

import sun.util.resources.cldr.aa.CalendarData_aa_ER;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired private TbSchoolMapper tbSchoolMapper;
	@Autowired private TbStudentMapper tbStudentMapper;
	@Autowired private TbCoachMapper tbCoachMapper;
	
	private String schName;
	private String schAccount;
	private String schBossname;
	//驾校入驻
	@Override
	@Transactional
	public MyResult insertSchoolInfo(String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge,String fileName) {
		String md5Password = Md5Tools.getMd5(password);
		MyResult result = new MyResult();
		TbSchool school = new TbSchool();
		school.setSchCreditcode(sch_creditcode);		
		TbSchool tbSchoolCreditcode = tbSchoolMapper.getSchoolByCreditcode(school);
		if(tbSchoolCreditcode != null) {
			result.setMyresult("creditcodeAlready");
		}else {
			school.setSchAccount(phone);
			TbSchool tbSchool = tbSchoolMapper.getSchool(school);
			if(tbSchool == null) {
				school.setRolId(3);
				school.setSchName(sch_name);		
				school.setSchPassword(md5Password);
				school.setSchAddress(sch_address);
				school.setSchIntroduction(sch_introduce);
				school.setSchBossname(sch_bossname);
				school.setSchRegistercapital(sch_registerCapital);				
				school.setSchType(sch_type);
				school.setSchHeadimg(fileName);
				school.setSchCharge(sch_charge);
				int res = tbSchoolMapper.insertSchoolInfo(school);
				if(res == 1) {
					result.setMyresult("success");
				}else {
					result.setMyresult("failed");
				}
			}else {
				result.setMyresult("already");
			}
		}		
		return result;
	}
	
	//驾校条件查找
	@Override
	public List<TbSchool> searchSchool(HttpServletRequest request) {
		schName=request.getParameter("schName");
		schAccount=request.getParameter("schAccount");
		schBossname=request.getParameter("schBossname");
		if(schName.trim().equals("")) {
			schName=null;
		}
		if(schAccount.trim().equals("")) {
			schAccount=null;
		}
		if(schBossname.trim().equals("")) {
			schBossname=null;
		}
		List<TbSchool> schoollist=tbSchoolMapper.selectSchool(schName,schAccount,schBossname);
		
		return schoollist;
	}


	@Override
	public List<TbExamschedule> selectExamschedule(HttpServletRequest request) {
		String schId = request.getParameter("schId");
		String subId = request.getParameter("subId");
		return tbSchoolMapper.selectExamschedule(schId,subId);
	}


	@Override
	@Transactional
	public MyResult addExamschedule(HttpServletRequest request) {
		MyResult myResult = new MyResult();
		TbExamschedule examschedule = new TbExamschedule();
		//学校
		examschedule.setSchId(Integer.parseInt(request.getParameter("schId")));
		//科目
		examschedule.setSubId(Integer.parseInt(request.getParameter("subId")));
		String date = request.getParameter("exsDate");
		String time = request.getParameter("exsTime");
		String exsTime = date+" "+time;
		//考试时间
		examschedule.setExsTime(exsTime);
		String exsEndTime = getExsEndTime(date);
		//报名截止时间
		examschedule.setExsEndtime(exsEndTime);
		//人数上限
		examschedule.setExsTotalnum(Integer.parseInt(request.getParameter("exsTotalNum")));
		//考试地点
		examschedule.setExsAddress(request.getParameter("exsAddress"));
		int res = tbSchoolMapper.addExamschedule(examschedule);
		if(res>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	
	public static String getExsEndTime(String dateGiven) {
		String exsEndTime=null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(dateGiven);
			Calendar calendar = new GregorianCalendar();
			//根据日期设置日历
			calendar.setTime(date);
			//当前日期的前三天
			calendar.add(calendar.DATE,-3);
			//通过日历获取新的时间
			date= calendar.getTime();
			exsEndTime = format.format(date);
			exsEndTime += " 00:00:00";
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exsEndTime;
	}


	/**
	 * 	录入学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	@Override
	@Transactional
	public MyResult addScore(HttpServletRequest request) {
		TbSubjectscore tbScore = new TbSubjectscore();
		MyResult myResult = new MyResult();	
		Integer stuId = Integer.parseInt(request.getParameter("stuId"));
		Integer subId =Integer.parseInt(request.getParameter("subId"));
		Integer score = Integer.parseInt(request.getParameter("score"));
		tbScore.setStuId(stuId);
		tbScore.setSubId(subId);
		tbScore.setSusScore(score);
		//录入的成绩插入数据库
		int res = tbSchoolMapper.addScore(tbScore);
		if(res>0) {
			TbStudent tbStudent = new TbStudent();
			tbStudent.setStuId(stuId);
			if(score>=80) {//成绩大于等于80时
				//进入下一科目
				tbStudent.setSubId(subId+1);
				//状态变更为未预约
				tbStudent.setStuBookingstate("未预约");
			}else {
				tbStudent.setStuBookingstate("可预约");
			}
			int res2 = tbStudentMapper.updateStudentWithScore(tbStudent);
			if(res2>0) {
				myResult.setMyresult("next");
			}else {
				myResult.setMyresult("failed");
			}
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	
	/**
	 * 	修改学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	@Override
	@Transactional
	public MyResult updateScore(HttpServletRequest request) {
		TbSubjectscore tbScore = new TbSubjectscore();
		MyResult myResult = new MyResult();	
		Integer stuId = Integer.parseInt(request.getParameter("stuId"));
		Integer subId = Integer.parseInt(request.getParameter("subId"));
		Integer score = Integer.parseInt(request.getParameter("score"));
		Integer susId = Integer.parseInt(request.getParameter("susId"));
		tbScore.setSusScore(score);
		tbScore.setStuId(stuId);
		tbScore.setSubId(subId);
		tbScore.setSusId(susId);
		//根据修改的成绩更新数据库
		int res = tbSchoolMapper.updateScore(tbScore);
		if(res>0) {
			TbStudent tbStudent = new TbStudent();
			tbStudent.setStuId(stuId);
			
			if(score>=80) {//成绩大于等于80时
				//进入下一科目
				tbStudent.setSubId(subId+1);
				//状态变更为未预约
				tbStudent.setStuBookingstate("未预约");
			}else {
				tbStudent.setStuBookingstate("可预约");
			}
			
			int res2 = tbStudentMapper.updateStudentWithScore(tbStudent);
			
			if(res2>0) {
				myResult.setMyresult("next");
			}else {
				myResult.setMyresult("failed");
			}
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	

	@Override
	public List<Count> countStudent(String coaId, String dateId) {// 根据驾校统计近半年,或者近30天报名人数
		if (dateId.equals("1")) {
			return tbSchoolMapper.countStudentByCoach(coaId);
		}
		return tbSchoolMapper.countStudentByMonth(coaId);
	}

	@Override
	public List<Count> searchDate() {// 获取近半年日期到月份
		return tbSchoolMapper.searchDate();
	}

	@Override
	public List<Count> countStudentByDate(String month,String schId) {// 查询某一个月有人报名的驾校的人数
		return tbSchoolMapper.countStudentByDate(month,schId);
	}

	@Override
	public List<Count> countAllStudentByDate(String month,String schId) {// 查询某一个月所有驾校报名人数，没有的置零
		return tbSchoolMapper.countAllStudentByDate(month,schId);
	}
	
	/**
	 * 	查询所有教练
	 */
	@Override
	public List<TbCoach> selectCoachBySchId(Integer schId) {
		return tbCoachMapper.selectCoachBySchId(schId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//禁止、恢复学员预约 
	@Override
	public void updateSignupstatus(HttpServletRequest request) {
		System.out.println("存入");
		String schId=request.getParameter("schId");
		String signupstatus=request.getParameter("schSignupstatus");
		if(signupstatus.equals("允许报名")) {
			signupstatus="禁止报名";
		}else {
			signupstatus="允许报名";
		}
		tbSchoolMapper.updateSignupstatus(signupstatus, schId);		
	}

	
	//禁止恢复驾校运营
	@Override
	public void updateOperativestatus(HttpServletRequest request) {
		String schId=request.getParameter("schId");
		String schOperativestatus=request.getParameter("schOperativestatus");
	
		if(schOperativestatus.equals("禁止运营")) {
			schOperativestatus="允许运营";
		}else {
			schOperativestatus="禁止运营";
		}
		tbSchoolMapper.updateOperativestatus(schOperativestatus, schId);
		
	}
	

	//查找未审核驾校
	@Override
	public List<TbSchool> findNotauditSchool() {
		List<TbSchool> notauditSchool=tbSchoolMapper.selectNotauditSchool();
		return notauditSchool;
	}
	
	@Override
	public String updateAudit(HttpServletRequest request) {
		String schid=request.getParameter("schId");
		String auditresult=request.getParameter("auditResult");
		String result="";
		System.out.println(schid);
		System.out.println(auditresult);
		if(auditresult.equals("审核不准")) {
			result="fail";
		}else if(auditresult.equals("审核通过")){
			tbSchoolMapper.updateAudit(schid);
			result="success";
		}
		return result;	
	}

	//获取驾校头像
	@Override
	public String selectHeadportrait(HttpServletRequest request) {
		int schid=Integer.valueOf(request.getParameter("schId"));
		TbSchool tbSchool =tbSchoolMapper.findSchoolBySchId(schid);
		String headportraiturl=tbSchool.getSchHeadimg();
		return headportraiturl;
	}

	//驾校具体信息
	@Override
	public TbSchool selectSchoolByid(int schid) {
		TbSchool tbSchool =tbSchoolMapper.findSchoolBySchId(schid);
		return tbSchool;
	}

	//插入申诉内容
	@Override
	public int insertThecomplaintcontent(HttpServletRequest request) {
		Date appTime=new Date();
//		String schid=(String) request.getSession().getAttribute("schId");
		String content=request.getParameter("content");
		String name=request.getParameter("sname");
		int insertresult=0;
		if (content.trim().equals("")) {
			content = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if(content!=null&&name!=null) {
			insertresult=tbSchoolMapper.insertThecomplaintcontent("1", content, appTime);
		}		
		return insertresult;
	}
	//查看申诉记录
	@Override
	public List<TbAppeal> selectReply(HttpServletRequest request) {
//		String schid=(String) request.getSession().getAttribute("schId");
		List<TbAppeal> appeallist=tbSchoolMapper.selectReply(1);
		return appeallist;
	}	


//查询所有允许报名和运营的驾校
	@Override
	public List<TbSchool> selectAllSchoolForAdvertise() {
		return tbSchoolMapper.selectAllSchoolForAdvertise();
	}




}
