package com.dsmp.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsmp.utils.KapchaUtil;
import com.google.code.kaptcha.Constants;

@Controller
@RequestMapping("/user")
public class IndexController {
	
	@RequestMapping("/loginGetCode")
	public void getCode(HttpSession session,HttpServletResponse response) {
		try {
			Map<String,BufferedImage> imgMap = KapchaUtil.captcha();
			session.setAttribute(Constants.KAPTCHA_SESSION_KEY, imgMap.keySet().iterator().next());
			String yzm = imgMap.keySet().iterator().next();
			BufferedImage bufferedImage = imgMap.get(yzm);
			ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
