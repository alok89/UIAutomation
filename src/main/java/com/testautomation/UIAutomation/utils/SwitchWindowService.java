package com.testautomation.UIAutomation.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SwitchWindowService {
	
	@Autowired
	private ApplicationContext appContext;
	
	private WebDriver driver;
	
	private List<String> windows;
	
	private void getAllWindows() {
		driver = appContext.getBean(WebDriver.class);
		windows = new ArrayList<>(driver.getWindowHandles());
	}
	
	public void switchToWindowByTitle(String windowTitle) {
		getAllWindows();
		windows.stream()
					.map(win -> driver.switchTo().window(win))
					.filter(win -> {
						System.out.println("Window Title :: "+win.getTitle());
						return win.getTitle().trim().equals(windowTitle);})
					.findFirst();
	}
	
	public void switchToWindowByIndex(int windowIndex) {
		getAllWindows();
		driver.switchTo().window(windows.get(windowIndex));
	}

}
