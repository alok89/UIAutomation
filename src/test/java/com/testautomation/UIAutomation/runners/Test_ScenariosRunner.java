package com.testautomation.UIAutomation.runners;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@Test
@CucumberOptions (
		features = "classpath:features",
		glue = "com.testautomation.UIAutomation",
		tags = "@mystore",//"@webdriveruniversity", //"@heroku", //"(@BMS and @BMS-UAT) or (@BMS and @BMS-Smoke)",
		monochrome = true,
		plugin = {"pretty", 
				  //"html:reports/cucumber-report.html",
				  "json:reports/cucumber.json",
				  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				  "summary"}, 
		snippets = SnippetType.CAMELCASE )
public class Test_ScenariosRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
}
