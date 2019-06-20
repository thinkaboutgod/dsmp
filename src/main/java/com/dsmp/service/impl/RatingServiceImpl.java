package com.dsmp.service.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsmp.mapper.TbRatingMapper;
import com.dsmp.pojo.TbRating;
import com.dsmp.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired private TbRatingMapper tbRatingMapper;
	

	@Override
	public List<TbRating> selectSchoolratingmsg(Integer schId, String choose) {
		if (choose.equals("所有评价")) {
			choose = null;
		}
		return tbRatingMapper.selectSchoolratingmsg(schId,choose);
	}
	
	/**学员对教练的评价插入评价表中
	 * @param coa_id 教练id
	 * @param stu_id 学员id
	 * @param starNum 几颗星（1-5）
	 * @param ratingContent(评价内容)
	 * @return
	 */
	@Transactional
	@Override
	public boolean addCoachRating(Integer coa_id, Integer stu_id, Integer starNum, String ratingContent) {
		boolean flag = false;
		String ratType = starJudge(starNum);
		TbRating tbRating = new TbRating(null, coa_id, null, stu_id, ratingContent, null, starNum, ratType);
		int res = tbRatingMapper.addCoachRating(tbRating);
		System.out.println("res:"+res);
		if(1==res) {
			flag = true;
			System.out.println("插入教练评价表成功！");
		}
		return flag;
	}
	
	/**根据几颗星算出评价等级ratType:4、5-好评；2、3-中评；0、1-差评
	 * @param starNum 几颗星
	 * @return 评价等级（好评，中评，差评）
	 */
	public String starJudge(Integer starNum) {
		String ratType = null;
		if(starNum>3) {//4、5
			ratType="好评";
		}else if(starNum>=2) {//2、3
			ratType="中评";			
		}else {//0、1
			ratType="差评";			
			
		}
		
		return ratType;
	}
	@Transactional
	@Override
	public boolean addSchoolRating(Integer sch_id, Integer stu_id, Integer starNum, String ratingContent) {
		boolean flag = false;
		String ratType = starJudge(starNum);
		TbRating tbRating = new TbRating(null, null, sch_id, stu_id, ratingContent, null, starNum, ratType);
		int res = tbRatingMapper.addSchoolRating(tbRating);
		if(1==res) {
			flag = true;
			System.out.println("插入驾校评价表成功！");
		}
		return flag;

	}

}
