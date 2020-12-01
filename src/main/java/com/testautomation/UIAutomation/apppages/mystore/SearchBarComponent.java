package com.testautomation.UIAutomation.apppages.mystore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class SearchBarComponent extends UIComponent{
	
	@FindBy(how = How.ID, using = "searchbox")
	private WebElement searchBox;
	
	@FindBy(how = How.ID, using = "#search_query_top")
	private WebElement searchTextbox;
	
	@FindBy(how = How.NAME, using = "submit_search")
	private WebElement searchIcon;
	
	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> searchBox.isDisplayed());
	}
	
	public void searchFor(String productName) {
		searchTextbox.clear();
		searchTextbox.sendKeys(productName);
		Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
		searchTextbox.submit();
	}
	
}
