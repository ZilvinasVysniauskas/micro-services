package com.thoughtmechanix.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
public class LicensesApplication {

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(LicensesApplication.class, args);
	}

}
