package com.dsmp.pojo;

public class TbSubjectscore {
    private Integer susId;

    private Integer subId;

    private Integer stuId;

    private Integer susScore;
    
    private TbSubject tbSubject;
    
    private TbStudent tbStudent;
    

    public Integer getSusId() {
        return susId;
    }

    public void setSusId(Integer susId) {
        this.susId = susId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getSusScore() {
        return susScore;
    }

    public void setSusScore(Integer susScore) {
        this.susScore = susScore;
    }

	public TbSubject getTbSubject() {
		return tbSubject;
	}

	public void setTbSubject(TbSubject tbSubject) {
		this.tbSubject = tbSubject;
	}

	public TbStudent getTbStudent() {
		return tbStudent;
	}

	public void setTbStudent(TbStudent tbStudent) {
		this.tbStudent = tbStudent;
	}
    
}