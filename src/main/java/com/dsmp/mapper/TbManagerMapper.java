package com.dsmp.mapper;

import com.dsmp.pojo.TbManager;

public interface TbManagerMapper {
	//验证后台登录
	public TbManager findManage(TbManager tbManage);
	
}