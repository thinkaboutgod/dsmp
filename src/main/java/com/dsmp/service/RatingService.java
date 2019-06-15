package com.dsmp.service;

/**
 * 评价业务层
 *
 */
public interface RatingService {
	/**学员对教练的评价插入评价表中
	 * @param coa_id 教练id
	 * @param stu_id 学员id
	 * @param starNum 几颗星（1-5）
	 * @param ratingContent(评价内容)
	 * @return
	 */
	public boolean addCoachRating(Integer coa_id,Integer stu_id,Integer starNum,String ratingContent);
	public boolean addSchoolRating(Integer sch_id,Integer stu_id,Integer starNum,String ratingContent);
}
