package com.testautomation.UIAutomation.apppages.myntra;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MyntraHomePage extends BasePage {
	
	@Value("Akansha Singh")
	private String userTitle;
	
	@Value("9511713623")
	private String userMobile;
	
	@Autowired
	private AppHeader header;
	
	@Autowired
	private ProfileOptions profileOptions;
	
	@FindBy(how = How.CSS, using = "ul.filter-summary-filterList>li>div.filter-summary-filter")
	private List<WebElement> appliedFilters_List;

	@Override
	public boolean at() {
		profileOptions = header.goToProfileOptions().get();
		String title = profileOptions.getUserTitle();
		String emailOrMobile = profileOptions.getUserEmailOrMobile();
		return (title.contains(userTitle) && emailOrMobile.equals(userMobile));
	}
	
	public MenOptions goToMenSection() {
		return header.goToMenOptions();
	}
	
	public void logoutFromApp() {
		profileOptions = header.goToProfileOptions().get();
		profileOptions.logout();
	}

}
