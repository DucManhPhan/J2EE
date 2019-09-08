package com.manhpd.webservice_webflux.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tweets")
@Data
@NoArgsConstructor
public class Tweet {

	@Id
	private String id;
	
	@NotBlank
	@Size(max = 140)
	private String text;
	
	@NotNull
	private Date createdAt = new Date();
	
	public Tweet(String text) {
		this.id = id;
		this.text = text;
	}
}
