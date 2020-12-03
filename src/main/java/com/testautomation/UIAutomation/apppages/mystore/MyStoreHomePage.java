package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MyStoreHomePage extends BasePage {

	@FindBy(how = How.ID, using = "header_logo")
	private WebElement myStoreImage;
	
	@LazyAutowired
	private HeaderComponent headerComponent;
	
	@LazyAutowired
	private MenuBarComponent menuBarComponent;
	
	@LazyAutowired
	private CartComponent cartComponent;
	
	@Value("${application.mystore.username}")
	private String loggedInUser;
	
	@Override
	public boolean at() {
		return loggedInUser.equals(headerComponent.getLoggedInUserName());
	}
	
	public MenuBarComponent getMenuBar() {
		return menuBarComponent;
	}
	
	public CartComponent getCart() {
		return cartComponent;
	}

}
