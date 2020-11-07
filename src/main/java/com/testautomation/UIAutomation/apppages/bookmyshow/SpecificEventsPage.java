package com.testautomation.UIAutomation.apppages.bookmyshow;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class SpecificEventsPage extends BasePage {
	
	@FindBy(how = How.CSS, using = "header h1")
	private WebElement eventHeader;

	@Override
	public boolean at() {
		return driver.getCurrentUrl().contains("events");
	}
	
	public String getEventTitle() {
		return eventHeader.getText().trim();
	}

}
