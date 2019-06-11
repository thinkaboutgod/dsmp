package com.dsmp.mapper;

import org.apache.ibatis.annotations.Param;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbStudyrecord;

public interface TbStudyrecordMapper {

	public int addStudyBeginTime(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
	public int addStudyEndTime(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
	public int findTimeLength(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
	public int addTimeLength(@Param("stuId")Integer stuId, @Param("subId")Integer subId,@Param("studyLength")Integer studyLength);
	public int sumTimeLength(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
    
    public Double countTimeByStuIdAndSubject(String stuId,String subId);//查询某学员该阶段已学时长
}