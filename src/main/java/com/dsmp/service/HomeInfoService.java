package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbNotice;

public interface HomeInfoService {
	//广告
	public List<TbAdvertisement> getAdvertisement();
	//公告service
	public List<TbNotice> getNotice();
	//公告service
	public List<TbHotlink> getHotlink();
}
