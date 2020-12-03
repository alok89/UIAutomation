package com.testautomation.UIAutomation.apppages.mystore;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

public abstract class UIComponent {
	
	@LazyAutowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@LazyAutowired
	protected WebDriverWait wait;
	
	@LazyAutowired
	protected WebDriver driver;
	
	@LazyAutowired
	protected Actions actions;
	
	@PostConstruct
	protected void initializeElements() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	public abstract boolean isDisplayed();

}
