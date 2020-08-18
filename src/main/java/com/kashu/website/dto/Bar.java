package com.kashu.website.dto;

public class Bar {
	private String x;
	private String y;
	private String z;
	
	public Bar() {
		
	}
	
	public Bar(String x, String y, String z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}
	
	public String toString() {
		String str = "Bar : x = " + x + " y = " + y + " z = " + z;
		return str;
	}
}
