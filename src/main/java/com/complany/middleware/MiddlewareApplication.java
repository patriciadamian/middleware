package com.complany.middleware;

import com.complany.middleware.service.AuthorService;
import com.complany.middleware.service.IAuthorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.UnknownHostException;

@SpringBootApplication
public class MiddlewareApplication {

	@Bean
	public IAuthorService orderService() {
		return new AuthorService();
	}

	@Bean
	public RmiServiceExporter exporter() throws UnknownHostException {
		RmiServiceExporter rse = new RmiServiceExporter();
		rse.setServiceName("IAuthorService");
		rse.setService(orderService());
		rse.setServiceInterface(IAuthorService.class);
		rse.setRegistryPort(2099);
		return rse;
	}

	public static void main(String[] args) {
		SpringApplication.run(MiddlewareApplication.class, args);
	}
}
