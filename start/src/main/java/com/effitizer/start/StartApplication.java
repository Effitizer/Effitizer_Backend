package com.effitizer.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextListener;

@EnableJpaAuditing
@SpringBootApplication
@EnableAspectJAutoProxy
public class StartApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
	@Bean
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	}
}