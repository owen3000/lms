package com.cafe24.dto;

public class JSONResult {

	private String result; // "success" or "fail"
	private String message; // result가 "fail"이면 원인 메세지 전달
	private Object data; // result가 "success"이면 전달할 data
	
	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	//성공일때는 데이터만
	static public JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	static public JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
