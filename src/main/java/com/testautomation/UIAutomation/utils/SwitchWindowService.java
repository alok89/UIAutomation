package com.testautomation.UIAutomation.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class SwitchWindowService {
	
	@Autowired
	private WebDriver driver;
	
	private List<String> windows;
	
	private void getAllWindows() {
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
