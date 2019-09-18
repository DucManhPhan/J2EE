package com.manhpd.webclient_utils.utils.sample_object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String username;

	private String email;

	private Integer credits;

	private String twitter_username;

	@Override
	public String toString() {
		return "UserName: " + this.getUsername() + " Email: " + this.getEmail();
	}

}
