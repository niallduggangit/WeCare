package com.infosys.wecare.tracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class WecareTracingZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(WecareTracingZipkinApplication.class, args);
	}

}
