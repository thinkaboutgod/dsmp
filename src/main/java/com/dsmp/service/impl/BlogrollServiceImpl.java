package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsmp.mapper.TbHotlinkMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.service.BlogrollService;
@Service
public class BlogrollServiceImpl implements BlogrollService {
	@Autowired 
	private TbHotlinkMapper tbHotlinkMapper;
	@Autowired
	private MyResult myResult;
	@Override
	public List<TbHotlink> searchAllBlogRoll() {//查询所有友情链接
		
		return tbHotlinkMapper.searchAll();
	}
	@Transactional
	@Override
	public MyResult updateBlogroll(TbHotlink tbHotlink) {//更新友情链接
		int res = tbHotlinkMapper.updateByPrimaryKeySelective(tbHotlink);
		if (res>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	@Transactional
	@Override
	public MyResult deleteBlogroll(String holId) {//删除友情链接
		int res = tbHotlinkMapper.deleteByPrimaryKey(Integer.valueOf(holId));
		if (res>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	@Transactional
	@Override
	public MyResult insertBlogroll(TbHotlink tbHotlink) {//新增友情链接
		int res = tbHotlinkMapper.insertSelective(tbHotlink);
		if (res>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

}
