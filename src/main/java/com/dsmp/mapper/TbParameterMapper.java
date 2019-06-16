package com.dsmp.mapper;

import com.dsmp.pojo.TbParameter;

import java.util.List;


public interface TbParameterMapper {

	public List<TbParameter> selectAllParameter();//查询所有参数
	
	public int updataParmeter(TbParameter tbParameter);//更新参数
	
	public String selectParamter(String parName);//查询参数
}