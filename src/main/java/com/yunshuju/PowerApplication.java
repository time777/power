package com.yunshuju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.yunshuju.*")
@SpringBootApplication
@EnableTransactionManagement
public class PowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerApplication.class, args);
	}
}
