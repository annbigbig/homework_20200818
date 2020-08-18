package com.kashu.website.exception;

public class NoSuchEmployeeException extends RuntimeException {

	private long errorCode;
    private String errorMessage;
	
    public NoSuchEmployeeException() {
    	
    }
    
    public NoSuchEmployeeException(long errorCode, String errorMessage) {
    	this.errorCode = errorCode;
    	this.errorMessage = errorMessage;
    }

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
}
