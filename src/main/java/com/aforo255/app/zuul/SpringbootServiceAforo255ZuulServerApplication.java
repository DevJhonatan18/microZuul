package com.aforo255.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableZuulProxy
@SpringBootApplication
public class SpringbootServiceAforo255ZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceAforo255ZuulServerApplication.class, args);
	}

}
