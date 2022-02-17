package hu.javaproject.processes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web;

@Configuration
//@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = "hu.javaproject.processes")
@SpringBootApplication
public class ProcessesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessesApplication.class, args);
	}

}
