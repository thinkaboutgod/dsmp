package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsmp.mapper.TbNoticeMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbNotice;
import com.dsmp.pojo.TbNoticeType;
import com.dsmp.service.NoticeService;
/**   
 * @ClassName:  NoticeServiceImpl   
 * @Description:公告，行业动态，新闻，法规
 * @author: 
 * @date:   2019年6月20日 下午3:56:20   
 *   
 */ 
@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private MyResult myResult;
	@Autowired 
	private TbNoticeMapper tbNoticeMapper;
	
	//查询所有
	@Override
	public List<TbNotice> selectAllNotice() {
		
		return tbNoticeMapper.selectByPrimaryKey();
	}
	//删除
	@Transactional
	@Override
	public MyResult deleteNoticeById(Integer notId) {
		if (tbNoticeMapper.deleteByPrimaryKey(notId)>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	//新增
	@Transactional
	@Override
	public MyResult insertNotice(TbNotice tbNotice) {
		if (tbNoticeMapper.insertSelective(tbNotice)>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	//查询所有公告类型
	@Override
	public List<TbNoticeType> selectAllType() {
		return  tbNoticeMapper.selectAllType();//查询所有公告类型;
	}
	
	
	//更新公告
	@Transactional
	@Override
	public MyResult changeNotice(TbNotice tbNotice) {
		
		if (tbNoticeMapper.updateByPrimaryKeySelective(tbNotice)>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

}
