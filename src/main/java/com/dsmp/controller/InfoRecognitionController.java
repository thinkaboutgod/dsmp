package com.dsmp.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dsmp.utils.AssesToken;
import com.dsmp.utils.Base64Util;
import com.dsmp.utils.FileUtil;
import com.dsmp.utils.HttpUtil;

@Controller
@RequestMapping("/idCard")
public class InfoRecognitionController {
	private File file2;

	// 学员报名身份证识别
	@RequestMapping(value = "idCard.action")
	public @ResponseBody HashMap<String, String> idCard(MultipartFile file) {
		// 身份证识别url
		String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
		// 本地图片路径
//        String filePath = "#####本地文件路径#####";
		// 获取文件名
		HashMap<String, String> map = null;
		try {
			String fileName = file.getOriginalFilename();
			// 获取文件后缀
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			Random random = new Random();
			String x = String.valueOf(random.nextInt(10000));
			// 用随机数作为文件名，防止生成的临时文件重复
			file2 = File.createTempFile(x, prefix);
			// MultipartFile to File
			file.transferTo(file2);

			byte[] imgData = FileUtil.readFileByBytes(file2);
			String imgStr = Base64Util.encode(imgData);
			// 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
			String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
					+ URLEncoder.encode(imgStr, "UTF-8");
			/**
			 * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			 */
			String accessToken = AssesToken.getAuth();
			;
			String result = HttpUtil.post(idcardIdentificate, accessToken, params);

			map = getHashMapByJson(result);

//			myResult = GsonUtils.toJson(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteFile(file2);
		}
		return map;
	}

	// 将身份信息转成map集合
	public static HashMap<String, String> getHashMapByJson(String jsonResult) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonResult);
			JSONObject words_result = jsonObject.getJSONObject("words_result");
			Iterator<String> it = words_result.keys();
			while (it.hasNext()) {
				String key = it.next();
				JSONObject result = words_result.getJSONObject(key);
				String value = result.getString("words");
				switch (key) {
				case "姓名":
					map.put("name", value);
					break;
				case "民族":
					map.put("nation", value);
					break;
				case "住址":
					map.put("address", value);
					break;
				case "公民身份号码":
					map.put("IDCard", value);
					break;
				case "出生":
					map.put("Birth", value);
					break;
				case "性别":
					map.put("sex", value);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 识别营业执照
	@RequestMapping(value = "business.action")
	public @ResponseBody HashMap<String, String> businessLicense(MultipartFile file) {
		// 营业执照识别url
		String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_license";
		HashMap<String, String> map = null;
		try {
			// 获取文件名
			String fileName = file.getOriginalFilename();
			// 获取文件后缀
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			Random random = new Random();
			String x = String.valueOf(random.nextInt(10000));
			// 用随机数作为文件名，防止生成的临时文件重复
			file2 = File.createTempFile(x, prefix);
			// MultipartFile to File
			file.transferTo(file2);

			byte[] imgData = FileUtil.readFileByBytes(file2);
			String imgStr = Base64Util.encode(imgData);
			// 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
			String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
					+ URLEncoder.encode(imgStr, "UTF-8");
			/**
			 * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			 */
			String accessToken = AssesToken.getAuth();
			
			String result = HttpUtil.post(idcardIdentificate, accessToken, params);
			map = getBusinessHashMapByJson(result);
//				myResult.setMyresult(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			deleteFile(file2);
		}
//			return myResult;
		return map;
	}

	// 将营业执照信息转成map集合
	public static HashMap<String, String> getBusinessHashMapByJson(String jsonResult) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonResult);
			JSONObject words_result = jsonObject.getJSONObject("words_result");
			Iterator<String> it = words_result.keys();
			while (it.hasNext()) {
				String key = it.next();
				JSONObject result = words_result.getJSONObject(key);
				String value = result.getString("words");
				switch (key) {
				case "社会信用代码":
					map.put("creditcode", value);
					break;
				case "单位名称":
					map.put("name", value);
					break;
				case "类型":
					map.put("type", value);
					break;
				case "地址":
					map.put("address", value);
					break;
				case "法人":
					map.put("bossname", value);
					break;
				case "注册资本":
					map.put("registerCapital", value);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param files
	 */
	public void deleteFile(File file) {
		if (file.exists()) {
			file.delete();
		}

	}

}
