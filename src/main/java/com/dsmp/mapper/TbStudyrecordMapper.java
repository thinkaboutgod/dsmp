package com.dsmp.mapper;

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
}