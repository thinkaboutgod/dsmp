package com.dsmp.mapper;

import org.apache.ibatis.annotations.Param;

public interface TbStudyRecordMapper {
	public int addStudyBeginTime(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
	public int addStudyEndTime(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
	public int findTimeLength(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
	public int addTimeLength(@Param("stuId")Integer stuId, @Param("subId")Integer subId,@Param("studyLength")Integer studyLength);
	public int sumTimeLength(@Param("stuId")Integer stuId, @Param("subId")Integer subId);
}
