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
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbTopic;
import com.dsmp.service.ParameterService;
import com.dsmp.service.PlateformService;
import com.dsmp.service.StudentService;
import com.dsmp.service.StudyRecordService;
import com.dsmp.service.SubjectScoreService;
import com.dsmp.service.SubjectService;
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
	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectScoreService subjectScoreService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private PlateformService plateformService;


	/**查询出题库里图片存放的路径
	 * @return 
	 */
	public String findTopicImgPath() {
		String filePath = plateformService.searchFilePathParameter();
		//查询出题库里图片存放的路径
		String topicImgFilePath = filePath+"/images/topic/";//得出：http://localhost:8080/images/topic/(依据：Servers/Tomcat v8.5 Server at localhost-config/server.xml/最后的docBase="C:\dsmpfile"--相当于"C:/dsmpfile/images/topic/")
//		System.out.println("filePath:"+filePath);
//		System.out.println("topicImgFilePath:"+topicImgFilePath);
		return topicImgFilePath;
	}
	@RequestMapping(value="/findTopic.action")
	public ModelAndView findTopic(Integer top_id) {
//		System.out.println("top_id："+top_id);
		ModelAndView mav = new ModelAndView();
		TbTopic topic = topicService.findTopic(2);
		
//			System.out.println("题目："+topic.getTopTopic());
//			System.out.println("选项："+topic.getOptions().toString());
/*			if(null!=topic.getOptions()) {
				for(TbOption option:topic.getOptions()) {
					System.out.println("选项："+option.getOptOption());
					System.out.println("选项对错："+option.getOptStatus());
					
				}
			}*/

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
//				System.out.println("学习开始时间插入成功！");
			}
		}
	}
	public Double sumTimeLength(Integer stu_id,Integer sub_id) {
		Double currTotalTimeLength=null;
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
	public String getPercentage(Double currTotalTimeLength,Double totalTimeLength) {
		 DecimalFormat df = new DecimalFormat("0.00%");
//		 Integer currTotalTimeLength = sumTimeLength(stu_id, sub_id);
//		 Integer totalTimeLength = 10*60*60;
		 String percentage = null;
		 if(null!=currTotalTimeLength && null!=totalTimeLength) {
//			 System.out.println(df.format((double)currTotalTimeLength/totalTimeLength)); 
			 percentage = df.format(currTotalTimeLength/totalTimeLength);
			 
		 }
		 return percentage;
	}
	/**
	 * @return 随机出一张卷子(题目集合)
	 */
	@RequestMapping(value="/findManyTopic.action")
	public ModelAndView findManyTopic() {
		
		
		Integer stu_id = null;//不用传值直接从session里面取（student）
		Integer sub_id = null;
		ModelAndView mav = new ModelAndView();
		TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
//		System.out.println("sessionStudeng:"+student);
		if(null!=student) {
//			System.out.println("sessionStuId:"+student.getStuId());
//			System.out.println("sessionSubId:"+student.getSubId());
//			System.out.println("sessionCoaId:"+student.getCoaId());
			student =  studentService.findStuById(student.getStuId());//这个student是登录时存入session里的，可能登录后交流已经修改了预约状态或则科目，所以这里有必要去数据库查询一下
			session.setAttribute("student", student);//覆盖掉原先的，现在是数据库中最新的数据
			stu_id = student.getStuId();
			sub_id = student.getSubId();
			if(null!=student.getSubId()&&student.getSubId()==1) {//只有在科目一（未登录或其他科目都不执行）时，才插入学习时间
				addStudyBeginTime(stu_id, sub_id);//进入模拟卷就插入开始学习时间
				Double currTotalTimeLength = sumTimeLength(stu_id, sub_id);//计算当前总学时
				mav.addObject("currTotalTimeLength", currTotalTimeLength);
				//查询出科目一需要的总学时：
				Double needStudyTime = subjectService.findNeedStudyTime(1);
//				System.out.println("needStudyTime:"+needStudyTime);
				
				mav.addObject("percentage", getPercentage(currTotalTimeLength,needStudyTime));//计算进度百分比
				mav.addObject("totalTimeLength", needStudyTime);//需要的总学时
			}
		}
			
		if(sub_id==null||sub_id!=1) {//如果是未登录或非科目一，也让试卷出来
			sub_id=1;
		}
		
		List<TbTopic> topicList = topicService.findManyTopic(100,sub_id);//topicList表示一张卷子题目集合；参数100表示一份卷子出100道题目
		for (TbTopic tbTopic : topicList) {
//			System.out.println(":"+tbTopic.getTopTopic());
			for (TbOption option : tbTopic.getOptions()) {
//				System.out.println("选项："+option.getOptOption());
//				System.out.println("选项对错："+option.getOptStatus());
			}			
			
		}
		//得到题库里存放图片的文件夹的路径
		String topicImgFilePath = findTopicImgPath();
/*		//得到考试通过分数：
		Integer passScore = findPassScore();
//		System.out.println("考试通过最低分数passScore:"+passScore);
		//得到考试答题时间间隔限制（单位毫秒s）
		Long timeLengthLimit = findTimeLengthLimit();
		session.setAttribute("timeLengthLimit", timeLengthLimit);//因为除了examOfSubject1用到这个答题时间间隔限制外keyi.jsp也有用到，所以存会花中
		
//		mav.addObject("timeLengthLimit", timeLengthLimit);
		mav.addObject("passScore", passScore);*/
		mav.addObject("topicImgFilePath", topicImgFilePath);
		mav.addObject("stu_id", stu_id);
		mav.addObject("sub_id", sub_id);
		mav.addObject("topicList", topicList);
		
		
		
		
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
/*		for (TbTopic tbTopic : allTopicList) {
			System.out.println(":"+tbTopic.getTopTopic());
			for (TbOption option : tbTopic.getOptions()) {
				System.out.println("选项："+option.getOptOption());
				System.out.println("选项对错："+option.getOptStatus());
			}			
		}*/
		//得到题库里存放图片的文件夹的路径
		String topicImgFilePath = findTopicImgPath();
		
		mav.addObject("topicImgFilePath", topicImgFilePath);
		
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
//		System.out.println("addMistakeCollection2exercise-studentId:"+studentId+",subId:"+subId);
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
//		System.out.println("delMistakeCollection2exercise-studentId:"+studentId+",subId:"+subId);
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
	public Map<String,Double> addOrDelMistakeCollection(@RequestParam Map<String,String> map,Integer studentId,Integer subId) {//点击了提交到这边来
//		System.out.println("进入把题目增加到错题集(或做对删除记录)的方法！");
//		System.out.println("studentId:"+map.get("studentId"));
//		System.out.println("subId:"+map.get("subId"));
		Map<String,Double> studyTimeResMap = null;
		if(null!=studentId) {//只有有学员id（即学员登录状态下）才能有错题集功能。（还要插入学习结束时间点到学习记录表中）
			//处理错题集相关
			Gson gson = new Gson();
			Map<Object, Object> examResultMap = gson.fromJson(map.get("jsonData"), Map.class);
//			System.out.println(examResultMap);		
			Map<String,String> exResultMap = (Map<String, String>) examResultMap.get("data");//key是topicId(题目id)，value是该学员选了选项是正确还是错误（yes/no）
//			System.out.println("exResultMap:"+exResultMap);
			
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
	public ModelAndView findMistakeTopic() {
		Integer stu_id = null;
		Integer sub_id = null;
 		ModelAndView mav = new ModelAndView();
		TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
		
		if(null==student) {//只有有学员id（即学员登录状态下）才能有错题集功能。
			
			mav.addObject("loginMessage", "请登录查看错题集！");
//			session.setAttribute("loginMessage", "请登录查看错题集！");
			
			mav.setViewName("client/mistakeCollectionOfSubject1");
			return mav;//如果满足，则不会执行下面的代码了，这里就会停下
		}else if(student.getSubId()==null) {
			mav.addObject("loginMessage", "需是报名账户才能查看错题集！");
			mav.setViewName("client/mistakeCollectionOfSubject1");
			return mav;
		}else if(student.getSubId()!=1) {
			mav.addObject("loginMessage", "您科目一已过，无科目一错题集！");
			mav.setViewName("client/mistakeCollectionOfSubject1");
			return mav;
		}
		stu_id = student.getStuId();
		sub_id = student.getSubId();
		addStudyBeginTime(stu_id, sub_id);//进入模拟卷就插入开始学习时间
		Double currTotalTimeLength = sumTimeLength(stu_id, sub_id);//计算总学时
		List<TbTopic> mistakeTopicList = topicService.findMistakeTopic(stu_id);//allTopicList表示查出题库里所有题目
		/*System.out.println("查询出错题集：");
		for (TbTopic tbTopic : mistakeTopicList) {
			System.out.println(":"+tbTopic.getTopTopic());
			for (TbOption option : tbTopic.getOptions()) {
				System.out.println("选项："+option.getOptOption());
				System.out.println("选项对错："+option.getOptStatus());
			}
			
			
		}*/
		//查询出科目一需要的总学时：
		Double needStudyTime = subjectService.findNeedStudyTime(1);
		//得到题库里存放图片的文件夹的路径
		String topicImgFilePath = findTopicImgPath();
		
		mav.addObject("topicImgFilePath", topicImgFilePath);
		mav.addObject("loginMessage", null);
		mav.addObject("stu_id", stu_id);
		mav.addObject("sub_id", sub_id);
		mav.addObject("mistakeTopicList", mistakeTopicList);
		mav.addObject("currTotalTimeLength", currTotalTimeLength);
//		mav.addObject("totalTimeLength", 10*60*60);
		mav.addObject("totalTimeLength", needStudyTime);
		mav.addObject("percentage", getPercentage(currTotalTimeLength,needStudyTime));
		
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

	/**修改状态：当科目一的当前学时大于等于科目一要求学时时，并且分数大于90，修改成"可预约"(js已经判断好了，才过来执行这边的)
	 * @param stuId 学员id
	 * @return
	 */
		@RequestMapping(value="/updateSubjectStatus.action")
	@ResponseBody
	public String updateSubjectStatus(Integer stuId) {
		String result = null;
		TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
/*		if(null!=student) {
			System.out.println("StuBookingstate:"+student.getStuBookingstate());
		}
		
		System.out.println("进入updateSubjectStatus:stuId-"+stuId);*/
		if(null!=stuId&&null!=student&&null!=student.getStuBookingstate()&&"未预约".equals(student.getStuBookingstate())) {//科目状态为"未预约"的时候，才有必要执行update语句：变成"可预约"-有了被教练安排考试资格
			boolean res = studentService.updateSubjectStatus(stuId,"可预约");
			if(res) {//更改成功！
//				student.setStuBookingstate("可预约");//把session中的student的StuSubjectstatus由lock改成canApply,学员便可以去申请考科目一了
//				System.out.println("更改成功，现在session中的subStatus:"+((TbStudent) session.getAttribute("student")).getStuSubjectstatus());
				result = "canApply";
			}
		}else if(null!=student&&null!=student.getStuBookingstate()&&"可预约".equals(student.getStuBookingstate())) {
			result = "canApply";
		}
		return result;
	}
		/**
		 * @return 随机出一张卷子(题目集合)(正式考试出卷：要插入分数到数据库的，判断有没有过)
		 */
		@RequestMapping(value="/trueFindManyTopic.action")
		public ModelAndView trueFindManyTopic(Integer stu_id,Integer sub_id) {
			ModelAndView mav = new ModelAndView();
			TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
			List<TbTopic> topicList = null;
//			System.out.println("sessionStudeng:"+student);
			if(null!=student) {
//				System.out.println("sessionStuId:"+student.getStuId());
//				System.out.println("sessionSubId:"+student.getSubId());
//				System.out.println("sessionCoaId:"+student.getCoaId());
//				System.out.println("Bookingstate:"+student.getStuBookingstate());//这个student是登录时存入session里的，可能登录后交流已经修改了预约状态，所以这里有必要去数据库查询一下
				student =  studentService.findStuById(student.getStuId());//这个student是登录时存入session里的，可能登录后交流已经修改了预约状态，所以这里有必要去数据库查询一下
				session.setAttribute("student", student);//覆盖掉原先的，现在是数据库中最新的数据
				stu_id = student.getStuId();
				sub_id = student.getSubId();
				//只有在科目状态为申请通过("已预约")，才允许考试
				if(null!=student.getSubId()&&null!=student.getStuBookingstate()&&"已预约".equals(student.getStuBookingstate())) {
					topicList = topicService.findManyTopic(10,sub_id);//topicList表示一张卷子题目集合；参数10表示一份卷子出10道题目
//					for (TbTopic tbTopic : topicList) {
//						System.out.println(":"+tbTopic.getTopTopic());
//						for (TbOption option : tbTopic.getOptions()) {
//							System.out.println("选项："+option.getOptOption());
//							System.out.println("选项对错："+option.getOptStatus());
//						}			
//						
//					}
					
				}
			}				
			//得到题库里存放图片的文件夹的路径
			String topicImgFilePath = findTopicImgPath();
			/*//得到考试通过分数：
			Integer passScore = findPassScore();
//			System.out.println("考试通过最低分数passScore:"+passScore);
			session.setAttribute("passScore", passScore);//因为除了trueExamOfSubject1用到这个通过分数外keyi.jsp也有用到，所以存会花中
*/			
//			mav.addObject("passScore", passScore);
			
			mav.addObject("topicImgFilePath", topicImgFilePath);
			mav.addObject("student", student);
			mav.addObject("topicList", topicList);
			mav.addObject("stu_id", stu_id);
			mav.addObject("sub_id", sub_id);
			

			mav.setViewName("client/trueExamOfSubject1");
			return mav;
		}
		/**正式考试插入分数到数据库
		 * @param stuId 学员id
		 * @param subId 科目id
		 * @param totalScore 考卷总分
		 * @return
		 */
		@RequestMapping(value="/addSubject1Score.action")
		@ResponseBody
		public String addSubject1Score(Integer stuId,Integer subId,Integer totalScore) {
			String result = null;
			System.out.println("进入addSubject1Score:stuId-"+stuId+",subId-"+subId+",totalScore-"+totalScore);
			if(null!=stuId&&null!=subId&&null!=totalScore) {//超过90分的才插入成绩
				boolean addres = subjectScoreService.addSubject1Score(stuId,subId,totalScore);
				if(addres) {//插入成绩成功！
					System.out.println("插入成绩成功！");
					result = "addSuccess";
				}
			}
			return result;
		}
		/**科目一正式考试没有通过，把科目预约状态由"已预约"改成"可预约",等教练再次给他预约考试
		 * @param stuId 学员id
		 * @param subId 科目id
		 * @return
		 */
		@RequestMapping(value="/noPass.action")
		@ResponseBody
		public String noPass(Integer stuId,Integer subId) {
			String result = null;
			TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
//			System.out.println("进入noPass:stuId-"+stuId+",subId-"+subId);
			if(null!=stuId&&null!=subId) {
				boolean res = studentService.updateSubjectStatus(stuId,"可预约");
				
				if(res) {//修改成功！
//					System.out.println("修改成功！");
					student.setStuBookingstate("可预约");//把session中的student的科目预约状态由"已预约"改成"可预约",等教练再次给他预约考试
					result = "success";
				}
			}
			return result;
		}
		/**科目一正式考试通过，把学员的科目预约状态由"已预约"改成"未预约",科目1改成2
		 * @param stuId 学员id
		 * @param subId 科目id
		 * @return
		 */
		@RequestMapping(value="/pass.action")
		@ResponseBody
		public String pass(Integer stuId,Integer subId) {
			String result = null;
			TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
//			System.out.println("进入noPass:stuId-"+stuId+",subId-"+subId);
			if(null!=stuId&&null!=subId) {
				boolean res = studentService.updateSubjectStatusAndSubId(stuId,"未预约",2);
				
				if(res) {//修改成功！
//					System.out.println("修改成功！");
					student.setStuBookingstate("未预约");//把session中的student的科目预约状态由"已预约"改成"可预约",等教练再次给他预约考试
					result = "success";
				}
			}
			return result;
		}
		/**查出数据库当前最新的student（查出来的student把登录时存入session的student覆盖掉）
		 * @param stuId 学员id
		 * @return
		 */
		@RequestMapping(value="/findNewStudent.action")
		@ResponseBody
		public TbStudent findNewStudent() {
			TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
			if(null!=student) {
				student =  studentService.findStuById(student.getStuId());//这个student是登录时存入session里的，可能登录后交流已经修改了预约状态，所以这里有必要去数据库查询一下
				session.setAttribute("student", student);//覆盖掉原先的，现在是数据库中最新的数据
			}
			return student;
		}
		
}
