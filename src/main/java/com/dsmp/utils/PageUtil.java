package com.dsmp.utils;

/**   
 * @ClassName:  PageUtil   
 * @Description:分页计算工具  
 * @author: 张德承
 * @date:   2019年4月28日 上午9:00:56   
 *   
 */ 
public class PageUtil {
	
	public static int getPage(int dataNum, int pageCount) {
		int totalPage = 0;
		if (dataNum <= pageCount) {
			totalPage = 1;
		} else {
			if (dataNum % pageCount == 0) {
				totalPage = dataNum / pageCount;
			} else {
				totalPage = dataNum / pageCount + 1;
			}
		}
		return totalPage;
	}
}
