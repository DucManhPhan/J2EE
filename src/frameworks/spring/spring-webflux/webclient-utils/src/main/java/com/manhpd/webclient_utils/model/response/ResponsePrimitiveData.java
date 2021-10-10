package com.manhpd.webclient_utils.model.response;

import lombok.Data;

@Data
public class ResponsePrimitiveData extends Response {

	String data;

	@Override
	public void setResponseData(Object data) {
		this.data = (String) data;
	}

	@Override
	public Object toJSONValue(String data) {
		return data;
	}

}
