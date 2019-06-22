package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbSubject;

public interface TbSubjectMapper {

    int deleteByPrimaryKey(Integer subId);

    int insert(TbSubject record);

    int insertSelective(TbSubject record);

    TbSubject selectByPrimaryKey(Integer subId);

    int updateByPrimaryKeySelective(TbSubject record);

    int updateByPrimaryKey(TbSubject record);
    
    public List<TbSubject> selectAllSubject();//查询所有科目
    
    /**查出该科目总需要学时
     * @param subId 科目id
     * @return
     */
    public Integer findNeedStudyTime(Integer subId);
}