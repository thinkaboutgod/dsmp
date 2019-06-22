package com.dsmp.log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
	public String operationDetail()default"";
	
}
