package com.dsmp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsmp.mapper.TbAdvertisementMapper;
import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.PageResult;
import com.dsmp.pojo.TbAdvLevel;
import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbTopic;
import com.dsmp.service.AdvertisementService;
import com.dsmp.utils.PageUtil;
@Service
public class PlateformAdvertiseImpl implements AdvertisementService{
@Autowired
private TbAdvertisementMapper tbAdvertisementMapper;
@Autowired 
private TbParameterMapper tbParameterMapper;
	//查询广告，分页，可查所有，可根据广告等级
	@Override
	public PageResult searchAdvertise(HttpServletRequest request) {
		PageResult pageResult = new PageResult();
		int pageIndex = Integer.valueOf(request.getParameter("page"));
		int pageCount = 6;
		int startIndex = (pageIndex - 1) * pageCount;// 起始条数，mysql分页只要起始条数，和一次几条
		String adlId = request.getParameter("subject");
		if (adlId.equals("0")) {
			adlId = null;
		}
		int count = tbAdvertisementMapper.countAdvertise(adlId);// 根据广告等级得到总条数
		pageResult.setTotalPage(PageUtil.getPage(count, pageCount));
		pageResult.setPageIndex(pageIndex);
		pageResult.setList(tbAdvertisementMapper.selectAdvertise(adlId, startIndex, pageCount));
		String filePath = tbParameterMapper.selectParamter("系统文件访问路径");// 获取系统文件访问路径
		pageResult.setData(filePath);
		return pageResult;
	}
	//查询广告等级对照表
	@Override
	public List<TbAdvLevel> searchAdvLevel(){
		return tbAdvertisementMapper.selectLevel();
	}
	
	//广告修改或者增加
	@Transactional
	@Override
	public String advertiseChange_add(HttpServletRequest request, MultipartFile newImg) {
		String result = null;
		
		String advPath = request.getParameter("toPath");
		String advDescribe = request.getParameter("describe");
		String schId = request.getParameter("schId");
		String type = request.getParameter("type");//动作类型，修改还是增加
		String advId = request.getParameter("advId");//广告id
		String adlId = request.getParameter("adlId");//广告优先级
		int res = 0;
		if (type.equals("add")) {
			TbAdvertisement addTbAdvertisement = new TbAdvertisement( Integer.valueOf(schId), advPath, null, advDescribe, Integer.valueOf(adlId));
			changeAdvertiseImage(request, addTbAdvertisement, newImg, type);//处理图片
			res = tbAdvertisementMapper.insertSelective(addTbAdvertisement);
			if (res>0) {
				result = "success";
			}else {
				result = "failed";
			}
		}else if (type.equals("change")) {
			TbAdvertisement changeTbAdvertisement = new TbAdvertisement(Integer.valueOf(advId), Integer.valueOf(schId), advPath, null, advDescribe, Integer.valueOf(adlId));
			changeAdvertiseImage(request, changeTbAdvertisement, newImg, type);//处理图片
			res = tbAdvertisementMapper.updateByPrimaryKeySelective(changeTbAdvertisement);
			if (res>0) {
				result = "success";
			}else {
				result = "failed";
			}
		}
		
		return result;
	}
	
	// 是否更新或者增加广告图片判断
	public void changeAdvertiseImage(HttpServletRequest request, TbAdvertisement tbAdvertisement, MultipartFile newImg, String type) {
		if ("" == newImg.getOriginalFilename()) {// 没有更新图片
			tbAdvertisement.setAdvImgpath(null);
		} else {// 有更新图片
			String fileName = System.currentTimeMillis() + "_" + newImg.getOriginalFilename();
			tbAdvertisement.setAdvImgpath("/images/advertise_img/"+fileName);
			String filePath = tbParameterMapper.selectParamter("系统文件存储路径");//获取系统文件储存路径
			String path = filePath+"/images/advertise_img/";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				newImg.transferTo(new File(path, fileName));// 插入图片
				if (type.equals("change")) {// 如果是修改图片，需要删除旧图片
					File deleteFile = new File(filePath + tbAdvertisementMapper.selectAdvertiseById(tbAdvertisement.getAdvId()).getAdvImgpath());
					if (deleteFile.exists()) {
						deleteFile.delete();
					}
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//删除广告
	@Override
	public MyResult deleteAdvertise(String advId) {
		MyResult myResult = new MyResult();
		String filePath = tbParameterMapper.selectParamter("系统文件存储路径");//获取系统文件储存路径
		File deleteFile = new File(filePath + tbAdvertisementMapper.selectAdvertiseById(Integer.valueOf(advId)).getAdvImgpath());
		if (deleteFile.exists()) {
			deleteFile.delete();
		}
		if (tbAdvertisementMapper.deleteByPrimaryKey(Integer.valueOf(advId))>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

}
