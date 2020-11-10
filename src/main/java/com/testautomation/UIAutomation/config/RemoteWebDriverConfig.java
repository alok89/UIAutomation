package com.testautomation.UIAutomation.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.testautomation.UIAutomation.annotations.LazyConfiguration;

@LazyConfiguration
@Profile("remote")
public class RemoteWebDriverConfig {
	
	@Value("${grid.hub.url}")
	private URL hubUrl;
	
	@Bean
	@ConditionalOnProperty(name = "browser", havingValue = "firefox")
	public WebDriver remoteFirefoxDriver() {
		return new RemoteWebDriver(hubUrl, new FirefoxOptions());
	}
	
	@Bean
	@ConditionalOnMissingBean
	public WebDriver remoteChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments(setRemoteChromeOptions());
		return new RemoteWebDriver(hubUrl, options);
	}
	
	public List<String> setRemoteChromeOptions() {
		List<String> arguments = new ArrayList<>();
		arguments.add("--no-sandbox"); // Environment for executing scripts in the browser
		arguments.add("--disable-infobars");	// Information bars appearing on top of the browser
		arguments.add("--disable-dev-shm-usage");	// Temporary file storage system which is quite less in size in linux
		arguments.add("--disable-browser-side-navigation"); // Side bars in browser
		arguments.add("--disable-gpu"); // Browser uses this to accelerate rendering process
		arguments.add("--disable-features=VizDisplayCompositor");
		arguments.add("--disable-cache");
		arguments.add("--disable-application-cache");
		return arguments;
	}

}
