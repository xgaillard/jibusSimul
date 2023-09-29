package com.fareco.simul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:simul.properties")
public class SimulStarter extends SpringBootServletInitializer {

//	private SimulWebSocket simulWs;
	public static void main(String[] args) {
		SpringApplication.run(SimulStarter.class, args);
	}

}
