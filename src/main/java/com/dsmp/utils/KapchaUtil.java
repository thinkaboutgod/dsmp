package com.dsmp.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
/**
 * 
 * 
 *验证码工具类
 */
public class KapchaUtil {
	public static Map<String, BufferedImage> captcha() throws IOException{
		 DefaultKaptcha kaptchaProducer = new DefaultKaptcha();
	        Properties properties= new Properties();
	        properties.setProperty("kaptcha.border", "no");
	        properties.setProperty("kaptcha.border.color", "black");
	        properties.setProperty("kaptcha.textproducer.font.color", "black");
	        properties.setProperty("kaptcha.image.height", "40");
	        properties.setProperty("kaptcha.image.width", "120");
	        properties.setProperty("kaptcha.textproducer.font.size","32");
	        
	        properties.setProperty("kaptcha.noise.color", "blue");
	        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
//	        properties.setProperty("kaptcha.textproducer.char.space", "5");
	        properties.setProperty("kaptcha.session.key", "code");
	        properties.setProperty("kaptcha.textproducer.char.length", "4");
	        properties.setProperty("kaptcha.textproducer.font.names", "Apple Chancery, HanziPen SC, Courier New");
	        kaptchaProducer.setConfig(new com.google.code.kaptcha.util.Config(properties));
	        
		String captext = kaptchaProducer.createText();
		BufferedImage bufferedImage = kaptchaProducer.createImage(captext);
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		ImageIO.write(bufferedImage, "jpg", outputStream);
		Map<String, BufferedImage> captcha = new HashMap<>();
//		captcha.put(captext, new ByteArrayInputStream(outputStream.toByteArray()));
		captcha.put(captext, bufferedImage);
		return captcha;
		
	}
	
	public static InputStream getInputstring(BufferedImage bufferedImage) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", outputStream);
		InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		return inputStream;
	}
}
