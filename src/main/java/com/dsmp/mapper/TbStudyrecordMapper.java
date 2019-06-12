package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbStudyrecord;

public interface TbStudyrecordMapper {

    int deleteByPrimaryKey(Integer strId);

    int insert(TbStudyrecord record);

    int insertSelective(TbStudyrecord record);
    
    TbStudyrecord selectByPrimaryKey(Integer strId);

    int updateByPrimaryKeySelective(TbStudyrecord record);

    int updateByPrimaryKey(TbStudyrecord record);
    
    public Double countTimeByStuIdAndSubject(String stuId,String subId);//查询某学员该阶段已学时长
    
    public List<TbStudyrecord> selectNowDayRecord(String stuId, String subId);//查询该学员今天最新的一条学习记录
    
    public int insertStudyRecord(String stuId, String subId);//插入该学员学习记录
}