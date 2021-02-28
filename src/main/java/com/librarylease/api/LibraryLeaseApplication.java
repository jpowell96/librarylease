package com.librarylease.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class LibraryLeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryLeaseApplication.class, args);
	}
}
