package com.testautomation.UIAutomation.apppages.webdriveruniversity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class ContactUsPage extends BasePage {

	@FindBy(how = How.NAME, using = "first_name")
	private WebElement firstName_Textbox;
	
	@FindBy(how = How.NAME, using = "last_name")
	private WebElement lastName_Textbox;
	
	@FindBy(how = How.NAME, using = "email")
	private WebElement emailAddress_Textbox;
	
	@FindBy(how = How.NAME, using = "message")
	private WebElement comments_Textbox;
	
	@FindBy(how = How.CSS, using = "input.contact_button[type='submit']")
	private WebElement submit_Button;
	
	@FindBy(how = How.CSS, using = "input.contact_button[type='reset']")
	private WebElement reset_Button;
	
	@FindBy(how = How.CSS, using = "#contact_reply>h1")
	private WebElement successfulMessage_Text;
	
	@FindBy(how = How.TAG_NAME, using = "body")
	private WebElement errorMessage_Text;

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.titleIs(WebDriverUniversityPageTitles.CONTACTUS_PAGE.getPageTitle()));
	}
	
	public void enterFirstName(String firstName) {
		enterValue(firstName_Textbox, firstName);
	}
	
	public void enterLastName(String lastName) {
		enterValue(lastName_Textbox, lastName);
	}
	
	public void enterEmail(String emailAddress) {
		enterValue(emailAddress_Textbox, emailAddress);
	}
	
	public void enterComments(String comments) {
		enterValue(comments_Textbox, comments);
	}
	
	public void submitData() {
		submit_Button.submit();
	}
	
	public void resetValues() {
		reset_Button.click();
	}
	
	public boolean whetherAllFieldsContainValues() {
		final String attributeName = "value";
		String firstName = firstName_Textbox.getAttribute(attributeName);
		String lastName = lastName_Textbox.getAttribute(attributeName);
		String emailAddress = emailAddress_Textbox.getAttribute(attributeName);
		String comments = comments_Textbox.getAttribute(attributeName);
		return ((!firstName.isEmpty()) && (!lastName.isEmpty()) && (!emailAddress.isEmpty()) && (!comments.isEmpty()));
	}
	
	public boolean whetherAllFieldsAreBlank() {
		clearValue(firstName_Textbox);
		clearValue(lastName_Textbox);
		clearValue(emailAddress_Textbox);
		clearValue(comments_Textbox);
		return (!whetherAllFieldsContainValues());
	}
	
	public String getSuccessfulMessage() {
		return successfulMessage_Text.getText();
	}
	
	public String getErrorMessage() {
		return errorMessage_Text.getText();
	}
	
	private void enterValue(WebElement element, String value) {
		clearValue(element);
		element.sendKeys(value);
	}
	
	private void clearValue(WebElement element) {
		element.click();
		element.clear();
	}

}
