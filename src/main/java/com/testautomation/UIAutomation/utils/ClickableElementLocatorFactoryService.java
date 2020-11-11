package com.testautomation.UIAutomation.utils;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ClickableElementLocatorFactoryService implements ElementLocatorFactory {
	
	@Autowired
	private ApplicationContext appContext;

	private WebDriver driver;
	
	@Value("${elementLocatorFactory.timeout}")
	private int timeOutInSeconds;

	@Override
	public ElementLocator createLocator(Field field) {
		driver = appContext.getBean(WebDriver.class);
		return new ClickableElementLocator(driver, field, timeOutInSeconds);
	}

	private class ClickableElementLocator extends AjaxElementLocator {
		
		public ClickableElementLocator(WebDriver driver, Field field, int timeOutInSeconds) {
			super(driver, field, timeOutInSeconds);
		}
		
		@Override
		protected long sleepFor() {
			return 500;
		}
		
		@Override
		protected boolean isElementUsable(WebElement element) {
			if(element != null) {
				return element.isDisplayed() && element.isEnabled();
			}else {
				return false;
			}
		}
		
	}

}
