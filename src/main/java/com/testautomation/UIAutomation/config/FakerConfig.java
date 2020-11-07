package com.testautomation.UIAutomation.config;

import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.testautomation.UIAutomation.annotations.LazyConfiguration;

@LazyConfiguration
public class FakerConfig {

	@Bean
	public Faker faker() {
		return new Faker();
	}
}
