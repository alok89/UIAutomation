package com.testautomation.UIAutomation.apppages.myntra;

import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MyntraLandingPage extends BasePage {

	@Autowired
	private AppHeader header;

	@Override
	public boolean at() {
		String title = driver.getTitle();
		return title.equals(MyntraPageTitles.LANDING_PAGE.getTitleName());
	}
	
	public ProfileOptions goToProfileOptions() {
		return header.goToProfileOptions();
	}

}
