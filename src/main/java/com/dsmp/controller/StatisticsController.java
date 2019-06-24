package com.dsmp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.TbOption;
import com.dsmp.pojo.TbTopic;
import com.dsmp.service.TopicService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private TopicService topicService;
	@Autowired 
	private HttpSession session;
	@RequestMapping(value="/findTopic.action")
	public ModelAndView findTopic(Integer top_id) {
		ModelAndView mav = new ModelAndView();
		TbTopic topic = topicService.findTopic(2);
			if(null!=topic.getOptions()) {
				for(TbOption option:topic.getOptions()) {
					
				}
				
			}
				
			

		session.setAttribute("topic", topic);
		mav.setViewName("");
		
		return mav;
	}
	@RequestMapping(value="/findManyTopic.action")
	public ModelAndView findManyTopic(Integer sub_id) {
		ModelAndView mav = new ModelAndView();
		List<TbTopic> topicList = topicService.findManyTopic(5,sub_id);//topicList表示一张卷子题目集合；参数10表示一份卷子出10道题目
		for (TbTopic tbTopic : topicList) {
			for (TbOption option : tbTopic.getOptions()) {
			}
			
			
		}
		session.setAttribute("topicList", topicList);
		mav.setViewName("client/examOfSubject1");
		return mav;
	}
	
}
