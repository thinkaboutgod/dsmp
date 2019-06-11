package com.dsmp.mapper;

import com.alipay.api.domain.Video;
import com.dsmp.pojo.PageResult;
import com.dsmp.pojo.TbVideo;
import java.util.List;

public interface TbVideoMapper {

	public int deleteByPrimaryKey(Integer vidId);//删除视频

	public int insert(TbVideo record);
	//根据科目分页查询视频
	public List<Object> searchVideoBySubect(String subject,int startIndex,int pagecount);
	//根据科目查询视频所有
	public List<Video> searchVideoBySubId(String subId);
	
	//查询总条数
	public PageResult countVideoBySubect(String subject);

	public int updateByPrimaryKeySelective(TbVideo record);//修改视频信息
	
	public TbVideo selectByPrimaryKey(Integer vidId);//查询单个视频对象
	
	public int insertVideo(TbVideo tbVideo);//插入新数据,增加视频
}