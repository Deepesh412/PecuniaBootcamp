package com.capgemini.accountmanagementpecunia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AccountManagementPecuniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementPecuniaApplication.class, args);
	}

}
