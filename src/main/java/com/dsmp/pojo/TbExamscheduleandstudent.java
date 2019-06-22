package com.dsmp.pojo;

public class TbExamscheduleandstudent {
    private Integer easId;

    private Integer exsId;

    private Integer stuId;

    private Integer easSeatnum;
    
    private TbExamschedule tbExamschedule;
    
    private TbStudent tbStudent;
    
    public TbExamschedule getTbExamschedule() {
		return tbExamschedule;
	}

	public void setTbExamschedule(TbExamschedule tbExamschedule) {
		this.tbExamschedule = tbExamschedule;
	}

	public Integer getEasId() {
        return easId;
    }

    public void setEasId(Integer easId) {
        this.easId = easId;
    }

    public Integer getExsId() {
        return exsId;
    }

    public void setExsId(Integer exsId) {
        this.exsId = exsId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getEasSeatnum() {
        return easSeatnum;
    }

    public void setEasSeatnum(Integer easSeatnum) {
        this.easSeatnum = easSeatnum;
    }

	public TbStudent getTbStudent() {
		return tbStudent;
	}

	public void setTbStudent(TbStudent tbStudent) {
		this.tbStudent = tbStudent;
	}
    
}