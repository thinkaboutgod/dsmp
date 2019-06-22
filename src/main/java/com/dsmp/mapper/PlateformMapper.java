package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbAppeal;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;

public interface PlateformMapper {
	public List<TbStudent> searchAllstudent(SearchBean sBean);//查询已报名学员
	public List<TbStudent> searchAllstudent2(SearchBean sBean);//查询未报名学员
	public int changeStudentState(int stuId,String state);//修改学员账号状态
	public int changeStudentStateLock(int stuId,String state);
	//查询申诉记录
	public List<TbAppeal> selectThecomplaint(SearchBean sBean);
	//申诉回复
	public int insertReply(String appReply,String appId);
}
