package com.testautomation.UIAutomation.stepdefs;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

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

	@Autowired
	private HerokuAppLandingPage herokuAppLandingPage;

	@Autowired
	private FileDownloadService fileDownloadService;

	@Value("${application.herokuapp_url}")
	private String url;
	
	@Value("Email")
	private String columnName;
	
	private DataTablesPage dataTablesPage;
	
	private SecureFileDownloadPage fileDownloadPage;
	
	private List<WebElement> links;

	@Given("I am on the HerokuApp Landing Page")
	public void i_am_on_the_HerokuApp_Landing_Page() {
		//logger.info("Step Execution - I am on the HerokuApp Landing Page");
		herokuAppLandingPage.goTo(url);
		Assert.assertTrue(herokuAppLandingPage.at());
	}

	@When("I hit the links")
	public void i_hit_the_links() {
		//logger.info("Step Execution - I hit the links");
		links = herokuAppLandingPage.getAllLinksFromThePage();
		Assert.assertTrue(!links.isEmpty());
	}

	@Then("It should show all the broken links by returning the error code")
	public void it_should_show_all_the_broken_links_by_returning_the_error_code() {
		List<String> workingLinks = herokuAppLandingPage.getAllWorkingLinks(links);
		Assert.assertTrue(workingLinks.size() > 35);
	}
	
	@When("I navigate to File Download Page by clicking on Secure File Download Link")
	public void i_navigate_to_File_Download_Page_by_clicking_on_Secure_File_Download_Link() {
		fileDownloadPage = herokuAppLandingPage.goToSecureFileDownloadPage();
		Assert.assertTrue(fileDownloadPage.at());
	}

	@And("Click on the {string} link")
	public void click_on_any_downloadable_link(String fileName) {
		fileDownloadPage.clickLinkToDownLoad(fileName);
	}

	@Then("The {string} file should get downloaded")
	public void the_file_should_get_downloaded(String fileName) {
		Assert.assertTrue(fileDownloadService.hasFileDownloaded(fileName));
	}

	@When("I navigate to Data Tables Page by clicking on Sortable Data Tables link")
	public void i_navigate_to_Data_Tables_Page_by_clicking_on_Sortable_Data_Tables_link() {
		dataTablesPage = herokuAppLandingPage.goToDataTablesPage();
		Assert.assertTrue(dataTablesPage.at());
	}

	@Then("User should be able to fetch all the emailids from the datatable")
	public void user_should_be_able_to_fetch_all_the_emailids_from_the_datatable() {
		List<String> emailIds = dataTablesPage.getAllValuesFromAColumn(columnName);
		Assert.assertTrue(emailIds.size() == 4);
	}

}
