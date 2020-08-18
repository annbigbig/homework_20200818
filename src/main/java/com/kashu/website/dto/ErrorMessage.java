package com.kashu.website.dto;

//用來儲存回覆給前端的錯誤訊息 
public class ErrorMessage {
	private boolean result;
	private long status;
	private String msg;
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(boolean result, long status, String msg) {
		this.result = result;
		this.status = status;
		this.msg = msg;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
