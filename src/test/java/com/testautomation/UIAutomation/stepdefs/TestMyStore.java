package com.testautomation.UIAutomation.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestMyStore {

	@Given("user is on home page")
	public void userIsOnMyStoreHomePage() {
	 
	}

	@When("user navigates to section {string}")
	public void userNavigatestoDressesSection(String sectionName) {
	    
	}

	@And("selects a product {string}")
	public void selectsAProduct(String productName) {
	   
	}

	@Then("product should get added to cart")
	public void productShouldGetAddedToCart() {
	    
	}

}
