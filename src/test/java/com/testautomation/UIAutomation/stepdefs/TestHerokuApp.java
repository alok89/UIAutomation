package com.testautomation.UIAutomation.stepdefs;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.apppages.herokuapp.DataTablesPage;
import com.testautomation.UIAutomation.apppages.herokuapp.HerokuAppLandingPage;
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
	
	private List<WebElement> links;

	@Given("I am on the HerokuApp Landing Page")
	public void iAmOnTheHerokuAppLanding_Page() {
		herokuAppLandingPage.goTo(url);
		Assert.assertTrue(herokuAppLandingPage.at());
	}

	@When("I hit the links")
	public void iHitTheLinks() {
		links = herokuAppLandingPage.getAllLinks();
		Assert.assertTrue(!links.isEmpty());
	}

	@Then("It should show all the broken links by returning the error code")
	public void itShouldShowAllTheBrokenLinksByReturningTheErrorCode() {
		List<String> workingLinks = herokuAppLandingPage.getAllWorkingLinks();
		Assert.assertTrue(workingLinks.size() > 35);
	}
	
	@When("I navigate to Secure File Download Page")
	public void iNavigateToSecureFileDownloadPage() {
		fileDownloadPage = herokuAppLandingPage.goToSecureFileDownloadPage();
		Assert.assertTrue(fileDownloadPage.at());
	}

	@And("Click on the {string} link")
	public void clickOnAnyDownloadableLink(String fileName) {
		fileDownloadPage.downLoadLink(fileName);
	}

	@Then("The {string} file should get downloaded")
	public void theFileShouldGetDownloaded(String fileName) {
		Assert.assertTrue(fileDownloadService.hasFileDownloaded(fileName));
	}

	@When("I navigate to Sortable Data Tables Page")
	public void iNavigateToSortableDataTablesPage() {
		dataTablesPage = herokuAppLandingPage.goToDataTablesPage();
		Assert.assertTrue(dataTablesPage.at());
	}

	@Then("User should be able to fetch all the emailids from the datatable")
	public void userShouldBeAbleToFetchAllTheEmailidsFromTheDatatable() {
		List<String> emailIds = dataTablesPage.getAllValuesFromAColumn(columnName);
		Assert.assertTrue(emailIds.size() == 4);
	}

}
