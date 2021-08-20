package com.manhpd.webclient_utils.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Response {

	private String message;

	private String errorCode;

	private boolean isError;

	public abstract void setResponseData(Object data);

	public abstract Object toJSONValue(String data);
}
