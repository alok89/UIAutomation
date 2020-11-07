package com.testautomation.UIAutomation.apppages.webdriveruniversity;

import org.springframework.context.annotation.Lazy;

@Lazy
public enum WebDriverUniversityPageTitles {
	
	HOME_PAGE("WebDriverUniversity.com"),
	TODOLIST_PAGE("WebDriver | To Do List"),
	IFRAME_PAGE("WebDriver | IFrame"),
	CONTACTUS_PAGE("WebDriver | Contact Us");
	
	private String pageTitle;
	
	WebDriverUniversityPageTitles(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	public String getPageTitle() {
		return pageTitle;
	}

}
