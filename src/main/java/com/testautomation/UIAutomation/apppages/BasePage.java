package com.testautomation.UIAutomation.apppages;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

public abstract class BasePage {

	@LazyAutowired
	protected WebDriver driver;
	
	@LazyAutowired
	protected WebDriverWait wait;
	
	//@LazyAutowired
	//protected Actions actionBuilder;
	
	@LazyAutowired
	protected ClickableElementLocatorFactoryService elementLocatorFactory;
	
	//private Logger logger = LogManager.getLogger();
	
	@PostConstruct
	protected void initElements() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	public abstract boolean at();
	
	public void goTo(String url) {
		//logger.info("Maximizing the browser window and hitting the url");
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	protected boolean hasTitleMatched(String pageTitle) {
		return wait.until(ExpectedConditions.titleIs(pageTitle));
	}
	
	protected boolean isElementContainsText(WebElement element, String text) {
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
}
