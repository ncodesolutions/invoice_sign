package com.ncode.invoice.esign;

public class Response {

	private String status;

	private Object responseBody;

	private String errorMessage;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(String status, Object responseBody, String errorMessage) {
		super();
		this.status = status;
		this.responseBody = responseBody;
		this.errorMessage = errorMessage;
	}
}