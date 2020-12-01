package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class HomePage extends BasePage {

	@FindBy(how = How.ID, using = "header_logo")
	private WebElement myStoreImage;
	
	@Autowired
	private HeaderComponent headerComponent;
	
	@Value("${application.mystore.username}")
	private String loggedInUser;
	
	@Override
	public boolean at() {
		return loggedInUser.equals(headerComponent.getLoggedInUserName());
	}

}
