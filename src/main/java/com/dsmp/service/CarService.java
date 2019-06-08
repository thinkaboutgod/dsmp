package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbCar;

public interface CarService {

	/**
	 * 查询驾校名下的车辆信息
	 * @param schId
	 * @return
	 */
	public List<TbCar> selectCarsBySchId(Integer schId);

}
