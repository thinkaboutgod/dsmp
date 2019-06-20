package com.dsmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.service.PlateformService;
import com.dsmp.service.VideoService;

@Controller
@RequestMapping("/video")
public class ClientVideoControll {
	@Autowired
	private VideoService videoService;
	@Autowired PlateformService plateformService;

	// 前台点击单个视频后切换页面查询视频
	@RequestMapping(value = "toSearchVideo.action")
	public ModelAndView toStudentController(String suject, String vidImgpath, String vidPath, String subName,
			String vidTitle) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vidPath", vidPath);
		mav.addObject("vidImgpath", vidImgpath);
		mav.addObject("subName", subName);
		mav.addObject("vidTitle", vidTitle);
		mav.addObject("videoList", videoService.searchVideoBySubId(suject));
		mav.addObject("mainPath", plateformService.searchFilePathParameter());//文件访问路径
		mav.setViewName("client/video_study");
		return mav;
	}

	
}
