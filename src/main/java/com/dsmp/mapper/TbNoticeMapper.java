package com.dsmp.mapper;

import com.dsmp.pojo.TbNotice;
import com.dsmp.pojo.TbNoticeType;

import java.util.List;

public interface TbNoticeMapper {
	
	public List<TbNotice> selectByPrimaryKey();//查询所有
	
	public int deleteByPrimaryKey(Integer notId);//删除
	
	public int insertSelective(TbNotice tbNotice);//新增
	
	public List<TbNoticeType> selectAllType();//查询所有公告类型
	
	public int updateByPrimaryKeySelective(TbNotice tbNotice);//修改公告
	
}