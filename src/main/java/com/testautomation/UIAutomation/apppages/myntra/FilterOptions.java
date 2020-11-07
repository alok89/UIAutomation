package com.testautomation.UIAutomation.apppages.myntra;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class FilterOptions {

	@Autowired
	private WebDriver driver;
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	private JavascriptExecutor js;

	public FilterOptions() {
		PageFactory.initElements(elementLocatorFactory, this);
		js = (JavascriptExecutor) driver;
	}

	@FindBy(how = How.CSS, using = "ul.brand-list")
	private WebElement brandOptionsContainer;

	@FindBy(how = How.CSS, using = "ul.price-list")
	private WebElement priceOptionsContainer;

	public void selectBrand(String brandName) {
		if(brandOptionsContainer.isDisplayed()) {
			By locator = By.xpath("//input[@type='checkbox'and@value='"+brandName+"']");
			WebElement brand = driver.findElement(locator); 
			clickUsingJs(brand);
		}else {
			System.err.println("Brand Filter Options not found");
		}
	}

	public void selectPrice(String priceName) {
		if(priceOptionsContainer.isDisplayed()) {
			By locator = By.xpath("//ul[@class='price-list']//input[@type='checkbox'and@value='"+priceName+"']");
			WebElement price = driver.findElement(locator);
			clickUsingJs(price);
		}else {
			System.err.println("Price Filter Options not found");
		}
	}

	/*private void selectElement(List<WebElement> elementsList, String filterName) {
		for(WebElement element : elementsList) {
			System.out.println("Element's text : "+element.getAttribute("value"));
			System.out.println("Passed input : "+filterName);
			if(element.getAttribute("value").trim().equals(filterName)) {
				System.out.println("Found ");
				//actionBuilder.moveToElement(element).click().build().perform();
				js.executeScript("arguments[0].click();", element);
				break;
			}
		}
	}*/
	
	private void clickUsingJs(WebElement element) {
		Uninterruptibles.sleepUninterruptibly(2500, TimeUnit.MILLISECONDS);
		js.executeScript("arguments[0].click();", element);
		Uninterruptibles.sleepUninterruptibly(2500, TimeUnit.MILLISECONDS);
	}
}
