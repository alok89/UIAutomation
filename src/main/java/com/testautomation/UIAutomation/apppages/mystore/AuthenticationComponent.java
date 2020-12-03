package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class AuthenticationComponent extends UIComponent {
	
	@FindBy(how = How.CSS, using = "h1.page-heading")
	private WebElement headerText;
	
	@LazyAutowired
	private AlreadyRegisteredComponent alreadyRegisteredComponent;

	@Override
	public boolean isDisplayed() {
		return headerText.getText().trim().equals("Authentication");
	}
	
	public AlreadyRegisteredComponent getLoginForm() {
		return alreadyRegisteredComponent;
	}

}
