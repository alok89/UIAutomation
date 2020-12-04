package com.testautomation.UIAutomation.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.apppages.herokuapp.DataTablesPage;
import com.testautomation.UIAutomation.apppages.herokuapp.DynamicLoadingPage;
import com.testautomation.UIAutomation.apppages.herokuapp.HerokuAppLandingPage;
import com.testautomation.UIAutomation.apppages.herokuapp.HorizontalSliderPage;
import com.testautomation.UIAutomation.apppages.herokuapp.KeyPressesPage;
import com.testautomation.UIAutomation.apppages.herokuapp.SecureFileDownloadPage;
import com.testautomation.UIAutomation.utils.FileDownloadService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestHerokuApp {
	
	/*
	 * static { ThreadContext.put("ROUTINGKEY", "TestHerokuApp"); }
	 * 
	 * private static final Logger logger =
	 * LogManager.getLogger(ThreadContext.get("ROUTINGKEY"));
	 */

	@LazyAutowired
	private HerokuAppLandingPage herokuAppLandingPage;

	@LazyAutowired
	private FileDownloadService fileDownloadService;

	@Value("${application.herokuapp_url}")
	private String url;
	
	@Value("Email")
	private String columnName;
	
	private DataTablesPage dataTablesPage;
	
	private SecureFileDownloadPage fileDownloadPage;
	
	private DynamicLoadingPage dynamicLoadingPage;
	
	private HorizontalSliderPage horizontalSliderPage;
	
	private KeyPressesPage keyPressesPage;
	
	private List<WebElement> links;

	@Given("user is on HerokuApp Landing Page")
	public void userIsOnHerokuAppLanding_Page() {
		herokuAppLandingPage.goTo(url);
		assertThat(herokuAppLandingPage.at()).isTrue();
	}

	@When("user hit the links")
	public void userHitTheLinks() {
		links = herokuAppLandingPage.getAllLinks();
		assertThat(links).isNotEmpty();
	}

	@Then("it should show all the broken links by returning the error code")
	public void itShouldShowAllTheBrokenLinksByReturningTheErrorCode() {
		List<String> workingLinks = herokuAppLandingPage.getAllWorkingLinks();
		assertThat(workingLinks).hasSizeGreaterThan(35);
	}
	
	@When("user navigates to Secure File Download Page")
	public void userNavigatesToSecureFileDownloadPage() {
		fileDownloadPage = herokuAppLandingPage.goToSecureFileDownloadPage();
		assertThat(fileDownloadPage.at()).isTrue();
	}

	@And("download the {string} link")
	public void downloadTheLink(String fileName) {
		fileDownloadPage.downLoadLink(fileName);
	}

	@Then("the {string} file should get downloaded")
	public void theFileShouldGetDownloaded(String fileName) {
		assertThat(fileDownloadService.hasFileDownloaded(fileName)).isTrue();
	}

	@When("user navigates to Sortable Data Tables Page")
	public void userNavigatesToSortableDataTablesPage() {
		dataTablesPage = herokuAppLandingPage.goToDataTablesPage();
		assertThat(dataTablesPage.at()).isTrue();
	}

	@Then("user should be able to fetch all the emailids from the datatable")
	public void userShouldBeAbleToFetchAllTheEmailidsFromTheDatatable() {
		List<String> emailIds = dataTablesPage.getAllValuesFromAColumn(columnName);
		assertThat(emailIds).hasSize(4);
	}

	@Given("user is on dynamic loading page")
	public void userIsOnDynamicLoadingPage() {
	    dynamicLoadingPage = herokuAppLandingPage.goToDynamicLoadingPage();
	    assertThat(dynamicLoadingPage.at()).isTrue();
	}

	@When("user clicks on {string}")
	public void userClicksOn(String exampleLink) {
	    dynamicLoadingPage.goToExample(exampleLink.trim());
	}

	@When("start the loading process")
	public void startTheLoadingProcess() {
	    dynamicLoadingPage.startLoading();
	}

	@Then("the element {string} should get rendered")
	public void theElementShouldGetRendered(String expectedText) {
	    dynamicLoadingPage.waitForElementToBeFound();
	    assertThat(dynamicLoadingPage.getElementText()).isEqualTo(expectedText);
	}

	@Given("user is on horizontal slider page")
	public void userIsOnHorizontalSliderPage() {
	    horizontalSliderPage = herokuAppLandingPage.goToHorizontalSliderPage();
	    assertThat(horizontalSliderPage.at()).isTrue();
	}

	@When("moves the slider to {string}")
	public void movesTheSliderTo(String value) {
	    horizontalSliderPage.moveSliderTo(Double.parseDouble(value));
	}

	@Then("the {string} should get updated accordingly")
	public void theShouldGetUpdatedAccordingly(String expectedRange) {
	    String actualValue = String.valueOf(horizontalSliderPage.getSliderValue());
	    assertThat(actualValue).isEqualTo(expectedRange);
	}

	@Given("user is on key_presses page")
	public void userIsOnKeyPressesPage() {
	    keyPressesPage = herokuAppLandingPage.goToKeyPressesPage();
	    assertThat(keyPressesPage.at()).isTrue();
	}

	@When("user enters the {string}")
	public void userEntersThe(String key) {
	    keyPressesPage.pressKey(key);
	}

	@Then("{string} should get displayed")
	public void shouldGetDisplayed(String expectedKey) {
	    String actualKey = keyPressesPage.getPressedKey();
	    if(expectedKey.equals("ARROW_RIGHT")) {
	    	expectedKey = "RIGHT";
	    }
	    assertThat(actualKey).isEqualTo(expectedKey);
	}

}
