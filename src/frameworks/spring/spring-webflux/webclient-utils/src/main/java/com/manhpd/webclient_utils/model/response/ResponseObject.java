package com.manhpd.webclient_utils.model.response;

import org.json.simple.JSONObject;

public class ResponseObject extends Response {

	private JSONObject data;
	
	@Override
	public void setResponseData(Object data) {
		this.data = (JSONObject) data;
	}

	@Override
	public Object toJSONValue(String data) {
		return null;
	}

}
