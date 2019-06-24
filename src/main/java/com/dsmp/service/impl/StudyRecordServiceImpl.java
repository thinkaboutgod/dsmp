package com.dsmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsmp.mapper.TbStudyrecordMapper;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.service.StudyRecordService;
@Service
public class StudyRecordServiceImpl implements StudyRecordService {
	@Autowired
	private TbStudyrecordMapper tbStudyRecordMapper;
	@Override
	public boolean addStudyBeginTime(Integer stuId, Integer subId) {
		boolean addRes=false;
		int res = tbStudyRecordMapper.addStudyBeginTime(stuId, subId);
		if(res==1) {
			addRes=true;
		}
		return addRes;
	}
	@Transactional
	@Override
	public Map<String,Double> addStudyEndTime(Integer stuId, Integer subId) {
		Map<String,Double> studyTimeResMap=new HashMap<String,Double>();
		int res = tbStudyRecordMapper.addStudyEndTime(stuId, subId);
		if(res==1) {//插入结束时间成功，此时要计算学习时长符不符合规矩：当时长超过24小时，我们认为是挂机，时长记成0
			Double studyLength=tbStudyRecordMapper.findTimeLength(stuId, subId);//单位秒

//			System.out.println("学习时长studyLength为："+studyLength);//单位秒
			if(studyLength>=24*60*60) {//如果时长超过24小时，则插入学习记录表的时长为0（即此次学习时长无效）24*60*60
				studyLength=0.0;
				int addres = tbStudyRecordMapper.addTimeLength(stuId, subId,studyLength);
				if(addres==1) {
//					System.out.println("因为最近的一次学习有挂机嫌疑，插入时长为0！");
//					studyTimeResMap.put("cheat", null);//欺骗行为，学习时长无效
					studyTimeResMap = null;
				}
			}else {//如果在24小时之内，就把studyLength插入到时长记录
				int addres = tbStudyRecordMapper.addTimeLength(stuId, subId,studyLength);
				if(addres==1) {
//					System.out.println("合规，插入时长成功！");
					//时长有效就要计算一下目前的总时长，看是否完成学时
					Double currTotalTimeLength = tbStudyRecordMapper.sumTimeLength(stuId, subId);//当前学员科目一总学时（单位：秒）
//					System.out.println("当前学员科目一总学时："+currTotalTimeLength+"s");
					studyTimeResMap.put("currTotalTimeLength", currTotalTimeLength);//把当前总时长放入map（单位：秒）
					//放入科目一要求的总时长：
					studyTimeResMap.put("totalTimeLength", 10*60*60.0);//把科目一要求总时长放入map（单位：秒）
					
				}
				
			}
			
			
		}
		return studyTimeResMap;
	}

	@Override
	public Double sumTimeLength(Integer stuId, Integer subId) {
		return tbStudyRecordMapper.sumTimeLength(stuId, subId);
	}
	/** 通过学员id和科目名称查询科目所有的学习记录
	 * @param stuId 学员id
	 * @param subName 科目名称
	 * @return 该科目所有的学习记录
	 */
	@Override
	public List<TbStudyrecord> findStudyRecord(Integer stuId, String subName) {
		
		return tbStudyRecordMapper.findStudyRecord(stuId, subName);
	}

}
