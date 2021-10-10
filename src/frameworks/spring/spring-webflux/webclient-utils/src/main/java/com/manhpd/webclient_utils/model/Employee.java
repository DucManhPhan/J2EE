package com.manhpd.webclient_utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private int id;

	private String name;

	private int age;

	private double score;
	
	public String toString() {
		return "Id: " + id + " - name: " + name + " - age: " + age + " - score: " + score;
	}
}
