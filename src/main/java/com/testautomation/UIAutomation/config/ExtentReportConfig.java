package com.testautomation.UIAutomation.config;

import org.springframework.context.annotation.Bean;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.LazyConfiguration;

@LazyConfiguration
public class ExtentReportConfig {
	
	@LazyAutowired
	private ExtentSparkReporter reporter;
	
	@Bean
	public ExtentReports getExtentReport() {
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		return extentReports;
	}

}
