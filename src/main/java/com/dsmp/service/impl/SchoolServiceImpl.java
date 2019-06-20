package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbSchoolMapper;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;

import com.dsmp.service.SchoolService;
import com.dsmp.utils.Md5Tools;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired private TbSchoolMapper tbSchoolMapper;


	//驾校入驻
	@Override
	public MyResult insertSchoolInfo(String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge,String fileName) {
		String md5Password = Md5Tools.getMd5(password);
		MyResult result = new MyResult();
		TbSchool school = new TbSchool();
		school.setSchCreditcode(sch_creditcode);		
		TbSchool tbSchoolCreditcode = tbSchoolMapper.getSchoolByCreditcode(school);
		if(tbSchoolCreditcode != null) {
			result.setMyresult("creditcodeAlready");
		}else {
			school.setSchAccount(phone);
			TbSchool tbSchool = tbSchoolMapper.getSchool(school);
			if(tbSchool == null) {
				school.setRolId(3);
				school.setSchName(sch_name);		
				school.setSchPassword(md5Password);
				school.setSchAddress(sch_address);
				school.setSchIntroduction(sch_introduce);
				school.setSchBossname(sch_bossname);
				school.setSchRegistercapital(sch_registerCapital);				
				school.setSchType(sch_type);
				school.setSchHeadimg(fileName);
				school.setSchCharge(sch_charge);
				int res = tbSchoolMapper.insertSchoolInfo(school);
				if(res == 1) {
					result.setMyresult("success");
				}else {
					result.setMyresult("failed");
				}
			}else {
				result.setMyresult("already");
			}
		}		
		System.out.println(result);
		return result;
	}

//查询所有允许报名和运营的驾校
	@Override
	public List<TbSchool> selectAllSchoolForAdvertise() {
		// TODO Auto-generated method stub
		return tbSchoolMapper.selectAllSchoolForAdvertise();
	}
	
	
}
