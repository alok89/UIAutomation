package com.testautomation.UIAutomation.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.BeanWithPrototypeScope;
import com.testautomation.UIAutomation.annotations.LazyConfiguration;

@LazyConfiguration
public class WebDriverWaitConfig {

	@Value("${webDriverWait.timeout:15}")
	private int timeOut;

	@BeanWithPrototypeScope
	public WebDriverWait webDriverWait(WebDriver driver) {
		return new WebDriverWait(driver, timeOut);
	}
}
