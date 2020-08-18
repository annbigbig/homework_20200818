package com.kashu.website.model;

public class Foo {
	private String a;
	private String b;
	private String c;
	
	public Foo() {
		
	}
	
	public Foo(String a , String b , String c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}
	
	public String toString() {
		String str = "Foo : a = " + a + " b = " + b + " c = " + c;
		return str;
	}
		
}