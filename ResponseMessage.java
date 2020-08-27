package com.fup.base;

public class ResponseMessage {

	private String message;
	private Object data;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	  public ResponseMessage(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public ResponseMessage(){}
	  public void setMessage(String message) {
	    this.message = message;
	  }
}
