package com.pb.domain;

import java.util.Set;

public class User {
	
	
	Integer id;
	String name;
	String password;
	Integer age;
	String gender;
	String phoneNumber;
	String address;
	Integer permission;
	
	
	Set<Email> emails;
	Set<Vocation> vocations;
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPermission() {
		return permission;
	}
	public void setPermission(Integer permission) {
		this.permission = permission;
	}
	public Set<Email> getEmails() {
		return emails;
	}
	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}
	public Set<Vocation> getVocations() {
		return vocations;
	}
	public void setVocations(Set<Vocation> vocations) {
		this.vocations = vocations;
	}
	
	
	
	
	

}
