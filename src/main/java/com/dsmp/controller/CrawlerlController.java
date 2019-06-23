package com.dsmp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.processor.TopicProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

@Controller
@RequestMapping("crawler")
public class CrawlerlController {

	@Autowired private TopicProcessor topicProcessor;
	
	@RequestMapping("startSpider")
	public @ResponseBody String startSpider() {
		Spider.create(topicProcessor).addUrl("http://www.jppt.com.cn/theory/queue-C1-1.html")
		.addPipeline(new FilePipeline("D:/data"))// 以文件方式保存
		.run();
		
		return "success";
	}
	
}

