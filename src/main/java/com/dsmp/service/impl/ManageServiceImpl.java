package com.dsmp.service.impl;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbManagerMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbManager;
import com.dsmp.service.ManageService;
import com.dsmp.utils.Md5Tools;

@Service
public class ManageServiceImpl implements ManageService {
	@Autowired private TbManagerMapper tbManagerMapper;
	
	@Override
	public MyResult adminLogin(HttpSession session, String account, String password) {
		System.out.println("管理员账号："+account);
		System.out.println("管理员密码："+password);
		String md5Password = Md5Tools.getMd5(password);
		System.out.println("md5加密后的密码："+md5Password);
		MyResult result = new MyResult();
		TbManager manager = new TbManager();
		manager.setManAccount(account);
		manager.setManPassword(md5Password);
		TbManager tbManage = tbManagerMapper.findManage(manager);
		if(tbManage != null){
			if(tbManage.getManPassword().equals(md5Password)) {
				session.setAttribute("manager", tbManage);
				result.setRoleId(tbManage.getRolId());
				result.setMyresult("success");
			}else {
				result.setMyresult("pwdErr");
			}			
		}else {
			result.setMyresult("failed");
		}
		return result;
	}

}
