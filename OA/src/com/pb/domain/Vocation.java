package com.pb.domain;

import java.util.Date;

public class Vocation {

	Integer id;
	String reason;
	Date beginDate;
	Date endDate;
	Integer days;
	
	User applier;
	User approver;
	Integer state;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public User getApplier() {
		return applier;
	}
	public void setApplier(User applier) {
		this.applier = applier;
	}
	public User getApprover() {
		return approver;
	}
	public void setApprover(User approver) {
		this.approver = approver;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	

}
