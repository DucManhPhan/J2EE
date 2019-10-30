package com.manhpd;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.github.javafaker.Faker;
import com.manhpd.create_file.CreationLargeFile;
import com.manhpd.read_file.multithreding.ReadLargeFileMultithreading;
import com.manhpd.read_file.sync.ReadLargeFile;


public class App {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, InterruptedException {
		long start = System.currentTimeMillis();
//		new CreationLargeFile().create();
//		new ReadLargeFile().readFile();
		new ReadLargeFileMultithreading().readFile();
		long time = System.currentTimeMillis() - start;
		System.out.printf("\nTook %.1f seconds to read all content of a file.\n", time / 1e3);
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
