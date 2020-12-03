package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class DressesComponent extends WomenComponent {
	
	@FindBy(how = How.XPATH, using = "(//ul[contains(@class, 'submenu-container')])[2]")
	private WebElement submenu;

	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> submenu.isDisplayed());
	}

}
