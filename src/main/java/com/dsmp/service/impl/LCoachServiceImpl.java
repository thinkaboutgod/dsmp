package com.dsmp.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.LCoachMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.mapper.TbStudyrecordMapper;
import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.LCoachService;

@Service
public class LCoachServiceImpl implements LCoachService {

	@Autowired
	private HttpSession session;

	private String account;
	private String name;
	private String beginTime;
	private String endTime;
	private String belongSubject;

	@Autowired
	private LCoachMapper lCoachMapper;

	@Autowired
	private TbStudentMapper tbStudentMapper;
	@Autowired
	private TbStudyrecordMapper tbStudyrecordMapper;

	@Override
	public List<TbStudent> belongtococh(int stuid, HttpServletRequest request) {
		account = request.getParameter("account");
		name = request.getParameter("name");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
		belongSubject = request.getParameter("belongSubject");
		System.out.println("科目是：" + belongSubject);
		if (account.trim().equals("")) {
			account = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}
		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		if (belongSubject != null) {
			if (belongSubject.equals("查看所有学员")) {
				belongSubject = null;
			} else if (belongSubject.equals("科目一学员")) {
				belongSubject = "科目一";
			} else if (belongSubject.equals("科目二学员")) {
				belongSubject = "科目二";
			} else if (belongSubject.equals("科目三学员")) {
				belongSubject = "科目三";
			} else if (belongSubject.equals("科目四学员")) {
				belongSubject = "科目四";
			} else {
				belongSubject = null;
			}
		}
		SearchBean serchBean = new SearchBean();
		serchBean.setName(name);
		serchBean.setAccount(account);
		serchBean.setBeginTime(beginTime);
		serchBean.setEndTime(endTime);
		serchBean.setBelongSubject(belongSubject);
		List<TbStudent> stuentlist = lCoachMapper.belongtocoach(stuid, serchBean);
		return stuentlist;
	}

	// 查找考试安排信息
	@Override
	public List<TbExamschedule> selectThetestmsg(int schId) {
		List<TbExamschedule> thetestmsg = lCoachMapper.selectTheTestMsg(schId);
		return thetestmsg;
	}

	@Override
	public TbCoach selectcoach(int coaid) {
		TbCoach bbCoach = lCoachMapper.selectCoach();
		return null;
	}

	// 查询教练底下科目二三学员
	@Override
	public List<TbStudent> searchSubjectStudent() {
//		String caoId = ((TbCoach)session.getAttribute("coach")).getCoaId().toString();
		return tbStudentMapper.selectStudentByCoachIdAndSubject("1");
	}

	//查询该教练底下学员已学学时
	@Override
	public MyResult countTimeByStuIdAndSubject(String stuId, String subId) {
		Double sum = tbStudyrecordMapper.countTimeByStuIdAndSubject(stuId, subId);
		MyResult myResult = new MyResult();
		if (null == sum) {
			sum = 0.0;
		}
		myResult.setSum(sum);
		return myResult;
	}

	@Override
	public List<BelongtoCoachStudentMsg> selectStudentParticulars(int stuid) {
		List<BelongtoCoachStudentMsg> studentmsg = lCoachMapper.selectStudentMsg(stuid);
		System.out.println("学生信息");
		return studentmsg;
	}

	@Override
	public List<TbRating> selectStudentratingmsg(int coaId, String choose) {
		if (choose.equals("所有评价")) {
			choose = null;
		}
		List<TbRating> ratinglist = lCoachMapper.selectStudentRating(coaId, choose);
		return ratinglist;

	}
}
