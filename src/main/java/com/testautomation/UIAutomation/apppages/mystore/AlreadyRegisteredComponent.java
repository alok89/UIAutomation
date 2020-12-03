package com.testautomation.UIAutomation.apppages.mystore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class AlreadyRegisteredComponent extends UIComponent {
	
	@FindBy(how = How.ID, using = "login_form")
	private WebElement alreadyRegistered;
	
	@FindBy(how = How.ID, using = "email")
	private WebElement emailTextbox;
	
	@FindBy(how = How.ID, using = "passwd")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.ID, using = "SubmitLogin")
	private WebElement submitBtn;
	
	@LazyAutowired
	private MyStoreHomePage myStoreHomePage;

	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> alreadyRegistered.isDisplayed());
	}
	
	public MyStoreHomePage login(String emailId, String password) {
		setEmail(emailId);
		setPassword(password);
		submitBtn.click();
		Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
		return myStoreHomePage;
	}

	void setEmail(String emailId) {
		emailTextbox.clear();
		emailTextbox.sendKeys(emailId);
	}

	void setPassword(String password) {
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
	}

}
