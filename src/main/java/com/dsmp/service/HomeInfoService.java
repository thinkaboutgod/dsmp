package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbNotice;

public interface HomeInfoService {
	//广告
	public List<TbAdvertisement> getAdvertisement(Integer adlId);
	//公告service
	public List<TbNotice> getNotice(Integer ntyId);
	//根据公告ID查询公告信息
	public TbNotice getNoticeByNotId(Integer notId);
	//友情链接service
	public List<TbHotlink> getHotlink();
}
