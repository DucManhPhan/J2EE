package com.manhpd.webclient_utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {

	private String message;

	private String errorCode;

	private boolean isError;

	private String data;

}