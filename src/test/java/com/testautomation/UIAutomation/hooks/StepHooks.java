package com.testautomation.UIAutomation.hooks;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.utils.ScreenshotService;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class StepHooks {
	
	@SuppressWarnings("unused")
	@Autowired
	private ScreenshotService screenshotService;
	
	@Value("${screenshot.path}")
	private Path path;
	
	@AfterStep
	public void afterStep(Scenario scenario) {
		/*
		 * String scenarioName = scenario.getName(); String lineNo =
		 * scenario.getLine().toString();
		 * screenshotService.getScreenshotAsFile(path.resolve(scenarioName),
		 * "Step_"+lineNo);
		 */
		//scenario.attach(screenshotService.getScreenshotAsBytes(), "image/png", scenarioName+"_"+lineNo);
	}


}
