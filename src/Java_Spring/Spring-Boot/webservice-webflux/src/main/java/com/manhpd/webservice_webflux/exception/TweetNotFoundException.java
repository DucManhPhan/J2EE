package com.manhpd.webservice_webflux.exception;

public class TweetNotFoundException extends RuntimeException {

	public TweetNotFoundException(String id) {
		super("Tweet not found with id " + id);
	}

}
