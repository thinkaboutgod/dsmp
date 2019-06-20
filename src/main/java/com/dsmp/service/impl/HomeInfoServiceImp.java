package com.dsmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.HomeInfoMapper;
import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbNotice;
import com.dsmp.service.HomeInfoService;
@Service
public class HomeInfoServiceImp implements HomeInfoService{
	@Autowired private HomeInfoMapper homeInfoMapper;
	
	@Override
	public List<TbAdvertisement> getAdvertisement(Integer adlId) {
		return homeInfoMapper.getAdvertisement(adlId);
	}

	@Override
	public List<TbNotice> getNotice(Integer ntyId) {
		List<TbNotice> noticeList = new ArrayList<>();
		noticeList = homeInfoMapper.findNotice(ntyId);
		return noticeList;
	}

	@Override
	public List<TbHotlink> getHotlink() {
		List<TbHotlink> hotlinkList = new ArrayList<>();
		hotlinkList = homeInfoMapper.findHotlink();
		return hotlinkList;
	}

	@Override
	public TbNotice getNoticeByNotId(Integer notId) {
		TbNotice tbNotice = homeInfoMapper.findNoticeByNotId(notId);
		return tbNotice;
	}

}
