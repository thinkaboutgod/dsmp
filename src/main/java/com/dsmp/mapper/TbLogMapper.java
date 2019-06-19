package com.dsmp.mapper;

import com.dsmp.pojo.TbLog;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbLogMapper {
	public List<TbLog> searchAllLog(@Param("logOperatoraccount") String logOperatoraccount,
			@Param("logRole") String logRole, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

	public int insertSelective(TbLog tbLog);//插入日志
}