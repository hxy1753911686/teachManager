package com.school.estimate.exception;

public class GlobalException extends Exception {
	private static final long serialVersionUID = 4055945147128016300L;
	//提示信息
	private String message;
	public String getMessage() {
		return message;
	}
	public GlobalException(String message) {
		this.message = message;
	}
}
