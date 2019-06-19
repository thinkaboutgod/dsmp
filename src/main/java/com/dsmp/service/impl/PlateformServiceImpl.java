package com.dsmp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.type.PrimitiveType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsmp.log.MyLog;
import com.dsmp.mapper.PlateformMapper;
import com.dsmp.mapper.TbCapitalrecordMapper;
import com.dsmp.mapper.TbOptionMapper;
import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.mapper.TbSubjectMapper;
import com.dsmp.mapper.TbTopicMapper;
import com.dsmp.mapper.TbVideoMapper;
import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.PageResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCapitalrecord;
import com.dsmp.pojo.TbOption;
import com.dsmp.pojo.TbParameter;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbSubject;
import com.dsmp.pojo.TbTopic;
import com.dsmp.pojo.TbVideo;
import com.dsmp.service.PlateformService;
import com.dsmp.utils.GsonUtils;
import com.dsmp.utils.PageUtil;

@Service
public class PlateformServiceImpl implements PlateformService {
	private String account;
	private String name;
	private String fwhere;
	private String beginTime;
	private String endTime;
	@Autowired
	private HttpSession session;
	@Autowired
	private MyResult myResult;
	@Autowired
	private PlateformMapper plateformMapper;
	@Autowired
	private TbSchoolMapper tbSchoolMapper;
	@Autowired
	private TbStudentMapper tbStudentMapper;
	@Autowired
	private TbVideoMapper tbVideoMapper;
	@Autowired
	private TbSubjectMapper tbSubjectMapper;
	@Autowired
	private TbCapitalrecordMapper tbCapitalrecordMapper;

	@Autowired
	private TbParameterMapper tbParameterMapper;

	@Override
	public List<TbStudent> searchStudent(HttpServletRequest request) {// 查询已报名学员
		account = request.getParameter("account");
		name = request.getParameter("name");
		fwhere = request.getParameter("school");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
		if (account.trim().equals("")) {
			account = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}
		if (fwhere.trim().equals("")) {
			fwhere = null;
		}

		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		SearchBean sBean = new SearchBean(account, name, fwhere, beginTime, endTime);

		return plateformMapper.searchAllstudent(sBean);
	}

	@Override
	public List<TbStudent> searchStudent2(HttpServletRequest request) {// 查询未报名学员
		account = request.getParameter("account");
		name = request.getParameter("name");
		fwhere = request.getParameter("school");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
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
		SearchBean sBean = new SearchBean(account, name, fwhere, beginTime, endTime);

		return plateformMapper.searchAllstudent2(sBean);
	}

	// 修改学员状态
	@Transactional
	@MyLog(operationDetail = "修改学员状态")
	@Override
	public MyResult changeStudentState(HttpServletRequest request, MyResult myResult) {
		String state = request.getParameter("state");
		String stuId = request.getParameter("stuId");
		String preText = request.getParameter("preText");
		int res = 0;
		if (preText.equals("锁定")) {
			res = plateformMapper.changeStudentStateLock(Integer.valueOf(stuId), "启用");
		} else {
//			if (state.equals("start")) {
//				state = "启用";
//			} else if (state.equals("forbid")) {
//				state = "禁用";
//			}
			res = plateformMapper.changeStudentState(Integer.valueOf(stuId), state);

		}
		if (res > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

	@Override
	public List<TbSchool> searchAllSchool() {// 查询所有驾校
		return tbSchoolMapper.selectAllSchool();
	}

	@Override
	public List<Count> countStudent(String schId, String dateId) {// 根据驾校统计近半年,或者近30天报名人数
		if (dateId.equals("1")) {
			return tbStudentMapper.countStudentBySchool(schId);
		}
		return tbStudentMapper.countStudentByMonth(schId);
	}

	@Override
	public List<Count> searchDate() {// 获取近半年日期到月份
		return tbStudentMapper.searchDate();
	}

	@Override
	public List<Count> countStudentByDate(String month) {// 查询某一个月有人报名的驾校的人数
		return tbStudentMapper.countStudentByDate(month);
	}

	@Override
	public List<Count> countAllStudentByDate(String month) {// 查询某一个月所有驾校报名人数，没有的置零
		return tbStudentMapper.countAllStudentByDate(month);
	}

	@Override
	public PageResult searchVideoBySubect(String subject, String page) {// 查询科目视频，根据科目

		int pageIndex = Integer.valueOf(page);
		int pageCount = 6;
		int startIndex = (pageIndex - 1) * pageCount;// 起始条数，mysql分页只要起始条数，和一次几条
		PageResult pageResult = tbVideoMapper.countVideoBySubect(subject);// 得到总条数
		pageResult.setTotalPage(PageUtil.getPage(pageResult.getTotalPage(), pageCount));
		pageResult.setPageIndex(pageIndex);
		pageResult.setList(tbVideoMapper.searchVideoBySubect(subject, startIndex, pageCount));
		String filePath = tbParameterMapper.selectParamter("系统文件访问路径");// 获取系统文件访问路径
		pageResult.setData(filePath);
		return pageResult;
	}

	@Transactional
	@Override
	public MyResult changeTbVideoTitleByVidId(TbVideo tbVideo) {// 修改视频标题
		int res = tbVideoMapper.updateByPrimaryKeySelective(tbVideo);
		if (res > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

	// 删除视频
	@Transactional
	@MyLog(operationDetail = "删除视频")
	@Override
	public MyResult deletVideoByVidId(HttpServletRequest request, String vidId) {
		TbVideo tbVideo = tbVideoMapper.selectByPrimaryKey(Integer.valueOf(vidId));
		String filePath = tbParameterMapper.selectParamter("系统文件存储路径");// 获取系统文件路径
		File fVideo = new File(filePath + tbVideo.getVidPath());// 视频文件路径
		if (fVideo.exists()) {
			fVideo.delete();// 删除视频
		}
		File fVideoImg = new File(filePath + tbVideo.getVidImgpath());// 视频图片路径
		if (fVideoImg.exists()) {
			fVideoImg.delete();// 删除封面图片
		}
		int res = tbVideoMapper.deleteByPrimaryKey(Integer.valueOf(vidId));
		if (res > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

	@Override
	public List<TbSubject> searchAllSubject() {// 查询所有科目
		return tbSubjectMapper.selectAllSubject();
	}

	// 上传视频临时储存
	@Override
	public MyResult uploadVideo(HttpServletRequest request, String vidTitle, String subject, MultipartFile file) {
		System.out.println("路径为"+request.getServletContext().getRealPath("/temporary_files/"));
		String filePath = tbParameterMapper.selectParamter("系统文件存储路径");// 获取系统文件储存路径
		String path = filePath + "/temporary_files/";
		File newFile = new File(path);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}
		String fileFileName = file.getOriginalFilename();// 获得文件名称
		TbVideo tbVideo = new TbVideo();
		long name = System.currentTimeMillis();// 获取时间戳
		fileFileName = name + "_" + fileFileName;
		try {
			file.transferTo(new File(path + fileFileName));// 临时文件储存到临时文件夹
			tbVideo.setVidTitle(vidTitle);
			tbVideo.setSubId(Integer.valueOf(subject));
			tbVideo.setVidPath(fileFileName);
			session.setAttribute("tbVideo", tbVideo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myResult.setMyresult("success");
		return myResult;
	}

	// 上传视频图片后和临时储存一起储存起来,插入数据库记录
	@Transactional
	@Override
	public MyResult uploadVideoImg(HttpServletRequest request, MultipartFile fileImg) {
		String filePath = tbParameterMapper.selectParamter("系统文件存储路径");// 获取系统文件储存路径
		TbVideo tbVideo = (TbVideo) session.getAttribute("tbVideo");
		String videoPath = filePath + "/video/video/";
		String imgPath = filePath + "/video/video_img/";
		String fromPath = filePath + "/temporary_files/";
		long name = System.currentTimeMillis();
		File newFile = new File(imgPath);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}
		File newFile2 = new File(videoPath);
		if (!newFile2.exists()) {
			newFile2.mkdirs();
		}
		String imgName = name + "_" + fileImg.getOriginalFilename();
		try {
			File videoFile = new File(fromPath + tbVideo.getVidPath());// 如果临时视频文件丢失
			if (!videoFile.exists()) {
				myResult.setMyresult("failed");
				return myResult;
			}
			// 截取名字
			String videoName = tbVideo.getVidPath().substring(tbVideo.getVidPath().indexOf("_"));
			videoName = name + "_" + videoName;// 更新名字
			tbVideo.setVidPath("/video/video/" + videoName);
			tbVideo.setVidImgpath("/video/video_img/" + imgName);
			FileUtils.copyFile(videoFile, new File(newFile2, videoName));// 复制视频
			fileImg.transferTo(new File(imgPath + imgName));// 复制图片到指定路径

			if (tbVideoMapper.insert(tbVideo) > 0) {
				myResult.setMyresult("successUpload");
			}
			;
			if (videoFile.exists()) {
				videoFile.delete();
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myResult;
	}

	// 资金记录查询
	@Override
	public List<TbCapitalrecord> searchMoneyRecord(HttpServletRequest request) {
		String capOrderNumber = request.getParameter("capOrderNumber");
		String stuName = request.getParameter("stuName");
		String schName = request.getParameter("schName");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		if (capOrderNumber.trim().equals("")) {
			capOrderNumber = null;
		}
		if (stuName.trim().equals("")) {
			stuName = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}
		if (schName.trim().equals("")) {
			schName = null;
		}

		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}

		return tbCapitalrecordMapper.searchMoneyRecord(capOrderNumber, stuName, schName, beginTime, endTime);
	}

	// 查询所有参数
	@Override
	public List<TbParameter> searchAllParameter() {

		return tbParameterMapper.selectAllParameter();
	}

	// 更新参数
	@Transactional
	@Override
	public MyResult updataParmeter(TbParameter tbParameter) {
		int res = tbParameterMapper.updataParmeter(tbParameter);
		if (res > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

	// 查询文件访问路径
	@Override
	public String searchFilePathParameter() {
		// TODO Auto-generated method stub
		return tbParameterMapper.selectParamter("系统文件访问路径");
	}

}
