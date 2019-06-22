package com.dsmp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.dsmp.pojo.TbAdvLevel;
import com.dsmp.pojo.TbAdvertisement;

public interface TbAdvertisementMapper {
	
	public List<Object> selectAdvertise(@Param("adlId")String adlId,int startIndex,int pagecount);

	//查询总条数
	public int countAdvertise(@Param("adlId")String adlId);
	//查询广告等级对照表
	public List<TbAdvLevel> selectLevel();
	//根据id查广告
	public TbAdvertisement selectAdvertiseById(Integer advId);
	//新增插入广告
	public int insertSelective(TbAdvertisement tbAdvertisement);
	//更新广告信息
	public int updateByPrimaryKeySelective(TbAdvertisement tbAdvertisement);
	//删除
	public int deleteByPrimaryKey(Integer advId);
}