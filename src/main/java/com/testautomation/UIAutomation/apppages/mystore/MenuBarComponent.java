package com.testautomation.UIAutomation.apppages.mystore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class MenuBarComponent extends UIComponent {
	
	@LazyAutowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@LazyAutowired
	private WomenComponent womenComponent;
	
	@LazyAutowired
	private DressesComponent dressesComponent;
	
	@FindBy(how = How.CSS, using = "ul.sf-menu")
	private WebElement menubar;
	
	@FindBy(how = How.LINK_TEXT, using = "Women")
	private WebElement women;
	
	@FindBy(how = How.LINK_TEXT, using = "Dresses")
	private WebElement dresses;
	
	@FindBy(how = How.LINK_TEXT, using = "T-shirts")
	private WebElement tShirts;
	
	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> menubar.isDisplayed());
	}
	
	public WomenComponent goToWomenSection() {
		actionBuilder.moveToElement(women).perform();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		return womenComponent;
	}
	
	public DressesComponent goToDressesSection() {
		actionBuilder.moveToElement(dresses).perform();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		return dressesComponent;
	}
	
	public void goToTShirts() {
		tShirts.click();
	}
	
}
