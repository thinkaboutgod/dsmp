package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbNotice;
import com.dsmp.pojo.TbNoticeType;

public interface NoticeService {

	public List<TbNotice> selectAllNotice();//查询所有
	
	public MyResult deleteNoticeById(Integer notId);//删除
	
	public MyResult insertNotice(TbNotice tbNotice);//新增
	
	public List<TbNoticeType> selectAllType();//查询所有公告类型
	
	public MyResult changeNotice(TbNotice tbNotice);//修改公告
	
}
