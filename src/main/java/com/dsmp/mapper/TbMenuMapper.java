package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbMenu;

public interface TbMenuMapper {
    public List<TbMenu>  selectMenu(int rol_id,int men_father);
}