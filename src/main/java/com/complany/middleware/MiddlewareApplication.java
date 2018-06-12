package com.complany.middleware;

import com.complany.middleware.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

@SpringBootApplication
public class MiddlewareApplication {

	@Autowired
	private AuthorService authorService;

	@Bean
	public Context context() throws Exception {
		Properties props = new Properties();
		props.setProperty("java.naming.factory.initial", "com.sun.jndi.cosnaming.CNCtxFactory");
		props.setProperty("java.naming.provider.url", "iiop://localhost:3000");
		Context ctx = new InitialContext(props);
		ctx.rebind("authorService", authorService);
		System.out.println("Java RMI IIOP waiting: locahost:3000/authorService");
		return ctx;
	}

	public static void main(String[] args) {
		SpringApplication.run(MiddlewareApplication.class, args);
	}
}
