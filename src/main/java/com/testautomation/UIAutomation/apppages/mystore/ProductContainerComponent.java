package com.testautomation.UIAutomation.apppages.mystore;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class ProductContainerComponent extends UIComponent {
	
	private String productName;
	
	private String productPrice;
	
	@FindBy(how = How.CSS, using = "ul.product_list>li>div.product-container")
	private List<WebElement> productsList;
	
	public ProductContainerComponent(String productName, String productPrice) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
	}

	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> !productsList.isEmpty());
	}
	
	public WebElement getProduct() {
		By prodNameLocator = By.cssSelector("div.right-block a.product-name");
		By prodPriceLocator = By.cssSelector("div.right-block span[itemprop='price']");
		Optional<WebElement> product = productsList.stream()
												.filter(element -> {
													String name = element.findElement(prodNameLocator).getText();
													String price = element.findElement(prodPriceLocator).getText();
													return productName.equals(name) && productPrice.equals(price);
												})
												.findFirst();
		return product.get();
	}
	
	public WebElement hoverOnProduct() {
		WebElement product = getProduct();
		actions.moveToElement(product).perform();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		return product;
	}
	
	public void clickAddToCart() {
		WebElement product = hoverOnProduct();
		product.findElement(By.cssSelector("div.right-block a.button[title='Add to cart']")).click();
	}

}
