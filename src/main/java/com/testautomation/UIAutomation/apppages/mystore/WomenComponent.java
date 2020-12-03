package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class WomenComponent extends UIComponent {

	@FindBy(how = How.XPATH, using = "(//ul[contains(@class, 'submenu-container')])[1]")
	private WebElement submenu;
	
	@FindBy(how = How.LINK_TEXT, using = "T-shirts")
	private WebElement tShirts;
	
	@FindBy(how = How.LINK_TEXT, using = "Blouses")
	private WebElement blouses;
	
	@FindBy(how = How.LINK_TEXT, using = "Casual Dresses")
	private WebElement casualDresses;
	
	@FindBy(how = How.LINK_TEXT, using = "Evening Dresses")
	private WebElement eveningDresses;
	
	@FindBy(how = How.LINK_TEXT, using = "Summer Dresses")
	private WebElement summerDresses;
	
	@LazyAutowired
	private ProductsComponent productsComponent;
	
	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> submenu.isDisplayed());
	}
	
	public ProductsComponent goToSummerDresses() {
		summerDresses.click();
		return productsComponent;
	}
	
	public void goToTShirts() {
		tShirts.click();
	}

}
