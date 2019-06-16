package com.dsmp.mapper;

import com.dsmp.pojo.TbHotlink;
import java.util.List;

public interface TbHotlinkMapper {

    public int deleteByPrimaryKey(Integer holId);//删除友情链接
   
   public List<TbHotlink> searchAll();//查询所有友情链接

   public int insertSelective(TbHotlink record);//新增友情链接


   public int updateByPrimaryKeySelective(TbHotlink record);//修改友情链接

}