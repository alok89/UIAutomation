package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class HeaderComponent extends UIComponent {
	
	@FindBy(how = How.CSS, using = "div.header_user_info>a.login")
	private WebElement signInBtn;
	
	@FindBy(how = How.CSS, using = "div.header_user_info>a.account>span")
	private WebElement userName;
	
	@FindBy(how = How.CSS, using = "div.header_user_info>a.logout")
	private WebElement signOut;
	
	@Override
	public boolean isDisplayed() {
		wait.until(driver -> signInBtn.isDisplayed());
		return false;
	}
	
	public void goToSignIn() {
		signInBtn.click();
	}
	
	public String getLoggedInUserName() {
		return userName.getText().trim();
	}
	
	public void logout() {
		signOut.click();
	}
	
	public AuthenticationComponent getAuthentication() {
		return new AuthenticationComponent();
	}
	
}
