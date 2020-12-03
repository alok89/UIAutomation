package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class HeaderComponent extends UIComponent {
	
	@FindBy(how = How.CSS, using = "div.header_user_info>a.login")
	private WebElement signInBtn;
	
	@FindBy(how = How.CSS, using = "div.header_user_info>a.account>span")
	private WebElement userName;
	
	@FindBy(how = How.CSS, using = "div.header_user_info>a.logout")
	private WebElement signOut;
	
	@LazyAutowired
	private AuthenticationComponent authenticationComponent;
	
	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> signInBtn.isDisplayed());
	}
	
	public AuthenticationComponent goToSignIn() {
		signInBtn.click();
		return authenticationComponent;
	}
	
	public String getLoggedInUserName() {
		return userName.getText().trim();
	}
	
	public void logout() {
		signOut.click();
	}
	
}
