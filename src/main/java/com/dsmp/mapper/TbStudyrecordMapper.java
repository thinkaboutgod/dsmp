package com.dsmp.mapper;

import java.util.List;
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
    
    public List<TbStudyrecord> selectNowDayRecord(String stuId, String subId);//查询该学员今天最新的一条学习记录
    
    public int insertStudyRecord(String stuId, String subId);//插入该学员学习记录
    
    public int updateByPrimaryKeySelective(TbStudyrecord tbStudyrecord);//更新学习记录,有效的
    
    public int updatefalse(int str_id);//更新学习记录,无效的
    
    public int updateNormal(int str_id,double str_time);//更新学习记录,有效
	/** 通过学员id和科目名称查询科目所有的学习记录
	 * @param stuId 学员id
	 * @param subName 科目名称
	 * @return 该科目所有的学习记录
	 */
	public List<TbStudyrecord> findStudyRecord(Integer stuId,String subName);
}