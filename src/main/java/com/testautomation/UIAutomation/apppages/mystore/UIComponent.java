package com.testautomation.UIAutomation.apppages.mystore;

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
	protected Actions actionBuilder;
	
	public UIComponent() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	public abstract boolean isDisplayed();

}
