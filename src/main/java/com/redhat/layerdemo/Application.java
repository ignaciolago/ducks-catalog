package com.redhat.layerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redhat.layerdemo.repository.DuckRepository;

@SpringBootApplication
public class Application {

	@Autowired
	DuckRepository duckRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
