package dev.atanu.ecom.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import dev.atanu.ecom.order.dto.ProjectBuildDetails;

@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
public class OrderServiceApplication {

	@Value("${info.app.version}")
	private String projectVersion;
	
	@Value("${build.number}")
	private String buildNumber;

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public ProjectBuildDetails getBuildNumber() {
		ProjectBuildDetails details = new ProjectBuildDetails(projectVersion, buildNumber);
		logger.info("Project Build Details : {}", details);
		return details;
	}
}
