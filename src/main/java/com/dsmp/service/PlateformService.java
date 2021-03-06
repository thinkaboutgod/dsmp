package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.PageResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.TbCapitalrecord;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbParameter;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbSubject;
import com.dsmp.pojo.TbTopic;
import com.dsmp.pojo.TbVideo;

public interface PlateformService {
	public List<TbStudent> searchStudent(HttpServletRequest request);// 查询已报名驾校学员

	public List<TbStudent> searchStudent2(HttpServletRequest request);// 查询未报名驾校学员

	public MyResult changeStudentState(HttpServletRequest request, MyResult myResult);// 修改学员账号状态

	public List<TbSchool> searchAllSchool();// 查询所有驾校
	
	public List<TbSubject> searchAllSubject();//查询所有科目
	
	public List<Count> searchDate();// 查询近六个月日期

	public List<Count> countStudent(String schId, String dateId);// 查询各驾校近六个月报名人数

	public List<Count> countStudentByDate(String month);//查询某一个月有人报名的驾校的人数
	
	public List<Count> countAllStudentByDate(String month);//查询某一个月所有驾校报名人数，没有的置零

	public PageResult searchVideoBySubect(String subject, String page);// 根据科目查询学习视频

	public MyResult changeTbVideoTitleByVidId(TbVideo tbVideo);// 修改视频标题
	
	public MyResult deletVideoByVidId(HttpServletRequest request, String vidId);//删除视频
	
	public MyResult uploadVideo(HttpServletRequest request,String vidTitle,String subject,MultipartFile file);//上传视频
	
	public MyResult uploadVideoImg (HttpServletRequest request,MultipartFile fileImg);//上传视频图片

	public List<TbCapitalrecord> searchMoneyRecord(HttpServletRequest request);//查询资金记录
	
	public List<TbParameter> searchAllParameter();//查询参数记录
	
	public MyResult updataParmeter(TbParameter tbParameter);//更新参数表
	

	public List<TbAppeal> findThecomplaint(HttpServletRequest request);//查询申诉记录

	public int insertReply(HttpServletRequest request);//申诉回复

	public String searchFilePathParameter();//查询参数记录

}
