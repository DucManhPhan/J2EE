package com.manhpd.webservice_webflux.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	private String id;

	@NotNull
	private String name;

	@NotNull
	private String address;

	@NotNull
	private String phone;

	@NotNull
	private int salary;

}
