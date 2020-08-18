package com.kashu.website.dto;

//用來儲存排序參數
public class Sort {
	String column;
    String type;
    
    public Sort() {
   	 
    }
    
    public Sort(String column , String type) {
   	 this.column = column;
   	 this.type = type;
    }

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		String str = "Sort : column = " + column + ", type = " + type; 
		return str;
		
	}
                   
}