package com.testautomation.UIAutomation;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UiAutomationApplication {

	public static void main(String[] args) {
		System.out.println("Starting Application.........");
		ApplicationContext context = SpringApplication.run(UiAutomationApplication.class, args);
		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.stream(beanNames).forEach(System.out::println);
	}

}
