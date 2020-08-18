package com.kashu.website.exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kashu.website.dto.ErrorMessage;

/*
 * 參考連結:
 * https://stackoverflow.com/questions/48991353/how-to-catch-all-unhandled-exceptions-i-e-without-existing-exceptionhandler
 */

@RestControllerAdvice
public class BobsonExceptionHandler {
	public static Log INFO = LogFactory.getLog("info");
	public static Log ERROR = LogFactory.getLog("error");

	/*
	 * it's working，抓得到
	 * 測試用的DummyException
	 */
	@ExceptionHandler({DummyException.class})
    public final ResponseEntity<ErrorMessage> handleDummyException(DummyException ex) {
		// log it first
		ERROR.error(ex.getMessage());
		ERROR.error(ex.getStackTrace());
        ErrorMessage errMsg = new ErrorMessage(false,404l,ex.getErrorMessage());
        return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	/*
	 * 以id找不到某員工的時候會丟出此例外
	 * 但是這個類別抓不到此例外 ? ? ?
	 */
	@ExceptionHandler({NoSuchEmployeeException.class})
    public final ResponseEntity<ErrorMessage> handleNoSuchEmployeeException(NoSuchEmployeeException ex) {
		// log it first
		ERROR.error(ex.getMessage());
		ERROR.error(ex.getStackTrace());
        ErrorMessage errMsg = new ErrorMessage(false,
        		EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getCode(),
        		EmployeeExceptionEnum.EMPLOYEE_NOTFOUND.getErrorMsg()
        		);
        return new ResponseEntity<>(errMsg, HttpStatus.NOT_ACCEPTABLE);
    }
	
	/*
	 * 抓不到 ...
	 * cannot catch this IllegalArgumentException (subclass of java.lang.RuntimeException)
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
		ERROR.error(ex.getMessage());
		ERROR.error(ex.getStackTrace());
        ErrorMessage errMsg = new ErrorMessage(false,405l,"不支持HTTPS (tcp port 443) ，請改用HTTP開頭的URL (tcp port 80)");
        return new ResponseEntity<>(errMsg, HttpStatus.METHOD_NOT_ALLOWED);
    }
   
	/*
	 * it's working
	 * 其他不在上面的所有Exception (ConstraintViolationException也在這裡被抓到)
	 */
	
	/* 不要關掉這個方法，Service Layer沒抓到的Exception會回500然後讓前端沒辦法正常工作 */
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleException(Exception ex) {
		String stacktrace = ExceptionUtils.getStackTrace(ex);
		ERROR.error(stacktrace);
		//ErrorMessage errMsg = new ErrorMessage(false,777l,"未被歸類的錯誤 : " + stacktrace);
        ErrorMessage errMsg = new ErrorMessage(false,
        		EmployeeExceptionEnum.UNCATEGORISED_ERROR.getCode(),
        		EmployeeExceptionEnum.UNCATEGORISED_ERROR.getErrorMsg() + stacktrace);
        return new ResponseEntity<>(errMsg, HttpStatus.OK);  //只能回給前端Http Status Code 200否則前端不能渲染
        //return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);  //< -- dont do this 千萬別這麼做
    }
   
}
