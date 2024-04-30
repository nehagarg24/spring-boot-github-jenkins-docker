package com.neha.springbootgithubjenkinsdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootGithubJenkinsDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGithubJenkinsDockerApplication.class, args);
	}

}
