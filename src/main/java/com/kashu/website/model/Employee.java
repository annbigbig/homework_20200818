package com.kashu.website.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonFormat;


/*
 * https://stackoverflow.com/questions/22816817/hibernate-create-index/22829167
 * https://docs.jboss.org/hibernate/stable/search/reference/en-US/html_single/#_indexing
 */

@Data
@Entity
@Table(name = "TBL_EMPLOYEE")
public class Employee implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",  //欄位的定義
	columnDefinition="bigint(20)",
	nullable=false,
	updatable=false,
	unique=true)
	private long id;
	
	@Column(name="name",
			columnDefinition="varchar(100)",
			nullable=false)
	private String name;
	
	@Column(name="birthday",
			columnDefinition="date",
			nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	//@JsonSerialize(using = BobsonDateSerializer.class)
    private Date birthday;
	
	@Column(name = "address",
			columnDefinition="varchar(200)",
			nullable=false)
	private String address;
	
	@Column(name = "luckyNumber",nullable=false)
	@Min(1)
	@Max(100)
	private int luckyNumber;
	
	@Column(name = "online",columnDefinition = "TINYINT",nullable=false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean online;
	
	public Employee() {
	}

	public Employee(long i, String n, Date birthday, String address, int luckyNumber, boolean online) {
		this.id = i;
		this.name = n;
		this.birthday = birthday;
		this.address = address;
		this.luckyNumber = luckyNumber;
		this.online = online;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getLuckyNumber() {
		return luckyNumber;
	}

	public void setLuckyNumber(int luckyNumber) {
		this.luckyNumber = luckyNumber;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Override
	public String toString() {
		String str = "id = " + id + " , " + "name = " + name + " , " + "birthday = " + birthday + " , "
				+ "address = " + address + " , " + "luckyNumber = " + luckyNumber + " , "
				+ "online = " + (online ? "true" : "false");
		return str;
	}
	
}