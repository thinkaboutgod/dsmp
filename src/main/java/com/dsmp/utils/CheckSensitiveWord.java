package com.dsmp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @ClassName: CheckSensitiveWord
 * @Description: TODO(校验字符串是否合法的工具类)
 */
public class CheckSensitiveWord {
	private static StringBuilder replaceAll;// 初始化
	private static String encoding = "UTF-8";
	private static String replceStr = "*";
	private static int replceSize = 500;
	private static String fileName = "SensitiveWord.txt";
	private static List<String> arrayList;
	private static Set<String> sensitiveWordSet;// 包含的敏感词列表，过滤掉重复项
	private static List<String> sensitiveWordList;// 包含的敏感词列表，包括重复项，统计次数

	/**
	 * 构造方法
	 */
	private CheckSensitiveWord() {

	}

	/**
	 * @Description:TODO(校验字符串是否合法)
	 * @param str 要校验的字符串
	 * @return 是否合法的结果 合法返回true 不合法返回 false
	 */
	public static boolean isRightful(String str) {
		boolean right = true;
		InitializationWork();// 加载敏感词
		sensitiveWordSet = new HashSet<String>();
		sensitiveWordList = new ArrayList<>();
		StringBuilder buffer = new StringBuilder(str);
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
		String temp;
		for (int x = 0; x < arrayList.size(); x++) {
			temp = arrayList.get(x);
			int findIndexSize = 0;
			for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1;) {
				findIndexSize = start + temp.length();// 从已找到的后面开始找
				Integer mapStart = hash.get(start);// 起始位置
				if (mapStart == null || (mapStart != null && findIndexSize > mapStart))// 满足1个，即可更新map
				{
					right = false;
					return right;
				}
			}
		}
		return right;
	}

	/**
	 * @Description:TODO(初始化敏感词汇)
	 *
	 */
	public static void InitializationWork() {
		replaceAll = new StringBuilder(replceSize);
		for (int x = 0; x < replceSize; x++) {
			replaceAll.append(replceStr);
		}
		// 加载词库
		arrayList = new ArrayList<String>();
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			
			read = new InputStreamReader(
//					CheckSensitiveWord.class.getClassLoader().getResourceAsStream("./com/res/" + fileName), encoding);
			CheckSensitiveWord.class.getClassLoader().getResourceAsStream(fileName), encoding);
			System.out.println("read："+read);
			bufferedReader = new BufferedReader(read);
			for (String txt = null; (txt = bufferedReader.readLine()) != null;) {
				if (!arrayList.contains(txt))
					arrayList.add(txt);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bufferedReader)
					bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != read)
					read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
