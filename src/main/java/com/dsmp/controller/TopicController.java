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
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	private TopicService topicService;
	@Autowired 
	private HttpSession session;
	@RequestMapping(value="/findTopic.action")
	public ModelAndView findTopic(Integer top_id) {
		System.out.println("top_id："+top_id);
		ModelAndView mav = new ModelAndView();
		TbTopic topic = topicService.findTopic(2);

			System.out.println("题目："+topic.getTopTopic());
			System.out.println("选项："+topic.getOptions().toString());
			if(null!=topic.getOptions()) {
				for(TbOption option:topic.getOptions()) {
					System.out.println("选项："+option.getOptOption());
					System.out.println("选项对错："+option.getOptStatus());
					
				}
				
			}
				
			

		session.setAttribute("topic", topic);
		mav.setViewName("");
		
		return mav;
	}
	/**
	 * @return 随机出一张卷子(题目集合)
	 */
	@RequestMapping(value="/findManyTopic.action")
	public ModelAndView findManyTopic() {
		ModelAndView mav = new ModelAndView();
		List<TbTopic> topicList = topicService.findManyTopic(10);//topicList表示一张卷子题目集合；参数10表示一份卷子出10道题目
		for (TbTopic tbTopic : topicList) {
			System.out.println(":"+tbTopic.getTopTopic());
			for (TbOption option : tbTopic.getOptions()) {
				System.out.println("选项："+option.getOptOption());
				System.out.println("选项对错："+option.getOptStatus());
			}
			
			
		}
		session.setAttribute("topicList", topicList);
		mav.setViewName("client/examOfSubject1");
		return mav;
	}
	
}
