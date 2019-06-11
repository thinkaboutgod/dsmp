package com.dsmp.log;

import java.time.LocalDate;
import java.time.Period;

public class demo {

	public static void main(String[] args) {
			
        Period period = Period.between(LocalDate.parse("2019-02-02"), LocalDate.parse("2019-03-01"));
        StringBuffer sb = new StringBuffer();
        sb.append(period.getYears()).append("年")
                .append(period.getMonths()).append("月")
                .append(period.getDays()).append("天");
        System.out.println(sb.toString());

	}
}
