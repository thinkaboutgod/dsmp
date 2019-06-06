package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbStudent;

public interface PlateformMapper {
	public List<TbStudent> searchAllstudent(SearchBean sBean);
	public List<TbStudent> searchAllstudent2(SearchBean sBean);
	public int changeStudentState(int stuId,String state);
	public int changeStudentStateLock(int stuId,String state);
}
