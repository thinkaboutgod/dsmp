package com.dsmp.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbSchoolMapper;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;

import com.dsmp.service.SchoolService;
import com.dsmp.utils.GsonUtils;
import com.dsmp.utils.Md5Tools;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired private TbSchoolMapper tbSchoolMapper;
	private String schName;
	private String schAccount;
	private String schBossname;
	//驾校入驻
	@Override
	public MyResult insertSchoolInfo(String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge,String fileName) {
		System.out.println("入驻账号"+phone);
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
		System.out.println(result);
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
}
