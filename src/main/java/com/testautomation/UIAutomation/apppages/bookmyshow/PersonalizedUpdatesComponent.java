package com.testautomation.UIAutomation.apppages.bookmyshow;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class PersonalizedUpdatesComponent {
	
	private static final String HEADING = "Get Personalized Updates";
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(elementLocatorFactory, this);
	}

	@FindBy(how = How.CSS, using = "div.wzrk-alert-heading")
	private WebElement popup_Text;
	
	@FindBy(how = How.ID, using = "wzrk-cancel")
	private WebElement cancel_Button;
	
	@FindBy(how = How.ID, using = "wzrk-confirm")
	private WebElement confirm_Button;
	
	public boolean at() {
		return HEADING.equals(popup_Text.getText().trim());
	}
	
	public void accept() {
		confirm_Button.click();
	}
	
	public void dismiss() {
		cancel_Button.click();
	}
}
