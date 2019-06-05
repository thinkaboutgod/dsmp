package com.dsmp.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbMenuMapper;
import com.dsmp.pojo.TbMenu;
import com.dsmp.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired TbMenuMapper tbMenuMapper;
	@Override
	public Map<TbMenu, List<TbMenu>> selectMen(int rol_id) {
		Map<TbMenu, List<TbMenu>> menuMap = new LinkedHashMap<>();
		List<TbMenu> listMenu = tbMenuMapper.selectMenu(rol_id, 0);
		for (int i = 0; i < listMenu.size(); i++) {
			List<TbMenu> list = tbMenuMapper.selectMenu(rol_id, listMenu.get(i).getMenId());
			menuMap.put(listMenu.get(i), list);
		}
		return menuMap;
	}

	
}
