package com.dsmp.service.impl;

import java.util.Date;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.domain.Data;
import com.dsmp.mapper.LCoachMapper;
import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.mapper.TbStudyrecordMapper;
import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.service.LCoachService;
import com.dsmp.utils.AssesToken;
import com.dsmp.utils.Base64Util;
import com.dsmp.utils.FileUtil;
import com.dsmp.utils.GsonUtils;
import com.dsmp.utils.HttpUtil;

@Service
public class LCoachServiceImpl implements LCoachService {

	@Autowired
	private HttpSession session;
	@Autowired
	private MyResult myResult;
	private String account;
	private String name;
	private String beginTime;
	private String endTime;
	private String belongSubject;
	private Date date;
	@Autowired private LCoachMapper lCoachMapper;
	
	//教练所属学生的普通信息
	@Autowired
	private TbStudentMapper tbStudentMapper;
	@Autowired
	private TbStudyrecordMapper tbStudyrecordMapper;


	@Autowired
	private TbParameterMapper tbParameterMapper;
	
	@Override
	public List<TbStudent> belongtococh(int stuid, HttpServletRequest request) {
		account = request.getParameter("account");
		name = request.getParameter("name");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
		belongSubject = request.getParameter("belongSubject");
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

	// 查询该教练底下学员已学学时
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

	//查找学生对教练的评价
	@Override
	public List<TbRating> selectStudentratingmsg(int coaId, String choose) {
		if (choose.equals("所有评价")) {
			choose = null;
		}
		List<TbRating> ratinglist = lCoachMapper.selectStudentRating(coaId, choose);
		return ratinglist;

	}

	// 查询该学员今天打卡时间是否已满，上次打卡是否结束能否重复打卡，打卡时间是否在要求内
	@Override
	public MyResult beginStudyJud(String stuId, String subId) {
		SimpleDateFormat df = new SimpleDateFormat("HH");// 设置日期格式
		int nowTime = Integer.valueOf(df.format(new Date()));// 获取当前时间点
		int beginTime =Integer.valueOf(tbParameterMapper.selectParamter("科目二三每天起始打卡时间").split(":")[0]) ;
		int endTime = Integer.valueOf(tbParameterMapper.selectParamter("科目二三每天结束打卡时间").split(":")[0]) ;
		if (nowTime < beginTime || nowTime > endTime) {// 不在打卡时间内
			myResult.setMyresult("outOfTime");
			myResult.setData(beginTime + "_" + endTime);
			return myResult;
		}
		// 获得今天的学习记录集合
		List<TbStudyrecord> recodList = tbStudyrecordMapper.selectNowDayRecord(stuId, subId);
		double time = 0.00;
		if (recodList.size() > 0) {// 有过记录
			if (recodList.get(0).getStrTime() == time&& recodList.get(0).getStrState()==null) {// 说明有还没结束的打卡，今天
				myResult.setMyresult("haveNotEnd");
				return myResult;
			}
			for (int i = 0; i < recodList.size(); i++) {
				time += recodList.get(i).getStrTime();
			}
			if (time >= 4) {// 今日打卡时长足够
				myResult.setMyresult("timeEnough");
				myResult.setData("4");
				return myResult;
			}
		}

		myResult.setMyresult("allow");

		return myResult;
	}

	// 查询该学员结束打卡学习判断，是否有需要结束的记录
	@Override
	public MyResult endStudyJud(String stuId, String subId) {
		// 获得今天的学习记录集合
		List<TbStudyrecord> recodList = tbStudyrecordMapper.selectNowDayRecord(stuId, subId);
		double time = 0.00;
		if (recodList.size() != 0) {// 有记录，才需要判断是否要结束
			// 说明有还没结束的打卡，今天，且非无效记录
			if (recodList.get(0).getStrTime() == time && recodList.get(0).getStrState() == null) {
				myResult.setMyresult("allowEnd");
				return myResult;
			}
		}
		myResult.setMyresult("doNotEnd");
		return myResult;
	}

	// 学员人脸识别打卡
	@Override
	public MyResult makeClock(HttpServletRequest request, String base, String stuId, String subId) {
		myResult = new MyResult();
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
		try {
			String photoPath = tbStudentMapper.findStudentImgByStuId(Integer.valueOf(stuId));//获取学员照片名称（路径）
			String filePath = tbParameterMapper.selectParamter("系统文件存储路径");//获取系统文件储存路径
			String path = filePath+"/images/student/"+photoPath;
			File file = new File(path);
			if (!file.exists()) {
				myResult.setData("noPhoto");
				return myResult;
			}
			byte[] bytes1 = FileUtil.readFileByBytes(path);
//					byte[] bytes2 = FileUtil.readFileByBytes("【本地文件2地址】");
			String image1 = Base64Util.encode(bytes1);
//					String image2 = Base64Util.encode(bytes2);
			String image2 = base;
			List<Map<String, Object>> images = new ArrayList<>();

			Map<String, Object> map1 = new HashMap<>();
			map1.put("image", image1);
			map1.put("image_type", "BASE64");
			
			map1.put("face_type", "CERT");
			map1.put("quality_control", "LOW");
			map1.put("liveness_control", "NONE");

			Map<String, Object> map2 = new HashMap<>();
			map2.put("image", image2);
			map2.put("image_type", "BASE64");
			map2.put("face_type", "LIVE");
			map2.put("quality_control", "LOW");
			map2.put("liveness_control", "NORMAL");

			images.add(map1);
			images.add(map2);

			String param = GsonUtils.toJson(images);

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = AssesToken.getAuth();

			String result = HttpUtil.post(url, accessToken, "application/json", param);
			myResult.setMyresult(result);

		} catch (Exception e) {
			e.printStackTrace();
			myResult.setData("fail");
		}
		return myResult;
	}

	// 插入学员开始打卡记录
	@Transactional
	@Override
	public MyResult insertStudyRecord(String stuId, String subId) {
		int res = tbStudyrecordMapper.insertStudyRecord(stuId, subId);
		if (res > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}

		return myResult;
	}
	
	@Transactional
	// 学员结束打卡，更新记录
	@Override
	public MyResult endStudyRecord(String stuId, String subId) {
		// 获得今天的学习记录集合
		List<TbStudyrecord> recordList = tbStudyrecordMapper.selectNowDayRecord(stuId, subId);
		Date startTime = recordList.get(0).getStrBegintime();
		long nowTime = System.currentTimeMillis();
		double minTime = 0.1;//最小学习时长
		double maxTime = Integer.valueOf(tbParameterMapper.selectParamter("科目二三每天打卡时长限制").split(":")[0]) ;//最长学习时长每天
		if (nowTime-startTime.getTime()<1000*60*minTime*60) {//小于5分钟
			tbStudyrecordMapper.updatefalse(recordList.get(0).getStrId());//添加无效学习记录
			myResult.setMyresult("timeTOShort");
			myResult.setData(minTime+"");
			return myResult;
		}else {
			double todayTime=0.0;
			for (int i = 0; i < recordList.size(); i++) {
				todayTime+=recordList.get(i).getStrTime();//今天已经学的时间
			}
			double timeLenth = (nowTime-startTime.getTime())/(double)(1000*60*60);//本次学习时长
			//今天超过允许时长
			if (timeLenth+todayTime>maxTime) {
				double allowTime = maxTime-todayTime;
				tbStudyrecordMapper.updateNormal(recordList.get(0).getStrId(), allowTime);
				myResult.setMyresult("timeTOLong");
				myResult.setData(maxTime+"_"+allowTime);
				return myResult;
			}
			//没有超出时长
			tbStudyrecordMapper.updateNormal(recordList.get(0).getStrId(), timeLenth);
			myResult.setMyresult("timeOk");
			myResult.setData(new DecimalFormat("#.00").format(timeLenth));
		}
		
		return myResult;
	}
	//查找考试安排信息
	@Override
	public List<TbExamschedule> selectThetestmsg(int schId,String subname) {
		date=new Date();
		List<TbExamschedule> thetestmsg=lCoachMapper.selectTheTestMsg(date,schId, subname);
		return thetestmsg;
	}
	//教练安排学生考试界面的表格信息
	@Override
	public List<TbStudent> findTestappointment(String subname, int coaid) {
		Integer subid=1;
		if(subname!=null&&subname.equals("科目一学员")) {
			subid=1;
		}
		if(subname!=null&&subname.equals("科目二学员")) {
			subid=2;
		}
		if(subname!=null&&subname.equals("科目三学员")) {
			subid=3;
		}
		if(subname!=null&&subname.equals("科目四学员")) {
			subid=4;
		}
		System.out.println("此时的科目是："+subid);
		System.out.println(subname);
		
		List<TbStudent> studenttestappointment=lCoachMapper.selectTestappointment(subid,coaid);
		return studenttestappointment;
	}

	@Override
	public List<TbStudent> findHaveappointment(int coaid,Integer stuid) {
		 List<TbStudent> haveappointmentstudent=lCoachMapper.selectHaveappointment(coaid,stuid);
		return haveappointmentstudent;
	}

	@Override
	public Integer findNumberofsubjects(String subname, int coaid) {
		int subid=0;
		if(subname!=null&&subname.equals("科目一")) {
			subid=1;
		}
		if(subname!=null&&subname.equals("科目二")) {
			subid=2;
		}
		if(subname!=null&&subname.equals("科目三")) {
			subid=3;
		}
		if(subname!=null&&subname.equals("科目四")) {
			subid=4;
		}
		
		Integer countNumberofsubjects=lCoachMapper.selectNumberofsubjects(subid,coaid);
		
		return countNumberofsubjects;
	}

	@Override
	public Integer findSeatNum(int exsid) {
		Integer seatNum=lCoachMapper.selecteasSeatNum(exsid);
		return seatNum;
	}

	@Override
	public void insertBooking(int exsid, int stuid, int seatNum) {
		lCoachMapper.insertBooking(exsid, stuid, seatNum);
		
	}

	@Override
	public void updateBookingstate(int stuid) {
		lCoachMapper.updateBookingstate(stuid);	
	}

	@Override
	public TbExamschedule findBookingnumber(int exsid) {
		TbExamschedule tbExamschedule=lCoachMapper.seletBookingnumber(exsid);
		return tbExamschedule;
	}

	@Override
	public void updateMaxBookingnum(int exsSignupnum, int exsId) {		
		lCoachMapper.updateMaxBookingnum(exsSignupnum, exsId);
	}
	//统计学员打卡计时
	@Override
	public Double findStudytime(int subid, int stuid) {
		
		Double studytemenum=lCoachMapper.selectStudytime(subid, stuid);
		if(subid==1) {
			if(studytemenum==null) {
				studytemenum=0.0;				
			}
			studytemenum = studytemenum/60/60;
		}
		return studytemenum;
	}
	//查找学员各科考试记录
	@Override
	public List<BelongtoCoachStudentMsg> findAcademicrecord(int subid, int stuid) {
		List<BelongtoCoachStudentMsg> academicrecord=lCoachMapper.selectAcademicrecord(subid, stuid);
		return academicrecord;
	}

	@Override
	public BelongtoCoachStudentMsg findSubjectnow(int subid, int stuid) {
		BelongtoCoachStudentMsg belongtoCoachStudentMsg=lCoachMapper.selectSubjectnow(subid, stuid);
		return belongtoCoachStudentMsg;
	}
	
}
