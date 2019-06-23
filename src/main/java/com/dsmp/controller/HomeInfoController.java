package com.dsmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.pojo.Count;
import com.dsmp.pojo.PageResult;
import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbNotice;
import com.dsmp.service.HomeInfoService;
import com.dsmp.service.ParameterService;
import com.dsmp.service.PlateformService;

@Controller
@RequestMapping("/home")
public class HomeInfoController {
	@Autowired private HomeInfoService homeInfoService;
	@Autowired private TbSchoolMapper tbSchoolMapper;
	@Autowired private TbCoachMapper TbCoachMapper;
	@Autowired private PlateformService plateformService;
	@Autowired 
	private HttpSession session;
	@Autowired
	private ParameterService parameterService;
	//主页科目一跳转相应界面
	@RequestMapping("/keyi")
	public ModelAndView getKeYiPage() {
		ModelAndView mav = new ModelAndView();
		//先把考试通过分数和模拟考答题时间间隔限制存入session中
		//1.得到考试通过分数：
		Integer passScore = findPassScore();
//		System.out.println("考试通过最低分数passScore:"+passScore);
		//2.得到考试答题时间间隔限制（单位毫秒s）
		Long timeLengthLimit = findTimeLengthLimit();
		session.setAttribute("timeLengthLimit", timeLengthLimit);//因为除了examOfSubject1用到这个答题时间间隔限制外keyi.jsp也有用到，所以存会花中		
		session.setAttribute("passScore", passScore);	
		

		mav.setViewName("client/keyi");
		return mav;
	}	
	//主页科目二/三跳转相应界面
	@RequestMapping("/kemu")
	public ModelAndView getKeErPage(String KeMuNum) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("KeMuNum",KeMuNum);
		mav.setViewName("client/videolist");
		return mav;
	}
	
	//去前台主页
	@RequestMapping("/main")
	public ModelAndView getHomeInfo(HttpServletRequest request) {
		request.getSession().setMaxInactiveInterval(120*60);
		ModelAndView mav = new ModelAndView();
		List<TbAdvertisement> advList = homeInfoService.getAdvertisement(1);
		List<TbNotice> notList = homeInfoService.getNotice(1);
		List<TbNotice> dynList = homeInfoService.getNotice(2);
		List<TbNotice> newsList = homeInfoService.getNotice(3);
		List<TbNotice> lawsList = homeInfoService.getNotice(4);
		List<TbHotlink> hotList = homeInfoService.getHotlink();
		String systemFilePath = plateformService.searchFilePathParameter();
		request.getSession().setAttribute("hotList", hotList);
		mav.addObject("advList", advList);
		mav.addObject("notList", notList);
		mav.addObject("newsList", newsList);
		mav.addObject("lawsList", lawsList);
		mav.addObject("dynList", dynList);
		mav.addObject("systemFilePath",systemFilePath);
		mav.setViewName("client/home");
		return mav;
	}	
	
	//驾校排行
	@RequestMapping("/schoolRanking")
	public @ResponseBody List<Count> getSchoolRanking(){
		List<Count> ranList = tbSchoolMapper.getSchoolRanking();		
		for(int i = 0;i<ranList.size();i++) {
			double starAvg = ranList.get(i).getStarAvg();
			ranList.get(i).setStarAvg(Math.round(starAvg));
		}
		return ranList;
	}
	//教练排行
	@RequestMapping("/coachRanking")
	public @ResponseBody List<Count> getCoachRanking(){
		List<Count> coaList = TbCoachMapper.getCoachRanking();	
		for(int i = 0;i<coaList.size();i++) {
			double starAvg = coaList.get(i).getStarAvg();
			coaList.get(i).setStarAvg(Math.round(starAvg));
		}
		return coaList;
	}
	//主页科目二/三跳转相应界面
	@RequestMapping("/logout")
	public ModelAndView getLogoutPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		request.getSession().removeAttribute("student");
		mav.setViewName("client/home");
		return mav;
	}	
	// 按科目查询学习视频
	@RequestMapping(value = "searchVideoBySubect.action")
	public @ResponseBody PageResult searchVideoBySubect(HttpServletResponse response, String subject, String page) {
//		response.setContentType("text/html;charset=utf-8");// 加上这个处理问号
		return plateformService.searchVideoBySubect(subject, page);
	}	
		
	//主页公告跳转相应信息界面
	@RequestMapping("/noticePage")
	public ModelAndView getNoticePage(Integer noticeId) {
		ModelAndView mav = new ModelAndView();
		TbNotice tbNotice = homeInfoService.getNoticeByNotId(noticeId);
		mav.addObject("notice",tbNotice);
		mav.setViewName("client/guide");
		return mav;
	}
	/***********ren star************/
	/**去参数表查出passScore，为科目一模拟考或正式考的通过分数，考试要大于这个分数才算通过
	 * @return
	 */
	public Integer findPassScore() {
		String passScoreStr = parameterService.searchParameterByName("passScore");
		Integer passScore = Integer.valueOf(passScoreStr);
		return passScore;
	}
	/**去参数表查出timeLengthLimit，为科目一模拟考的答题时间间隔，单位毫秒ms
	 * @return
	 */
	public Long findTimeLengthLimit() {
		String passScoreStr = parameterService.searchParameterByName("timeLengthLimit");//得到的是秒
		Long passScore = (Long.valueOf(passScoreStr))*1000;//秒转成毫秒要*1000
		return passScore;
	}
	/***********ren end************/
}
