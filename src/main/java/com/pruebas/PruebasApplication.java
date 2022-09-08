package com.pruebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.pruebas.repositories")
public class PruebasApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(PruebasApplication.class, args);
	}

}
