package com.testautomation.UIAutomation.webelements;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.LazyComponent;

@LazyComponent
public class Alerts {
	
	@LazyAutowired
	private ApplicationContext context;
	
	private WebDriver driver;
	
	private Alert alert;

	private WebDriver getDriver() {
		driver = context.getBean(WebDriver.class);
		return driver;
	}
	
	private Alert switchToAlert() {
		getDriver();
		alert = driver.switchTo().alert();
		return alert;
	}
	
	public void acceptAlert() {
		switchToAlert().accept();
	}
	
	public String getAlertText() {
		return switchToAlert().getText();
	}
	
	public void enterValueIntoAlert(String value) {
		switchToAlert().sendKeys(value);
	}
	
	public void dismissAlert() {
		switchToAlert().dismiss();
	}
}
