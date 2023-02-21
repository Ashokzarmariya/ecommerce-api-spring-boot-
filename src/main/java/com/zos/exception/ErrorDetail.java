package com.zos.exception;

import java.time.LocalDateTime;

public class ErrorDetail {
	
	private String message;
	private String detail;
	private LocalDateTime timeStamp;
	
	public ErrorDetail() {
		// TODO Auto-generated constructor stub
	}

	public ErrorDetail(String message, String detail, LocalDateTime timeStamp) {
		super();
		this.message = message;
		this.detail = detail;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
