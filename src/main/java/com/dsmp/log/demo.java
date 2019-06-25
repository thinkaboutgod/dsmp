package com.dsmp.log;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class demo {

	public static void main(String[] args) {

		Calendar calendar = Calendar.getInstance();

		// 当前日期是本月第几周
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("今天是本月第" + day + "天");

		// 今天是当年的第几天
		int yearDay = calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println("今天是今年第" + yearDay + "天");
		
		calendar.set(2019,0, 1);// 2011年1月1号
		int week = calendar.get(Calendar.DAY_OF_WEEK);// 获取星期，1是周日，2是周1
		String weekstr="";
		switch (week) {
		case 1:
			weekstr="日";
			break;
		case 2:
			weekstr="一";
			break;
		case 3:
			weekstr="二";
			break;
		case 4:
			weekstr="三";
			break;
		case 5:
			weekstr="四";
			break;
		case 6:
			weekstr="五";
			break;
		case 7:
			weekstr="六";
			break;
		default:
			break;
		}
		System.out.println("今年第一天是周"+weekstr);
	}

}

//Period period = Period.between(LocalDate.parse("2019-02-02"), LocalDate.parse("2020-03-01"));
//StringBuffer sb = new StringBuffer();
//sb.append(period.getYears()).append("年")
//      .append(period.getMonths()).append("月")
//      .append(period.getDays()).append("天");
//System.out.println(sb.toString());