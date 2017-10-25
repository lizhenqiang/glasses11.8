package com.pb.domain;

import java.util.Date;



public class Email {
	
	
	Integer id;
	String title;
	String context;
	String attachment;
	int isRead;
	int isDelete;
	
	Date senderDate;
	
	User sender;
	User reciver;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Date getSenderDate() {
		return senderDate;
	}
	public void setSenderDate(Date senderDate) {
		this.senderDate = senderDate;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReciver() {
		return reciver;
	}
	public void setReciver(User reciver) {
		this.reciver = reciver;
	}
	
	
	
	
	
	
	
	
	
	

}
