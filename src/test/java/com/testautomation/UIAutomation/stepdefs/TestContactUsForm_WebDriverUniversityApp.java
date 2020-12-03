package com.testautomation.UIAutomation.stepdefs;

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
	
	/*
	 * static { ThreadContext.put("ROUTINGKEY",
	 * "TestContactUsForm_WebDriverUniversityApp"); }
	 */
	
	//private static final Logger logger = LogManager.getLogger(ThreadContext.get("ROUTINGKEY"));
	
	@Autowired
	private WBDUniversityHomePage homePage;

	@Value("${application.webdriveruniversity_url}")
	private String url;
	
	@Autowired
	private Faker faker;
	
	private ContactUsPage contactUsPage;

	@Given("I am on the WebDriverUniversity App Home Page")
	public void iAmOnTheWebDriverUniversityAppHomePage() {
		homePage.goTo(url);
		Assert.assertTrue(homePage.at());
	}

	@Given("I navigate to ContactUs Page")
	public void iNavigateToContactUsPage() {
		contactUsPage = homePage.goToContactUsPage();
		Assert.assertTrue(contactUsPage.at());
	}

	@When("I enter all the details i.e. FirstName, LastName, EmailId, Comments")
	public void iEnterAllTheDetailsIEFirstNameLastNameEmailIdComments() {
		contactUsPage.enterFirstName(faker.name().firstName());
		contactUsPage.enterLastName(faker.name().lastName());
		contactUsPage.enterEmail(faker.internet().emailAddress());
		contactUsPage.enterComments(faker.address().secondaryAddress());
	}

	@When("Click on Submit button")
	public void clickOnSubmitButton() {
		contactUsPage.submitData();
	}

	@Then("A successful message {string} should get displayed")
	public void aSuccessfulMessageShouldGetDisplayed(String expectedMessage) {
		String actualMessage = contactUsPage.getSuccessfulMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	@Then("An error message {string} should get displayed")
	public void anErrorMessageShouldGetDisplayed(String expectedMessage) {
		String actualMessage = contactUsPage.getErrorMessage();
		Assert.assertTrue(actualMessage.contains(expectedMessage));
	}

	@When("I enter the details i.e. FirstName, LastName, Comments by leaving the email address blank")
	public void iEnterTheDetailsIEFirstNameLastNameCommentsByLeavingTheEmailAddressBlank() {
		contactUsPage.enterFirstName(faker.name().firstName());
		contactUsPage.enterLastName(faker.name().lastName());
		contactUsPage.enterComments(faker.address().secondaryAddress());
	}

	@When("Click on Reset button")
	public void clickOnResetButton() {
		contactUsPage.resetValues();
	}

	@Then("All the entered values should get removed from the fields")
	public void allTheEnteredValuesShouldGetRemovedFromTheFields() {
		Assert.assertTrue(contactUsPage.whetherAllFieldsAreBlank());
	}

}
