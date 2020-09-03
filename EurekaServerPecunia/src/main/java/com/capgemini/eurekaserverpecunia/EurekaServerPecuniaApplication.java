package com.capgemini.eurekaserverpecunia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerPecuniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerPecuniaApplication.class, args);
	}

}
