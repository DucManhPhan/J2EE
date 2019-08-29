package com.manhpd.large_file;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.github.javafaker.Faker;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		new ProcessingFile().create();
	}

	void createFakeDate() {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		System.out.println(name);
		System.out.println(faker.name().fullName());
	}
}
