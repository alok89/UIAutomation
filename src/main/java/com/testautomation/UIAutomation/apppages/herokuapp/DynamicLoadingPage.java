package com.testautomation.UIAutomation.apppages.herokuapp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.apppages.BasePage;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@PageComponent
public class DynamicLoadingPage extends BasePage {

	private static final String PAGE_URL_LABEL = "dynamic_loading";
	private static final String PAGE_HEADER = "Dynamically Loaded Page Elements";
	private static final String FIRST_EXAMPLE_TEXT = "Example 1: Element on page that is hidden";
	private static final String SECOND_EXAMPLE_TEXT = "Example 2: Element rendered after the fact";
	
	private final By elementLocator = By.cssSelector("#finish>h4");
	
	@FindBy(how = How.TAG_NAME, using = "h3")
	private WebElement pageHeader;
	
	@FindBy(how = How.CSS, using = ".example>h4")
	private WebElement pageSecondHeader;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Example 1:")
	private WebElement firstExample_Link;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Example 2:")
	private WebElement secondExample_Link;
	
	@FindBy(how = How.TAG_NAME, using = "button")
	private WebElement start_Button;
	
	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.and(
				ExpectedConditions.urlContains(PAGE_URL_LABEL),
				ExpectedConditions.textToBePresentInElement(pageHeader, PAGE_HEADER)
				));
	}
	
	private void goToFirstExample() {
		clickElement(firstExample_Link);
		wait.until(ExpectedConditions.textToBePresentInElement(pageSecondHeader, FIRST_EXAMPLE_TEXT));
	}
	
	private void goToSecondExample() {
		clickElement(secondExample_Link);
		wait.until(ExpectedConditions.textToBePresentInElement(pageSecondHeader, SECOND_EXAMPLE_TEXT));
	}
	
	public void goToExample(String exampleText) {
		if(exampleText.equals(FIRST_EXAMPLE_TEXT)) {
			goToFirstExample();
		}else if(exampleText.equals(SECOND_EXAMPLE_TEXT)) {
			goToSecondExample();
		}
	}
	
	public void startLoading() {
		clickElement(start_Button);
	}
	
	public void waitForElementToBeFound() {
		int count = 1;
		while (count <= 20) {
			try {
				WebElement element = driver.findElement(elementLocator);
				if (element.isDisplayed())
					break;
			} catch (ElementNotVisibleException | NoSuchElementException ex) {
				System.out.println(ex.getMessage());
				Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
			}
		}
	}
	
	public String getElementText() {
		return driver.findElement(elementLocator).getText();
	}
	
	private void clickElement(WebElement element) {
		element.click();
	}

}
