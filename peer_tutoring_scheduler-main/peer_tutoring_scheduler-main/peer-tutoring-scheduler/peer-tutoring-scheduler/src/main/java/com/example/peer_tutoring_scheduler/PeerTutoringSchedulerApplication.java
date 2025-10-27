
package com.example.peer_tutoring_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.peertutoring.repository")
@EntityScan(basePackages = "com.example.peertutoring.entity")
public class PeerTutoringSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeerTutoringSchedulerApplication.class, args);
	}

}
