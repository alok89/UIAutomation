package com.testautomation.UIAutomation.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;

import com.testautomation.UIAutomation.annotations.LazyConfiguration;
import com.testautomation.UIAutomation.scope.BrowserScopePostProcessor;

@LazyConfiguration
public class BrowserScopeConfig {

	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new BrowserScopePostProcessor();
	}
	
}
