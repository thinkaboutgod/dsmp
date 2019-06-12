package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbNotice;

public interface HomeInfoMapper {
	//广告查询
	public List<TbAdvertisement> getAdvertisement();
	//公告查询
	public List<TbNotice> findNotice();
	//友情链接
	public List<TbHotlink> findHotlink();
}
