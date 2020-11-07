package com.testautomation.UIAutomation.apppages.myntra;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class MenOptions {

	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	public MenOptions() {
		PageFactory.initElements(elementLocatorFactory, this);
	}

	@FindBy(how = How.CSS, using = "div.desktop-categoryContainer[data-group='men']")
	private WebElement menOptionsContainer;
	
	@FindBy(how = How.LINK_TEXT, using = "Sneakers")
	private WebElement sneakers_Link;
	
	public MenSneakersPage goToMenSneakersPage() {
		if(menOptionsContainer.isDisplayed()) {
			sneakers_Link.click();
			return new MenSneakersPage();
		}else {
			throw new ElementNotVisibleException("MenOptions Container not loaded");
		}
	}
}
