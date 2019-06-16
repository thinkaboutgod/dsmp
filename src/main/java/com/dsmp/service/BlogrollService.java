package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbHotlink;

public interface BlogrollService {

	public List<TbHotlink> searchAllBlogRoll();//查询友情链接
	
	public MyResult updateBlogroll(TbHotlink tbHotlink);//修改友情链接
	
	public MyResult deleteBlogroll(String holId);//删除友情链接
	
	public MyResult insertBlogroll(TbHotlink tbHotlink);//增加友情链接
}
