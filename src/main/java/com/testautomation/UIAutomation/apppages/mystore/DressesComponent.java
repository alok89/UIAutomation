package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class DressesComponent extends UIComponent {
	
	@LazyAutowired
	private ProductsComponent productsComponent;
	
	@FindBy(how = How.XPATH, using = "(//ul[contains(@class, 'submenu-container')])[2]")
	private WebElement submenu;
	
	@FindBy(how = How.XPATH, using = "(//*[text()='Summer Dresses'])[2]")
	private WebElement summerDresses;

	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> submenu.isDisplayed());
	}
	
	public ProductsComponent goToSummerDresses() {
		summerDresses.click();
		return productsComponent;
	}

}
