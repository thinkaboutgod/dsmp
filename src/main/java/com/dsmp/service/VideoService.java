package com.dsmp.service;

import java.util.List;

import com.alipay.api.domain.Video;

public interface VideoService {
	public List<Video> searchVideoBySubId(String subId);
}
