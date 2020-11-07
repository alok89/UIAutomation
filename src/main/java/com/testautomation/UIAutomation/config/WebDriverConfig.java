package com.testautomation.UIAutomation.config;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

import com.testautomation.UIAutomation.annotations.BeanWithBrowserScope;
import com.testautomation.UIAutomation.annotations.LazyConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;

@LazyConfiguration
@Profile("!remote")
public class WebDriverConfig {

	@Value("${downloadedfiles.path}")
	private String path;
	
	@Value("${headless}")
	private String browserHeadlessMode;

	@BeanWithBrowserScope
	@ConditionalOnProperty(name = "browser", havingValue = "firefox")
	public WebDriver firefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}
	
	@BeanWithBrowserScope
	@ConditionalOnProperty(name = "browser", havingValue = "ie")
	public WebDriver ieDriver() {
		WebDriverManager.iedriver().setup();
		return new InternetExplorerDriver();
	}
	
	@BeanWithBrowserScope
	@ConditionalOnMissingBean
	public WebDriver chromeDriver() {
		WebDriverManager.chromedriver().setup();
		Map<String, String> preferences = new HashMap<>();
		preferences.put("download.default_directory", path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		if(Boolean.parseBoolean(browserHeadlessMode)) {
			options.setHeadless(true);
			options.addArguments("window-size=1200x600");
		}
		options.setExperimentalOption("prefs", preferences);
		return new ChromeDriver(options);
	}
}
