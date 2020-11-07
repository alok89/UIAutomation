package com.testautomation.UIAutomation.apppages.myntra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MyntraLoginPage extends BasePage {

	@FindBy(how = How.CSS, using = "input[class$='form-control mobileNumberInput']")
	private WebElement mobileNumber_Textbox;
	
	@FindBy(how = How.CSS, using = "div.submitBottomOption")
	private WebElement continue_Button;
	
	@FindBy(how = How.ID, using = "mobileNumberPass")
	private WebElement email_Textbox;
	
	@FindBy(how = How.CSS, using = "input[type='password']")
	private WebElement password_Textbox;
	
	@FindBy(how = How.CSS, using = "div.authPage div.signInContainer")
	private WebElement signInContainer;
	
	@FindBy(how = How.CSS, using = "div.authPage>div.formContainer")
	private WebElement loginFormContainer;

	@Override
	public boolean at() {
		String title = driver.getTitle();
		return title.equals(MyntraPageTitles.LOGIN_PAGE.getTitleName());
	}
	
	public void enterMobileNumberAndContinue(String mobileNumber) {
		if(signInContainer.isDisplayed() && signInContainer.isEnabled()) {
			mobileNumber_Textbox.click();
			mobileNumber_Textbox.sendKeys(mobileNumber);
			continue_Button.click();
		}else {
			throw new RuntimeException();
		}
	}
	
	private void switchToLoginForm() {
		final By containerLocator = By.className("verificationContainer");
		try {
			WebElement verificationContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(containerLocator));
			if(verificationContainer.isEnabled()) {
				final By passwordlocator = By.xpath("//div[@class='verificationContainer']//span[text()=' Password ']");
				driver.findElement(passwordlocator).click();
			}
		}catch(Exception ex) {
			System.err.println(ex);
		}
	}
	
	private void enterEmailID(String emailId) {
		email_Textbox.click();
		email_Textbox.clear();
		email_Textbox.sendKeys(emailId);
	}
	
	private void enterPassword(String password) {
		password_Textbox.click();
		password_Textbox.sendKeys(password);
	}
	
	private void submitAndNavigatetoHomePage() {
		final By locator = By.xpath("//button[text()='LOGIN']");
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
		loginButton.click();
	}
	
	public MyntraHomePage submitLoginCredentialsAndgoToHomePage(String emailId, String password) {
		switchToLoginForm();
		if(loginFormContainer.isEnabled()) {
			enterEmailID(emailId);
			enterPassword(password);
			submitAndNavigatetoHomePage();
		}else {
			throw new RuntimeException("LoginForm Container not found");
		}
		return new MyntraHomePage();
	}

}
