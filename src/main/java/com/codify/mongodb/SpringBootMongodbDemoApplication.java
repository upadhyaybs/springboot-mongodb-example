package com.codify.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author upadhyaybs
 *
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SpringBootMongodbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbDemoApplication.class, args);
	}

}
