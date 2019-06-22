package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.PageResult;
import com.dsmp.pojo.TbAdvLevel;

public interface AdvertisementService {
	public PageResult searchAdvertise(HttpServletRequest request);//查询广告
	
	public List<TbAdvLevel> searchAdvLevel();//查询广告等级对照表
	
	public String advertiseChange_add(HttpServletRequest request, MultipartFile newImg);//广告增加或者修改

	public MyResult deleteAdvertise(String advId);//删除广告
}
