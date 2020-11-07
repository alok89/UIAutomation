package com.testautomation.UIAutomation.hooks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.utils.ManageFilesDirectories;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ScenarioHooks {
	
	@Autowired
	private WebDriver driver;

	@Value("${screenshot.path}")
	private Path path;

	@Autowired
	private ManageFilesDirectories manageDirectories;
	
	//private Logger logger = LogManager.getLogger();

	@Before(order = 0)
	public void beforeScenario(Scenario scenario) {
		String scenarioName = scenario.getName();
		System.out.println("Scenario SetUp : "+getFeatureName(scenario)+" > "+scenarioName);
		//logger.info("\n===============Scenario SetUp : "+getFeatureName(scenario)+" > "+scenarioName+"===============");
		createScreenshotDirectory(scenarioName);
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		//logger.info("===============Scenario Status : "+scenario.getStatus().toString()+"===============\n");
		driver.quit();
	}

	private void createScreenshotDirectory(String scenarioName) {
		//logger.debug("Creating Screenshot Directory");
		path = path.resolve(scenarioName);
		try {
			if(path.toFile().exists()) {
				manageDirectories.deleteDirectoryWalkTree(path);
			}
			Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getFeatureName(Scenario scenario) {
		String id = scenario.getId();
		String featureFileName = id.substring((id.indexOf('/')+1), id.lastIndexOf(':'));
		return featureFileName;
	}

}
