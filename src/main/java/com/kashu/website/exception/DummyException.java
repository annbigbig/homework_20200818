package com.kashu.website.exception;

//測試用的Dummy例外
public class DummyException extends RuntimeException {
	/*
	 * https://matthung0807.blogspot.com/2019/12/spring-boot-controlleradvice-return-json.html
	 */

    private long errorCode;
    private String errorMessage;

    public DummyException(long errorCode, String errorMessage) {
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