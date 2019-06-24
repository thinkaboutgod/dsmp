package com.dsmp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsmp.mapper.TbOptionMapper;
import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.mapper.TbTopicMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbMistakeCollection;
import com.dsmp.pojo.TbOption;
import com.dsmp.pojo.TbTopic;
import com.dsmp.service.MistakeCollectionService;
import com.dsmp.service.TopicService;
import com.dsmp.utils.GsonUtils;

@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TbTopicMapper tbTopicMapper;
	@Autowired
	private MistakeCollectionService mistakeCollectionService;

	@Autowired
	private TbOptionMapper tbOptionMapper;

	@Autowired
	private TbParameterMapper tbParameterMapper;
	
	@Override
	public TbTopic findTopic(Integer topId) {

		return tbTopicMapper.findTopic(topId);
	}

	/**
	 * @param topicAmount 出题数量
	 * @return 随机产生一份题目数量为topicAmount的试卷
	 */
	@Override
	public List<TbTopic> findManyTopic(Integer topicAmount,Integer subId) {
		List<TbTopic> topicList = tbTopicMapper.findManyTopic(subId);//从数据库题目表查出所有试题
		topicList = randomTopic(topicList,topicAmount);		
		return topicList;
	}

	/**
	 * 从数据库题库里随机产生一张考卷，题目数量是(出一张卷子出几道题)topicAmount
	 * 
	 * @param topicList   从数据库题目表查出所有的题目（包括题目下的选项）
	 * @param topicAmount 出题数量
	 * @return 产生的一套题量为topicAmount的模拟卷(如果出题量topicAmount小于等于题库题目数量则按出题量出题；如果出题量topicAmount大于题库题目数量，则按题库已有数量出题)
	 */
	public List<TbTopic> randomTopic(List<TbTopic> topicList, Integer topicAmount) {
		List<TbTopic> randomTopicList = new ArrayList<TbTopic>();
		Random ran = new Random();
//		System.out.println("题库题目数量:" + topicList.size());
		if (topicList.size() >= topicAmount) {// 如果题库题目数比出题数topicAmount大（或等于），则可以出题
			for (int i = 0; i < topicAmount; i++) {// 循环次数topicAmount，每次从题库拿到一道题放入到我的卷子中
				int ranIndex = ran.nextInt(topicList.size());
				TbTopic topic = topicList.get(ranIndex);
				randomTopicList.add(topic);
				// 把已经插入的题目从集合移除掉
				topicList.remove(ranIndex);

			}

		} else {// 如果出题数量大于题库题数，则把所有的题目都出掉
			randomTopicList = topicList;
		}
		return randomTopicList;

	}

	/**
	 * @param studentId   学员id
	 * @param exResultMap 学员提交一份模拟卷时候的map集合，key-题目id，value-该题学员做对了(yes)还是错了(no)
	 *                    错了就往错题集表里插入记录，对了就看错题集里面是否有这条记录，有则删除。
	 */
	@Transactional
	@Override
	public void addOrDelMistakeCollection(Integer studentId, Map<String, String> exResultMap) {
		Set<String> keySet = exResultMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String topId = iterator.next();
			String optionRes = exResultMap.get(topId);
//			System.out.println("topId:" + topId + ",optionRes:" + optionRes);
			// 先查看一下这条记录是否已经存在：
			TbMistakeCollection mistakeCollection = mistakeCollectionService.findMistakeTopicBySidAndTopId(studentId,
					topId);
			boolean topicExist = true;// 错题集存在这题
			if (null == mistakeCollection) {
				topicExist = false;// 错题集不存在这题
			}
			if ("no".equals(optionRes)) {// 如果这一题选错的话
				// 记录插入到错题集表里：
				if (!topicExist) {// 错题集不存在这题
//					System.out.println("错题集不存在这题,可执行插入：");
					boolean addres = mistakeCollectionService.addMistakeTopic(studentId, topId);
					if (addres) {
//						System.out.println("插入studentId&topId：" + studentId + "," + topId + "成功！");
					}
				} else {
//					System.out.println("学员做错了，但错题集已经存在这题，无需插入！");
				}

			} else if ("yes".equals(optionRes)) {// 如果这一题选对的话
				// 从错题集中删除这条记录：
				if (topicExist) {// 错题集存在这题
//					System.out.println("学员做对了，错题集存在这题,可执行删除：");
					boolean delres = mistakeCollectionService.delMistakeTopic(studentId, topId);
					if (delres) {
//						System.out.println("删除studentId&topId：" + studentId + "," + topId + "成功！");
					}
				}

			}

		}

	}
	/**通过学员id查询出该学员的错题集
	 * @param stuId 学员id
	 * @return 错题集
	 */
	@Override
	public List<TbTopic> findMistakeTopic(Integer stuId) {

		return tbTopicMapper.findMistakeTopic(stuId);
	}

	@Override
	public List<TbTopic> findAllTopic(Integer subId) {
		List<TbTopic> topicList = tbTopicMapper.findManyTopic(subId);//从数据库题目表查出所有试题
		return topicList;
	}
	/**（单题目类型）选错时，加入错题集
	 * @param studentId 学员id
	 * @param subId 科目id
	 * @param topId 选对的题目id
	 */
	@Transactional
	@Override
	public void addMistakeCollection2exercise(Integer studentId, Integer subId, String topId) {
		//先查看一下这条记录是否已经存在：
		TbMistakeCollection mistakeCollection = mistakeCollectionService.findMistakeTopicBySidAndTopId(studentId, topId);
		boolean topicExist = true;//错题集存在这题
		if(null==mistakeCollection) {
			topicExist = false;//错题集不存在这题
		}
		//记录插入到错题集表里：
		if(!topicExist) {//错题集不存在这题
//			System.out.println("错题集不存在这题,可执行插入：");
			boolean addres = mistakeCollectionService.addMistakeTopic(studentId, topId);
			if(addres) {
//				System.out.println("插入studentId&topId："+studentId+","+topId+"成功！");
			}
		}else {
//			System.out.println("学员做错了，但错题集已经存在这题，无需插入！");
		}
		
	}
	/**（单题目类型）选对时，加入错题集
	 * @param studentId 学员id
	 * @param subId 科目id
	 * @param topId 选对的题目id
	 */
	@Transactional
	@Override
	public void delMistakeCollection2exercise(Integer studentId, Integer subId, String topId) {
		//先查看一下这条记录是否已经存在：
		TbMistakeCollection mistakeCollection = mistakeCollectionService.findMistakeTopicBySidAndTopId(studentId, topId);
		boolean topicExist = true;//错题集存在这题
		if(null==mistakeCollection) {
			topicExist = false;//错题集不存在这题
		}
		//从错题集中删除这条记录：
		if(topicExist) {//错题集存在这题
//			System.out.println("学员做对了，错题集存在这题,可执行删除：");
			boolean delres = mistakeCollectionService.delMistakeTopic(studentId, topId);
			if(delres) {
//				System.out.println("删除studentId&topId："+studentId+","+topId+"成功！");
			}
		}
		
	}

	// 查询所有科一题目
	@Override
	public List<TbTopic> searchAllTopic(String subId) {
		return tbTopicMapper.findManyTopic(Integer.valueOf(subId));
	}

	// 修改科目一题目方法
	@Override
	public String changeTopic(HttpServletRequest request, String map, MultipartFile newImg) {

		return doTOpic(request, map, newImg, "change");
	}

	// 增加科目一题目方法
	@Override
	public String addTopic(HttpServletRequest request, String map, MultipartFile addnewImg) {

		return doTOpic(request, map, addnewImg, "add");
	}

	// 科目一题目修改和增加共用
	@Transactional // 事务注解，dotype为修改还是增加的类型
	public String doTOpic(HttpServletRequest request, String map, MultipartFile img, String doType) {
		String result = null;
		Map<String, Object> map2 = GsonUtils.fromJson(map, HashMap.class);
		TbTopic tbTopic = GsonUtils.fromJson(map2.get("topic").toString(), TbTopic.class);
		TbOption optionA = GsonUtils.fromJson(map2.get("optionA").toString(), TbOption.class);
		TbOption optionB = GsonUtils.fromJson(map2.get("optionB").toString(), TbOption.class);
		String type = String.valueOf(map2.get("type"));
		changeTopicImage(request, tbTopic, img, doType);// 是否更新图片方法
		if (type.equals("1.0") || type.equals("2.0")) {// 4个选项类型
			TbOption optionC = GsonUtils.fromJson(map2.get("optionC").toString(), TbOption.class);
			TbOption optionD = GsonUtils.fromJson(map2.get("optionD").toString(), TbOption.class);
			if (optionA.getOptStatus().equals("yes")) {// 得到正确选项是哪个
				tbTopic.setTopAnswer("A");
			} else if (optionB.getOptStatus().equals("yes")) {
				tbTopic.setTopAnswer("B");
			} else if (optionC.getOptStatus().equals("yes")) {
				tbTopic.setTopAnswer("C");
			} else {
				tbTopic.setTopAnswer("D");
			}

			int tb = 0, a = 0, b = 0, c = 0, d = 0;
			switch (doType) {
			case "change":// 修改题目
				tb = tbTopicMapper.updateByPrimaryKeySelective(tbTopic);// 更新题目记录
				a = tbOptionMapper.updateByPrimaryKeySelective(optionA);// 更新选项
				b = tbOptionMapper.updateByPrimaryKeySelective(optionB);
				c = tbOptionMapper.updateByPrimaryKeySelective(optionC);
				d = tbOptionMapper.updateByPrimaryKeySelective(optionD);
				break;
			case "add":// 增加题目
				tbTopic.setSubId(1);
				tb = tbTopicMapper.insertSelective(tbTopic);// 插入题目
				int topId = tbTopicMapper.selecttopId();// 查询刚插入题目的id
				optionA.setTopId(topId);
				optionB.setTopId(topId);
				optionC.setTopId(topId);
				optionD.setTopId(topId);
				a = tbOptionMapper.insertSelective(optionA);// 插入选项
				b = tbOptionMapper.insertSelective(optionB);
				c = tbOptionMapper.insertSelective(optionC);
				d = tbOptionMapper.insertSelective(optionD);
				break;
			}

			if (tb > 0 && a > 0 && b > 0 && c > 0 && d > 0) {
				result = "success";
			} else {
				result = "false";
			}

		} else {// 2个选项题目类型
			if (optionA.getOptStatus().equals("yes")) {// 得到正确选项是哪个
				tbTopic.setTopAnswer("A");
			} else if (optionB.getOptStatus().equals("yes")) {
				tbTopic.setTopAnswer("B");
			}
			int tb = 0, a = 0, b = 0;
			switch (doType) {
			case "change":
				tb = tbTopicMapper.updateByPrimaryKeySelective(tbTopic);// 更新题目记录
				a = tbOptionMapper.updateByPrimaryKeySelective(optionA);// 更新选项
				b = tbOptionMapper.updateByPrimaryKeySelective(optionB);
				break;
			case "add":
				tbTopic.setSubId(1);
				tb = tbTopicMapper.insertSelective(tbTopic);// 插入题目
				int topId = tbTopicMapper.selecttopId();// 查询刚插入题目的id
				optionA.setTopId(topId);
				optionB.setTopId(topId);
				a = tbOptionMapper.insertSelective(optionA);// 插入选项
				b = tbOptionMapper.insertSelective(optionB);
				break;
			}

			if (tb > 0 && a > 0 && b > 0) {
				result = "success";
			} else {
				result = "false";
			}
		}
		return result;
	}

	// 是否更新图片判断
	public void changeTopicImage(HttpServletRequest request, TbTopic tbTopic, MultipartFile newImg, String doType) {
		if ("" == newImg.getOriginalFilename()) {// 没有更新图片
			tbTopic.setTopImg(null);
		} else {// 有更新图片
			String fileName = System.currentTimeMillis() + "_" + newImg.getOriginalFilename();
			tbTopic.setTopImg(fileName);
			String filePath = tbParameterMapper.selectParamter("系统文件存储路径");//获取系统文件储存路径
			String path = filePath+"/images/topic/";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				newImg.transferTo(new File(path, fileName));// 插入图片
				if (doType.equals("change")) {// 如果是修改图片，需要删除旧图片
					String deleteFileName = tbTopicMapper.selecttopImg(tbTopic.getTopId());
					File deleteFile = new File(path + deleteFileName);
					if (deleteFile.exists()) {
						deleteFile.delete();
					}
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 删除题目
	@Transactional
	@Override
	public MyResult deleteTopic(HttpServletRequest request, String topId) {
		int op = tbOptionMapper.deleteByTopId(Integer.valueOf(topId));// 删除选项,先删选项，因为和题目主外键关系
		int res = tbTopicMapper.deleteByPrimaryKey(Integer.valueOf(topId));
		
		MyResult myResult = new MyResult();
		if (res > 0 && op > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

}
