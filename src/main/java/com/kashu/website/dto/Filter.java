package com.kashu.website.dto;

//用來儲存過濾參數
public class Filter {
	 String column;
	 String operator;
	 String value;
	 
	 public Filter() {
		 
	 }
	 
	 public Filter(String column, String operator, String value) {
		 this.column = column;
		 this.operator = operator;
		 this.value = value;
	 }

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		String str = "Filter : column = " + column + ", operator = " + operator + ", value = " + value; 
		return str;
		
	}
}