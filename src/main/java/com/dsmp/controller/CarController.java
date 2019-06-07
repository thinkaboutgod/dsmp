package com.dsmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dsmp.service.CarService;

@Controller
public class CarController {

	@Autowired private CarService carService;
}
