package com.dsmp.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbStudent {
	
	private Integer stuId;

	private Integer coaId;

	private Integer schId;

	private Integer subId;

	private String stuName;

	private String stuAccount;

	private String stuPassword;

	private String stuSex;

	private String stuBirthday;

	private String stuIdcard;

	private String stuImg;

	private String stuHeadimg;

	private String stuAddress;

	private Date stuRegistertime;

	private Integer stuErrcount;

	private Date stuErrtime;

	private String stuStatus;

	private Date stuSignuptime;

	private String stuVerifystatus;
	
	private String stuBookingstate;//学员当前科目的状态（未预约-不能考（默认）；可预约-有了考试资格（如科一学时，分数都够）；已预约-学员可以考）
	
	private TbSubject tbSubject;

	private TbCoach tbCoach;

	private TbSchool tbSchool;
	
	private TbStudyrecord tbStudyrecord;
	
	private TbExamscheduleandstudent tbExamscheduleandstudent;
	
	private List<TbSubjectscore> scoreList = new ArrayList<>();

//	private String stuSubjectstatus;//学员当前科目的状态（lock-不能考（默认）；canApply-有了考试资格（如科一学时，分数都够）；apply-学员在申请；unlock-申请通过，学员可以考）

	
	public TbExamscheduleandstudent getTbExamscheduleandstudent() {
		return tbExamscheduleandstudent;
	}

	public void setTbExamscheduleandstudent(TbExamscheduleandstudent tbExamscheduleandstudent) {
		this.tbExamscheduleandstudent = tbExamscheduleandstudent;
	}

	public TbStudyrecord getTbStudyrecord() {
		return tbStudyrecord;
	}

	public void setTbStudyrecord(TbStudyrecord tbStudyrecord) {
		this.tbStudyrecord = tbStudyrecord;
	}


	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	
	public String getStuBookingstate() {
		return stuBookingstate;
	}

	public void setStuBookingstate(String stuBookingstate) {
		this.stuBookingstate = stuBookingstate;
	}

	public Integer getCoaId() {
		return coaId;
	}

	public void setCoaId(Integer coaId) {
		this.coaId = coaId;
	}

	public Integer getSchId() {
		return schId;
	}

	public void setSchId(Integer schId) {
		this.schId = schId;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName == null ? null : stuName.trim();
	}

	public String getStuAccount() {
		return stuAccount;
	}

	public void setStuAccount(String stuAccount) {
		this.stuAccount = stuAccount == null ? null : stuAccount.trim();
	}

	public String getStuPassword() {
		return stuPassword;
	}

	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword == null ? null : stuPassword.trim();
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex == null ? null : stuSex.trim();
	}

	public String getStuBirthday() {
		return stuBirthday;
	}

	public void setStuBirthday(String stuBirthday) {
		this.stuBirthday = stuBirthday == null ? null : stuBirthday.trim();
	}

	public String getStuIdcard() {
		return stuIdcard;
	}

	public void setStuIdcard(String stuIdcard) {
		this.stuIdcard = stuIdcard == null ? null : stuIdcard.trim();
	}

	public String getStuImg() {
		return stuImg;
	}

	public void setStuImg(String stuImg) {
		this.stuImg = stuImg == null ? null : stuImg.trim();
	}

	public String getStuHeadimg() {
		return stuHeadimg;
	}

	public void setStuHeadimg(String stuHeadimg) {
		this.stuHeadimg = stuHeadimg == null ? null : stuHeadimg.trim();
	}

	public String getStuAddress() {
		return stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress == null ? null : stuAddress.trim();
	}

	public Date getStuRegistertime() {
		return stuRegistertime;
	}

	public void setStuRegistertime(Date stuRegistertime) {
		this.stuRegistertime = stuRegistertime;
	}

	public Integer getStuErrcount() {
		return stuErrcount;
	}

	public void setStuErrcount(Integer stuErrcount) {
		this.stuErrcount = stuErrcount;
	}

	public Date getStuErrtime() {
		return stuErrtime;
	}

	public void setStuErrtime(Date stuErrtime) {
		this.stuErrtime = stuErrtime;
	}

	public String getStuStatus() {
		return stuStatus;
	}

	public void setStuStatus(String stuStatus) {
		this.stuStatus = stuStatus == null ? null : stuStatus.trim();
	}

	public Date getStuSignuptime() {
		return stuSignuptime;
	}

	public void setStuSignuptime(Date stuSignuptime) {
		this.stuSignuptime = stuSignuptime;
	}

	public String getStuVerifystatus() {
		return stuVerifystatus;
	}

	public void setStuVerifystatus(String stuVerifystatus) {
		this.stuVerifystatus = stuVerifystatus == null ? null : stuVerifystatus.trim();
	}

	public TbSubject getTbSubject() {
		return tbSubject;
	}

	public void setTbSubject(TbSubject tbSubject) {
		this.tbSubject = tbSubject;
	}

	public TbCoach getTbCoach() {
		return tbCoach;
	}

	public void setTbCoach(TbCoach tbCoach) {
		this.tbCoach = tbCoach;
	}

	public TbSchool getTbSchool() {
		return tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public List<TbSubjectscore> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<TbSubjectscore> scoreList) {
		this.scoreList = scoreList;
	}


}