package com.testautomation.UIAutomation.apppages.myntra;

import java.time.Clock;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class ProfileOptions extends SlowLoadableComponent<ProfileOptions> {

	@Autowired
	private WebDriver driver;
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	public ProfileOptions() {
		super(Clock.systemDefaultZone(), 15);
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	@FindBy(how = How.CSS, using = "div.desktop-userActions")
	private WebElement userRelatedActions_DropWindow;
	
	@FindBy(how = How.CSS, using = "a.desktop-linkButton[data-track='login']")
	private WebElement login_Button;
	
	@FindBy(how = How.CSS, using = "div.desktop-userActionsContent div.desktop-getInLinks:nth-child(1)>a")
	private List<WebElement> profileSection1; 
	
	@FindBy(how = How.CSS, using = "div.desktop-userActionsContent div.desktop-getInLinks:nth-child(2)>a")
	private List<WebElement> profileSection2;
	
	@FindBy(how = How.CSS, using = "a[data-track='edit_profile']>div.desktop-infoTitle")
	private WebElement userTitle;
	
	@FindBy(how = How.CSS, using = "a[data-track='edit_profile']>div.desktop-infoEmail")
	private WebElement userEmail;
	
	@FindBy(how = How.CSS, using = "div.desktop-accActions>[data-track='edit_profile']>div")
	private WebElement editProfile_Link;
	
	@FindBy(how = How.CSS, using = "div.desktop-accActions>[data-track='logout']>div")
	private WebElement logout_Link;
	
	public MyntraLoginPage goToLoginPage() {
		login_Button.click();
		return new MyntraLoginPage();
	}
	
	public String getUserTitle() {
		return userTitle.getText();
	}
	
	public String getUserEmailOrMobile() {
		return userEmail.getText();
	}
	
	public void logout() {
		logout_Link.click();
	}

	@Override
	protected void load() {
		WebElement profileTab = driver.findElement(By.cssSelector("div.desktop-userIconsContainer"));
		profileTab.click();
	}

	@Override
	protected void isLoaded() throws Error {
		boolean isLoaded = false;
		try {
			WebElement section1 = driver.findElement(
					By.cssSelector("div.desktop-userActionsContent div.desktop-getInLinks:nth-child(1)"));
			WebElement section2 = driver.findElement(
					By.cssSelector("div.desktop-userActionsContent div.desktop-getInLinks:nth-child(2)"));
			isLoaded = ((section1.isDisplayed() && section1.isEnabled()) && 
										(section2.isDisplayed() && section2.isEnabled()));
		}catch(Exception ex) {
			System.err.println(ex);
		}
		if(!isLoaded) {
			throw new Error("Profile Options dropdown not loaded");
		}
	}	

}
