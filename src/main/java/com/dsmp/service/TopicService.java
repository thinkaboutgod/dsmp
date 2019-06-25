package com.dsmp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbTopic;

public interface TopicService {
	public TbTopic findTopic(Integer topId);
	/**
	 * @param topicAmount 出题数量
	 * @return 随机产生一份题目数量为topicAmount的试卷
	 */
	public List<TbTopic> findManyTopic(Integer topicAmount,Integer subId);
	/**
	 * @param studentId 学员id
	 * @param exResultMap 学员提交一份模拟卷时候的map集合，key-题目id，value-该题学员做对了(yes)还是错了(no)
	 * 错了就往错题集表里插入记录，对了就看错题集里面是否有这条记录，有则删除。
	 */
	public void addOrDelMistakeCollection(Integer studentId,Map<String,String> exResultMap);
	/**通过学员id查询出该学员的错题集
	 * @param stuId 学员id
	 * @return 错题集
	 */
	public List<TbTopic> findMistakeTopic(Integer stuId);
	public List<TbTopic> findAllTopic(Integer subId);
	/**（单题目类型）选对时，加入错题集
	 * @param studentId 学员id
	 * @param subId 科目id
	 * @param topId 选对的题目id
	 */
	public void addMistakeCollection2exercise(Integer studentId,Integer subId,String topId);
	/**（单题目类型）做对删除错题集记录
	 * @param studentId 学员id
	 * @param subId 科目id
	 * @param topId 选对的题目id
	 */
	public void delMistakeCollection2exercise(Integer studentId,Integer subId,String topId);
	
	public List<TbTopic> searchAllTopic(String subId);//查询题库；
	
	public String changeTopic(HttpServletRequest request,MultipartFile newImg);//修改科目一题目

	public String addTopic(HttpServletRequest request,MultipartFile addnewImg);//增加科目一题目
	
	public MyResult deleteTopic(HttpServletRequest request,String topId);//删除题目
}
