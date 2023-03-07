package com.aterehov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class AvroKafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroKafkaDemoApplication.class, args);
	}

}
