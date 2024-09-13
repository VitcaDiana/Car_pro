package com.project.CarPro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CarProApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarProApplication.class,args);
	}

}
