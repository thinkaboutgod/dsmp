package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.domain.Topic;
import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.PageResult;
import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbCapitalrecord;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbLog;
import com.dsmp.pojo.TbNotice;
import com.dsmp.pojo.TbOption;
import com.dsmp.pojo.TbParameter;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbSubject;
import com.dsmp.pojo.TbTopic;
import com.dsmp.pojo.TbVideo;
import com.dsmp.service.AdvertisementService;
import com.dsmp.service.BlogrollService;
import com.dsmp.service.LogService;
import com.dsmp.service.NoticeService;
import com.dsmp.service.PlateformService;
import com.dsmp.service.SchoolService;
import com.dsmp.service.TopicService;
import com.dsmp.service.impl.LogServiceImpl;
import com.dsmp.utils.GsonUtils;

@Controller
@RequestMapping("/plateform")
public class PlateformController {
	@Autowired
	private PlateformService plateformService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private BlogrollService blogrollService;
	@Autowired
	private LogService logServiceImpl;
	@Autowired
	private MyResult myResult;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private NoticeService noticeService;

	@Autowired
	private SchoolService schoolService;

	// 学员查看页面
	@RequestMapping(value = "toStudentController.action")
	public String toStudentController() {
		return "back/plateform_student";
	}

	// 视频管理页面
	@RequestMapping(value = "toVideoController.action")
	public ModelAndView toVideoController() {
		ModelAndView mav = new ModelAndView();
		List<TbSubject> subList = plateformService.searchAllSubject();
		mav.addObject("subList", subList);
		mav.setViewName("back/plateform_video");
		return mav;
	}

	// 学员统计页面
	@RequestMapping(value = "toStudentCount.action")
	public ModelAndView toStudentCount() {
		ModelAndView mav = new ModelAndView();
		List<TbSchool> listSchool = plateformService.searchAllSchool();// 查询所有驾校
		List<Count> dateList = plateformService.searchDate();// 查询近六个月月份
		mav.addObject("listSchool", listSchool);
		mav.addObject("dateList", dateList);
		mav.setViewName("back/plateform_studentcount");
		return mav;
	}

	// 搜索学员,已报名的
	@RequestMapping(value = "searchAllStudent.action")
	public @ResponseBody Map<String, List<TbStudent>> searchStudent(HttpServletRequest request) {
		List<TbStudent> list = plateformService.searchStudent(request);
		Map<String, List<TbStudent>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}

	// 搜索学员，未报名的
	@RequestMapping(value = "searchAllStudent2.action")
	public @ResponseBody Map<String, List<TbStudent>> searchStudent2(HttpServletRequest request) {
		List<TbStudent> list = plateformService.searchStudent2(request);
		Map<String, List<TbStudent>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}

	// 修改学员状态
	@RequestMapping(value = "changeStudentState.action")
	public @ResponseBody MyResult changeStudentState(HttpServletRequest request) {
		myResult = plateformService.changeStudentState(request, myResult);
		return myResult;
	}

	// 按照驾校统计近半年报名学员人数
	@RequestMapping(value = "countStudentBySchool.action")
	public @ResponseBody List<Count> countStudentBySchool(String schId, String dateId) {
		List<Count> cList = plateformService.countStudent(schId, dateId);

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
		List<Count> cList = plateformService.countStudentByDate(month);
		Map<String, List<Count>> map = new HashMap<>();
		map.put("data", cList);
		return map;
	}

	// 按照驾校查询某一个月有人报名的驾校的报名人数
	@RequestMapping(value = "countAllStudentByDate.action")
	public @ResponseBody List<Count> countAllStudentByDate(String month) {
		List<Count> cList = plateformService.countAllStudentByDate(month);
		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).getData() == null) {
				cList.get(i).setData("0");
			}
		}
		return cList;
	}

	// 按科目查询学习视频
	@RequestMapping(value = "searchVideoBySubect.action")
	public @ResponseBody PageResult searchVideoBySubect(HttpServletResponse response, String subject, String page) {
		response.setContentType("text/html;charset=utf-8");// 加上这个处理问号
		return plateformService.searchVideoBySubect(subject, page);
	}

	// 修改视频标题
	@RequestMapping(value = "changeTitleByVidId.action")
	public @ResponseBody MyResult changeTitleByVidId(@RequestBody TbVideo tbVideo) {

		return plateformService.changeTbVideoTitleByVidId(tbVideo);
	}

	// 删除视频
	@RequestMapping(value = "deletVideoByVidId.action")
	public @ResponseBody MyResult deletVideoByVidId(HttpServletRequest request, String vidId) {
		return plateformService.deletVideoByVidId(request, vidId);
	}

	// 新增视频
	@RequestMapping(value = "uploadVideo.action")
	public @ResponseBody MyResult uploadVideo(HttpServletRequest request, String vidTitle, String subject,
			MultipartFile file) {
		return plateformService.uploadVideo(request, vidTitle, subject, file);
	}

	// 新增视频图片，包括视频，插入数据库
	@RequestMapping(value = "uploadVideoImg.action")
	public @ResponseBody MyResult uploadVideoImg(HttpServletRequest request, MultipartFile fileImg) {
		return plateformService.uploadVideoImg(request, fileImg);
	}

	// 发送题库管理界面
	@RequestMapping(value = "toTopicControl.action")
	public String toTopicControl() {
		return "back/topic_control";
	}

	// 题库管理查询所有题目
	@RequestMapping(value = "searchAllTopic.action")
	public @ResponseBody Map<String, List<TbTopic>> searchAllTopic(String subId) {
		Map<String, List<TbTopic>> map = new HashMap<>();
		List<TbTopic> list = topicService.searchAllTopic(subId);
		map.put("data", list);
		return map;
	}

	// 科目一题库修改
	@RequestMapping(value = "changeTopic.action")
	public @ResponseBody String changeTopic(HttpServletRequest request, String map, MultipartFile newImg) {
		return topicService.changeTopic(request, map, newImg);
	}

	// 科目一题库增加
	@RequestMapping(value = "addTopic.action")
	public @ResponseBody String addTopic(HttpServletRequest request, String map, MultipartFile addnewImg) {
		return topicService.addTopic(request, map, addnewImg);
	}

	// 科目一题目删除
	@RequestMapping(value = "deleteTopic.action")
	public @ResponseBody MyResult deleteTopic(HttpServletRequest request, String topId) {
		return topicService.deleteTopic(request, topId);
	}

	// 发送资金记录界面
	@RequestMapping(value = "searchMoney.action")
	public String searchMoney() {
		return "back/plateform_money";
	}

	// 查询资金记录
	@RequestMapping(value = "searchMoneyRecord.action")
	public @ResponseBody Map<String, List<TbCapitalrecord>> searchMoneyRecord(HttpServletRequest request) {
		Map<String, List<TbCapitalrecord>> map = new HashMap<>();
		List<TbCapitalrecord> recordList = plateformService.searchMoneyRecord(request);
		map.put("data", recordList);
		return map;
	}

	// 发送参数管理界面
	@RequestMapping(value = "toParameterController.action")
	public String toParameter() {
		return "back/plateform_parameter";
	}

	// 查询参数记录
	@RequestMapping(value = "searchParameter.action")
	public @ResponseBody Map<String, List<TbParameter>> searchParameter() {
		Map<String, List<TbParameter>> map = new HashMap<>();
		List<TbParameter> parList = plateformService.searchAllParameter();
		map.put("data", parList);
		return map;
	}

	// 修改参数表的参数
	@RequestMapping(value = "changeParameter.action", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public @ResponseBody MyResult changeParameter(@RequestBody TbParameter tbParameter) {
		return plateformService.updataParmeter(tbParameter);
	}

	// 发送友情链接管理界面
	@RequestMapping(value = "toblogRollController.action")
	public String toblogRollControl() {
		return "back/plateform_blogroll";
	}

	// 查询友情链接
	@RequestMapping(value = "searchAllBlogRoll.action")
	public @ResponseBody Map<String, List<TbHotlink>> searchAllBlogRoll() {
		Map<String, List<TbHotlink>> map = new HashMap<>();
		List<TbHotlink> bloList = blogrollService.searchAllBlogRoll();
		map.put("data", bloList);
		return map;
	}

	// 修改友情链接
	@RequestMapping(value = "changeBlogroll.action", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public @ResponseBody MyResult changeBlogroll(@RequestBody TbHotlink tbHotlink) {
		return blogrollService.updateBlogroll(tbHotlink);
	}

	// 删除友情链接
	@RequestMapping(value = "deleteBlogroll.action")
	public @ResponseBody MyResult deleteBlogroll(String holId) {
		return blogrollService.deleteBlogroll(holId);
	}

	// 新增友情链接
	@RequestMapping(value = "addBlogroll.action", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public @ResponseBody MyResult addBlogroll(@RequestBody TbHotlink tbHotlink) {
		return blogrollService.insertBlogroll(tbHotlink);
	}

	// 发送日志查看界面
	@RequestMapping(value = "searchLogController.action")
	public String searchLogController() {
		return "back/plateform_log";
	}

	// 查询友情链接
	@RequestMapping(value = "searchLog.action")
	public @ResponseBody Map<String, List<TbLog>> searchLog(HttpServletRequest request) {
		Map<String, List<TbLog>> map = new HashMap<>();
		List<TbLog> logList = logServiceImpl.searchLog(request);
		map.put("data", logList);
		return map;
	}

	// 发送系统文件路径
	@RequestMapping(value = "searchFilePath.action")
	public @ResponseBody String searchFilePath() {
		return plateformService.searchFilePathParameter();
	}

	// 发送广告管理界面
	@RequestMapping(value = "advertiseController.action")
	public ModelAndView advertiseController() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("levelList", advertisementService.searchAdvLevel());
		mav.addObject("schoolList", schoolService.selectAllSchoolForAdvertise());// 所有允许报名和运营的驾校
		mav.setViewName("back/plateform_advertise");
		return mav;
	}

	// 查询广告，分页，可查所有，可根据广告等级
	@RequestMapping(value = "searchAdvertise.action")
	public @ResponseBody PageResult searchAdvertise(HttpServletRequest request) {
		return advertisementService.searchAdvertise(request);
	}

	// 广告修改或者增加
	@RequestMapping(value = "forAdvertise.action")
	public @ResponseBody String advertiseChange_add(HttpServletRequest request, MultipartFile newImg) {
		return advertisementService.advertiseChange_add(request, newImg);
	}

	// 广告删除
	@RequestMapping(value = "deleteAdvertise.action")
	public @ResponseBody MyResult deleteAdvertise(String advId) {
		return advertisementService.deleteAdvertise(advId);
	}

	// 发送公告管理界面
	@RequestMapping(value = "noticeController.action")
	public ModelAndView noticeController() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("typelList", noticeService.selectAllType());
		mav.setViewName("back/plateform_notice");
		return mav;
	}

	// 发送所有公告
	@RequestMapping(value = "searchAllNotice.action")
	public @ResponseBody Map<String, List<TbNotice>> searchAllNotice() {
		Map<String, List<TbNotice>> map = new HashMap<>();
		map.put("data", noticeService.selectAllNotice());
		return map;
	}

	// 新增公告动态
	@RequestMapping(value = "addNotice.action", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public @ResponseBody MyResult addNotice(@RequestBody TbNotice tbNotice) {

		return noticeService.insertNotice(tbNotice);
	}

	// 修改公告动态
	@RequestMapping(value = "changeNotice.action", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
	public @ResponseBody MyResult changeNotice(@RequestBody TbNotice tbNotice) {
		System.out.println(tbNotice.getNotId());
		return noticeService.changeNotice(tbNotice);
	}

	// 删除公告动态
	@RequestMapping(value = "deleteNotice.action")
	public @ResponseBody MyResult deleteNotice(String notId) {
		return noticeService.deleteNoticeById(Integer.valueOf(notId));
	}
}
