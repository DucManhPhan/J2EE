package com.manhpd;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.github.javafaker.Faker;
import com.manhpd.create_file.CreationLargeFile;


public class App {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		new CreationLargeFile().create();
	}

	public static void createFakeDate() {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		System.out.println(name);
		System.out.println(faker.name().fullName());
	}
}
