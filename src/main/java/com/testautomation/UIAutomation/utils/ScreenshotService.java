package com.testautomation.UIAutomation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.github.javafaker.Faker;

@Service
public class ScreenshotService {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Lazy
	@Autowired
	private Faker faker;
	
	private TakesScreenshot driver;
	
	public String getScreenshotAsFile(Path path, String screenshotName) {
		createTakesScreenshotInstance();
		File destinationFile = path.resolve(screenshotName+".jpg").toFile();
		File sourceFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileCopyUtils.copy(sourceFile, destinationFile);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return destinationFile.getPath();
	}
	
	public byte[] getScreenshotAsBytes() {
		createTakesScreenshotInstance();
		byte[] bytesData = driver.getScreenshotAs(OutputType.BYTES);
		return bytesData;
	}
	
	private TakesScreenshot createTakesScreenshotInstance() {
		driver = appContext.getBean(TakesScreenshot.class); 
		return driver;
	}

}
