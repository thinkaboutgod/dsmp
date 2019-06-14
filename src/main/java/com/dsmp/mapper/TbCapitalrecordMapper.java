package com.dsmp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dsmp.pojo.TbCapitalrecord;

public interface TbCapitalrecordMapper {
	// 学员报名资金
	public int insertTbCapitalrecord(TbCapitalrecord tbCapitalrecord);
	//查询所有资金记录
	public List<TbCapitalrecord> searchMoneyRecord(@Param("capOrderNumber")String capOrderNumber,@Param("stuName") String stuName, @Param("schName")String schName,
			@Param("beginTime")String beginTime, @Param("endTime")String endTime);
}
