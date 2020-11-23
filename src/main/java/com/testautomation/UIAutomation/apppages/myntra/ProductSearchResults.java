package com.testautomation.UIAutomation.apppages.myntra;

import java.time.Clock;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class ProductSearchResults extends SlowLoadableComponent<ProductSearchResults> {

	@Autowired
	private WebDriver driver;
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;

	public ProductSearchResults() {
		super(Clock.systemDefaultZone(), 30);
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	@FindBy(how = How.CSS, using = "ul.results-base>li .product-productMetaInfo")
	private List<WebElement> productsList;
	
	public void addProductToWishlist(String brandName, String modelName) {
		if(productsList.size() > 1) {
			Iterator<WebElement> iterator = productsList.iterator();
			while(iterator.hasNext()) {
				WebElement product = iterator.next();
				String brand = product.findElement(By.cssSelector("h3.product-brand")).getText();
				String model = product.findElement(By.cssSelector("h4.product-product")).getText();
				if(brand.trim().equals(brandName) && model.trim().equals(modelName)){
					System.out.println("Brand : "+brand);
					System.out.println("Model : "+model);
				}
			}
		}
	}

	@Override
	protected void load() {
		//Not required as of now
	}

	@Override
	protected void isLoaded() throws Error {
		boolean isVisible = false;
		try {
			WebElement results = driver.findElement(By.cssSelector("div.search-searchProductsContainer ul.results-base"));
			WebElement pagination = driver.findElement(By.cssSelector("div.results-showMoreContainer>ul.pagination-container"));
			if((results.isDisplayed() && results.isEnabled()) && 
								(pagination.isDisplayed() && pagination.isEnabled())) {
				isVisible = true;
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		if(!isVisible) {
			throw new Error("Search Results could not loaded");
		}
	}

}
