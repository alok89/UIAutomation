package com.testautomation.UIAutomation.stepdefs;

import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.apppages.mystore.AlreadyRegisteredComponent;
import com.testautomation.UIAutomation.apppages.mystore.AuthenticationComponent;
import com.testautomation.UIAutomation.apppages.mystore.MyStoreHomePage;
import com.testautomation.UIAutomation.apppages.mystore.MyStoreLandingPage;
import com.testautomation.UIAutomation.apppages.mystore.ProductsComponent;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestMyStore {
	
	@Value("${application.mystore_url}")
	private String url;
	
	@Value("${application.mystore.emailid}")
	private String emailId;
	
	@Value("${application.mystore.password}")
	private String password;
	
	@LazyAutowired
	private MyStoreLandingPage myStoreLandingPage;
	
	private MyStoreHomePage myStoreHomePage;
	
	private ProductsComponent products;
	
	private AuthenticationComponent authenticationComponent;

	@Given("user is on home page")
	public void userIsOnHomePage() {
		myStoreLandingPage.goTo(url);
		if(myStoreLandingPage.at()) {
			authenticationComponent = myStoreLandingPage.getHeader().goToSignIn();
			AlreadyRegisteredComponent loginForm = authenticationComponent.getLoginForm();
			myStoreHomePage = loginForm.login(emailId, password);
			Assert.assertTrue(myStoreHomePage.at());
		}
	}

	@When("user navigates to section {string}")
	public void userNavigatestoDressesSection(String sectionName) {
	    if(sectionName.equals("Dresses")) {
	    	products = myStoreHomePage.getMenuBar().goToDressesSection().goToSummerDresses();
	    }else if(sectionName.equals("Women")) {
	    	products = myStoreHomePage.getMenuBar().goToWomenSection().goToSummerDresses();
	    }
	    Assert.assertTrue(products.isDisplayed());
	}

	@And("selects a product by {string} and {string}")
	public void selectsAProduct(String productName, String productPrice) {
		products.getProductContainer().hoverOnProductAndAddtoCart(productName, productPrice);
	}

	@Then("product should get added to cart")
	public void productShouldGetAddedToCart() {
	    int count = myStoreHomePage.getCart().getNumberOfProductsAddedInCart();
	    Assert.assertEquals(count, 1);
	}

}
