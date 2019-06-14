package com.dsmp.mapper;

import com.dsmp.pojo.TbOption;

public interface TbOptionMapper {

<<<<<<< HEAD
    int deleteByPrimaryKey(Integer optId);

    int insert(TbOption record);

    public  int insertSelective(TbOption record);//插入选项

    TbOption selectByPrimaryKey(Integer optId);

    public int updateByPrimaryKeySelective(TbOption record);//更新选项
    
    public int deleteByTopId(Integer optId);//删除题目

    int updateByPrimaryKey(TbOption record);
=======
	int insertOption(TbOption tbOption);
	
>>>>>>> branch 'master' of https://github.com/thinkaboutgod/dsmp
}