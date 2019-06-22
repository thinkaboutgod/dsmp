package com.dsmp.utils;

public class Main {
	public static void main(String[] args) {
		int x= 10;
		int y= 4;

		double res = (double)x/(double)y;
		System.out.println(res);
		System.out.println("向上取整:" + (int) Math.ceil(96.1));// 97 (去掉小数凑整:不管小数是多少，都进一)
		System.out.println("向下取整" + (int) Math.floor(96.8));// 96 (去掉小数凑整:不论小数是多少，都不进位)
		System.out.println("四舍五入取整:" + Math.round(res));// 96 (这个好理解，不解释)
		System.out.println("四舍五入取整:" + Math.round(96.8));// 97   
		}
	}

