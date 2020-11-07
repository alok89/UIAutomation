package com.testautomation.UIAutomation.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.testautomation.UIAutomation.apppages.webdriveruniversity.ContactUsPage;
import com.testautomation.UIAutomation.apppages.webdriveruniversity.WBDUniversityHomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestContactUsForm_WebDriverUniversityApp {
	
	static {
		ThreadContext.put("ROUTINGKEY", "TestContactUsForm_WebDriverUniversityApp");
	}
	
	private static final Logger logger = LogManager.getLogger(ThreadContext.get("ROUTINGKEY"));
	
	@Autowired
	private WBDUniversityHomePage homePage;

	@Value("${application.webdriveruniversity_url}")
	private String url;
	
	@Autowired
	private Faker faker;
	
	private ContactUsPage contactUsPage;

	@Given("I am on the WebDriverUniversity App Home Page")
	public void iAmOnTheWebDriverUniversityAppHomePage() {
		logger.info("Given - I am on the WebDriverUniversity App Home Page");
		homePage.goTo(url);
		Assert.assertTrue(homePage.at());
	}

	@Given("I navigate to ContactUs Page")
	public void iNavigateToContactUsPage() {
		logger.info("Given - I navigate to ContactUs Page");
		contactUsPage = homePage.goToContactUsPage();
		Assert.assertTrue(contactUsPage.at());
	}

	@When("I enter all the details i.e. FirstName, LastName, EmailId, Comments")
	public void iEnterAllTheDetailsIEFirstNameLastNameEmailIdComments() {
		logger.info("When - I enter all the details i.e. FirstName, LastName, EmailId, Comments");
		contactUsPage.enterFirstName(faker.name().firstName());
		contactUsPage.enterLastName(faker.name().lastName());
		contactUsPage.enterEmail(faker.internet().emailAddress());
		contactUsPage.enterComments(faker.address().secondaryAddress());
	}

	@When("Click on Submit button")
	public void clickOnSubmitButton() {
		logger.info("When - Click on Submit button");
		contactUsPage.submitData();
	}

	@Then("A successful message {string} should get displayed")
	public void aSuccessfulMessageShouldGetDisplayed(String expectedMessage) {
		logger.info("Then - A successful message should get displayed");
		String actualMessage = contactUsPage.getSuccessfulMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	@Then("An error message {string} should get displayed")
	public void anErrorMessageShouldGetDisplayed(String expectedMessage) {
		logger.info("Then - An error message should get displayed");
		String actualMessage = contactUsPage.getErrorMessage();
		Assert.assertTrue(actualMessage.contains(expectedMessage));
	}

	@When("I enter the details i.e. FirstName, LastName, Comments by leaving the email address blank")
	public void iEnterTheDetailsIEFirstNameLastNameCommentsByLeavingTheEmailAddressBlank() {
		logger.info("When - I enter the details i.e. FirstName, LastName, Comments by leaving the email address blank");
		contactUsPage.enterFirstName(faker.name().firstName());
		contactUsPage.enterLastName(faker.name().lastName());
		contactUsPage.enterComments(faker.address().secondaryAddress());
	}

	@When("Click on Reset button")
	public void clickOnResetButton() {
		logger.info("When - Click on Reset button");
		contactUsPage.resetValues();
	}

	@Then("All the entered values should get removed from the fields")
	public void allTheEnteredValuesShouldGetRemovedFromTheFields() {
		logger.info("Then - All the entered values should get removed from the fields");
		Assert.assertTrue(contactUsPage.whetherAllFieldsAreBlank());
	}

}
