package com.testautomation.UIAutomation.apppages.myntra;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class AppHeader {

	@Autowired
	private WebDriver driver;
	
	@Autowired
	private WebDriverWait wait;
	
	@Autowired
	private Actions actionBuilder;
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	public AppHeader() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	/*@FindBy(how = How.CSS, using = "div.desktop-userIconsContainer>span.desktop-userTitle")
	private WebElement profile_Tab;*/
	
	@FindBy(how = How.CSS, using = "a.desktop-wishlist>span.desktop-userTitle")
	private WebElement wishlist_Tab;
	
	@FindBy(how = How.CSS, using = "a.desktop-cart>span.desktop-userTitle")
	private WebElement bag_Tab;
	
	@FindBy(how = How.CSS, using = "a[data-group='men']")
	private WebElement menOptions_Tab;
	
	public ProfileOptions goToProfileOptions() {
		By locator = By.cssSelector("div.desktop-userIconsContainer>span.desktop-userTitle");
		WebElement profile_Tab = null;
		try {
			profile_Tab = wait.until(ExpectedConditions.elementToBeClickable(locator));
		}catch(StaleElementReferenceException ex){
			profile_Tab = driver.findElement(locator);
		}
		profile_Tab.click();
		return new ProfileOptions();
	}
	
	public MenOptions goToMenOptions() {
		actionBuilder.moveToElement(menOptions_Tab).perform();
		return new MenOptions();
	}
}
