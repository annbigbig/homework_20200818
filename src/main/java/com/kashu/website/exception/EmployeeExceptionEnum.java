package com.kashu.website.exception;

public enum EmployeeExceptionEnum {

	// 代碼為暫定，非定版
	UPDATE_FAILED(1, "員工資料更新失敗"),
	SAVE_FAILED(2, "員工資料儲存失敗"),
	UNKNOWN_ERROR(-1, "UNKNOWN_ERROR"),
	UNIQE_ERROR(-2, "違反 UNIQUE KEY 條件約束, 插入重複的索引鍵"),
	NULL_ERROR(-3, "調用 NULL 方法"),
	ARGUMENT_ERROR(-4, "方法參數錯誤"),
	NUMBERFORMAT_ERROR(-5, "轉換數字型態錯誤"),
	SQL_ERROR(-6, "SQL錯誤"),
	DELETE_ERROR(-7,"員工資料刪除失敗"),
	EMPLOYEE_NOTFOUND(-99, "EMPLOYEE_NOTFOUND"),
	UNCATEGORISED_ERROR(777,"未被歸類的錯誤");
	
	private int code;
	
	private String errorMsg;

	private EmployeeExceptionEnum(int code,String errorMsg){
		this.code = code;
		this.errorMsg = errorMsg;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
}