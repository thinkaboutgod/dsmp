package com.dsmp.controller;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.TbOption;
import com.dsmp.pojo.TbTopic;
import com.dsmp.service.StudyRecordService;
import com.dsmp.service.TopicService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired 
	private HttpSession session;
	@Autowired
	private TopicService topicService;
	@Autowired
	private StudyRecordService studyRecordService;
	
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
				
			

		mav.addObject("topic", topic);
//		session.setAttribute("topic", topic);
		mav.setViewName("");
		
		return mav;
	}
	/**进入模拟卷，错题集等就插入开始学习时间
	 * @param stu_id
	 * @param sub_id
	 */
	public void addStudyBeginTime(Integer stu_id,Integer sub_id) {
		if(null!=stu_id) {//如果是登录状态，就要计入学时（插入开始学习时间到学习记录表）
			boolean addres = studyRecordService.addStudyBeginTime(stu_id, sub_id);
			if(addres) {
				System.out.println("学习开始时间插入成功！");
			}
		}
	}
	public Integer sumTimeLength(Integer stu_id,Integer sub_id) {
		Integer currTotalTimeLength=null;
		if(null!=stu_id) {//如果是登录状态，则查询该学员总学时
			currTotalTimeLength = studyRecordService.sumTimeLength(stu_id,sub_id);
		}
		return currTotalTimeLength;
	}
	/**算出百分比
	 * @param currTotalTimeLength
	 * @param totalTimeLength
	 * @return
	 */
	public String getPercentage(Integer currTotalTimeLength,Integer totalTimeLength) {
		 DecimalFormat df = new DecimalFormat("0.00%");
//		 Integer currTotalTimeLength = sumTimeLength(stu_id, sub_id);
//		 Integer totalTimeLength = 10*60*60;
		 String percentage = null;
		 if(null!=currTotalTimeLength && null!=totalTimeLength) {
			 System.out.println(df.format((double)currTotalTimeLength/totalTimeLength)); 
			 percentage = df.format((double)currTotalTimeLength/totalTimeLength);
			 
		 }
		 return percentage;
	}
	/**
	 * @return 随机出一张卷子(题目集合)
	 */
	@RequestMapping(value="/findManyTopic.action")
	public ModelAndView findManyTopic(Integer stu_id,Integer sub_id) {
		ModelAndView mav = new ModelAndView();
		addStudyBeginTime(stu_id, sub_id);//进入模拟卷就插入开始学习时间
		Integer currTotalTimeLength = sumTimeLength(stu_id, sub_id);//计算总学时
		List<TbTopic> topicList = topicService.findManyTopic(10,sub_id);//topicList表示一张卷子题目集合；参数10表示一份卷子出10道题目
		for (TbTopic tbTopic : topicList) {
			System.out.println(":"+tbTopic.getTopTopic());
			for (TbOption option : tbTopic.getOptions()) {
				System.out.println("选项："+option.getOptOption());
				System.out.println("选项对错："+option.getOptStatus());
			}			
			
		}
		
		mav.addObject("stu_id", stu_id);
		mav.addObject("sub_id", sub_id);
		mav.addObject("topicList", topicList);
		mav.addObject("currTotalTimeLength", currTotalTimeLength);
		mav.addObject("totalTimeLength", 10*60*60);
		mav.addObject("percentage", getPercentage(currTotalTimeLength,10*60*60));
		
/*		session.setAttribute("stu_id", stu_id);
		session.setAttribute("sub_id", sub_id);
		session.setAttribute("topicList", topicList);
		session.setAttribute("currTotalTimeLength", currTotalTimeLength);
		session.setAttribute("totalTimeLength", 10*60*60);
		session.setAttribute("percentage", getPercentage(currTotalTimeLength,10*60*60));*/
		mav.setViewName("client/examOfSubject1");
		return mav;
	}
	/**
	 * @return 题库里所有题目（一题一题显示）
	 */
	@RequestMapping(value="/findAllTopic.action")
	public ModelAndView findAllTopic(Integer stu_id,Integer sub_id) {
		ModelAndView mav = new ModelAndView();
		List<TbTopic> allTopicList = topicService.findAllTopic(sub_id);//allTopicList表示查出题库里所有题目
		for (TbTopic tbTopic : allTopicList) {
			System.out.println(":"+tbTopic.getTopTopic());
			for (TbOption option : tbTopic.getOptions()) {
				System.out.println("选项："+option.getOptOption());
				System.out.println("选项对错："+option.getOptStatus());
			}			
		}
		mav.addObject("stu_id", stu_id);
		mav.addObject("sub_id", sub_id);
		mav.addObject("allTopicList", allTopicList);
		
/*		session.setAttribute("stu_id", stu_id);
		session.setAttribute("sub_id", sub_id);
		session.setAttribute("allTopicList", allTopicList);*/
		mav.setViewName("client/exerciseOfSubject1");
		return mav;
		
	}
	@RequestMapping(value="/findAllTopicJs.action",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody List<TbTopic> findAllTopicJs(Integer sub_id){
		List<TbTopic> allTopicList = topicService.findAllTopic(sub_id);//allTopicList表示查出题库里所有题目
		return allTopicList;
	}
	/**科目练习题（单题目类型）做错加入错题集
	 * @param studentId 学时id
	 * @param subId 科目id
	 */
	@RequestMapping(value="/addMistakeCollection2exercise.action")
	@ResponseBody
	public String addMistakeCollection2exercise(Integer studentId,Integer subId,String topId) {
		System.out.println("addMistakeCollection2exercise-studentId:"+studentId+",subId:"+subId);
		if(null!=studentId) {//只有有学员id（即学员登录状态下）才能有错题集功能。
			topicService.addMistakeCollection2exercise(studentId,subId,topId);
		}
		return "success";
	}
	/**科目练习题（单题目类型）做对删除错题集记录
	 * @param studentId 学时id
	 * @param subId 科目id
	 */
	@RequestMapping(value="/delMistakeCollection2exercise.action")
	@ResponseBody
	public String delMistakeCollection2exercise(Integer studentId,Integer subId,String topId) {
		System.out.println("delMistakeCollection2exercise-studentId:"+studentId+",subId:"+subId);
		if(null!=studentId) {//只有有学员id（即学员登录状态下）才能有错题集功能。
			topicService.delMistakeCollection2exercise(studentId,subId,topId);
		}
		return "success";
	}
	/**对学员做完一份试卷的错题集的整理，做错了插入错题集，做对了看一下错题集里面有没有，有则删除。（模拟卷类型）
	 * @param map 传过来的包含学生做一份卷子的对错集合。主要传递了学员答题的题目id和本题的对错情况的map集合，但是要处理一下
	 * @param studentId 哪个学员登录的，这边就是谁的stuId
	 */
	@RequestMapping(value="/addOrDelMistakeCollection.action")
	@ResponseBody
	public Map<String,Integer> addOrDelMistakeCollection(@RequestParam Map<String,String> map,Integer studentId,Integer subId) {//点击了提交到这边来
		System.out.println("进入把题目增加到错题集(或做对删除记录)的方法！");
		System.out.println("studentId:"+map.get("studentId"));
		System.out.println("subId:"+map.get("subId"));
		Map<String,Integer> studyTimeResMap = null;
		if(null!=studentId) {//只有有学员id（即学员登录状态下）才能有错题集功能。（还要插入学习结束时间点到学习记录表中）
			//处理错题集相关
			Gson gson = new Gson();
			Map<Object, Object> examResultMap = gson.fromJson(map.get("jsonData"), Map.class);
			System.out.println(examResultMap);		
			Map<String,String> exResultMap = (Map<String, String>) examResultMap.get("data");//key是topicId(题目id)，value是该学员选了选项是正确还是错误（yes/no）
			System.out.println("exResultMap:"+exResultMap);
			
			topicService.addOrDelMistakeCollection(studentId,exResultMap);
			
			//处理插入学习结束时间点相关
			studyTimeResMap = studyRecordService.addStudyEndTime(studentId, subId);

//			session.setAttribute("studyTimeResMap", studyTimeResMap);
		}
		return studyTimeResMap;
		
		
	}
	/**一定是学员登录状态下才执行这个方法
	 * @param stu_id 学员id
	 * @return mistakeTopicList是该学员的错题集，到错题集页面
	 */
	@RequestMapping(value="/findMistakeTopic.action")
	public ModelAndView findMistakeTopic(Integer stu_id,Integer sub_id) {
		ModelAndView mav = new ModelAndView();
		
		if(null==stu_id) {//只有有学员id（即学员登录状态下）才能有错题集功能。
			
			mav.addObject("loginMessage", "请登录查看错题集！");
//			session.setAttribute("loginMessage", "请登录查看错题集！");
			
			mav.setViewName("client/mistakeCollectionOfSubject1");
			return mav;//如果null==stu_id，则不会执行下面的代码了，这里就会停下
		}
		addStudyBeginTime(stu_id, sub_id);//进入模拟卷就插入开始学习时间
		Integer currTotalTimeLength = sumTimeLength(stu_id, sub_id);//计算总学时
		List<TbTopic> mistakeTopicList = topicService.findMistakeTopic(stu_id);//allTopicList表示查出题库里所有题目
		System.out.println("查询出错题集：");
		for (TbTopic tbTopic : mistakeTopicList) {
			System.out.println(":"+tbTopic.getTopTopic());
			for (TbOption option : tbTopic.getOptions()) {
				System.out.println("选项："+option.getOptOption());
				System.out.println("选项对错："+option.getOptStatus());
			}
			
			
		}
		mav.addObject("loginMessage", null);
		mav.addObject("stu_id", stu_id);
		mav.addObject("sub_id", sub_id);
		mav.addObject("mistakeTopicList", mistakeTopicList);
		mav.addObject("currTotalTimeLength", currTotalTimeLength);
		mav.addObject("totalTimeLength", 10*60*60);
		mav.addObject("percentage", getPercentage(currTotalTimeLength,10*60*60));
		
/*		session.setAttribute("loginMessage", null);
		session.setAttribute("stu_id", stu_id);
		session.setAttribute("sub_id", sub_id);
		session.setAttribute("mistakeTopicList", mistakeTopicList);
		session.setAttribute("currTotalTimeLength", currTotalTimeLength);
		session.setAttribute("totalTimeLength", 10*60*60);
		session.setAttribute("percentage", getPercentage(currTotalTimeLength,10*60*60));*/
		mav.setViewName("client/mistakeCollectionOfSubject1");
		return mav;
		
	}
}
