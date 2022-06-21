package com.nttdata.bc19.mstransactioncredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsTransactionCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTransactionCreditApplication.class, args);
	}

}
