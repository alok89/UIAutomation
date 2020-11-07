package com.testautomation.UIAutomation.apppages.makemytrip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MMTLandingPage extends BasePage {

	@Value("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday")
	private String pageTitle;
	
	@Value("Login or Create Account")
	private String label;
	
	@FindBy(how = How.CSS, using = "div.flexOne.whiteText.latoBold>p")
	private WebElement loginOrCreateAccount_Label;

	@Override
	public boolean at() {
		return hasTitleMatched(pageTitle) && 
						isElementContainsText(loginOrCreateAccount_Label, label);
	}

}
