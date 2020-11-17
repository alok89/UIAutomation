package com.testautomation.UIAutomation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testautomation.UIAutomation.annotations.LazyConfiguration;

@LazyConfiguration
public class ExtentSparkReporterConfig {

	@Value("report.path")
	private String reportPath;
	
	@Bean
	public ExtentSparkReporter getExtentSparkReporter() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Test-Results");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("UI-Automation");
		return reporter;
	}
}
