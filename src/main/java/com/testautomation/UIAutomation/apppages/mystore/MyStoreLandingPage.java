package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MyStoreLandingPage extends BasePage {

	@FindBy(how = How.ID, using = "header_logo")
	private WebElement myStoreImage;
	
	@Autowired
	private HeaderComponent headerComponent;
	
	@Override
	public boolean at() {
		return wait.until(driver -> myStoreImage.isDisplayed() && driver.getTitle().equals("My Store"));
	}
	
	public HeaderComponent getHeader() {
		return headerComponent;
	}

}
