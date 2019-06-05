package com.dsmp.service;

import java.util.List;
import java.util.Map;

import com.dsmp.pojo.TbMenu;

public interface MenuService {
	public Map<TbMenu, List<TbMenu>> selectMen(int rol_id);
}
