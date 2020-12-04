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

	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> !productsList.isEmpty());
	}
	
	private void setProductDetails(String productName, String productPrice) {
		System.out.println("Setting the product details");
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	private WebElement getProduct() {
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
	
	public void hoverOnProductAndAddtoCart(String productName, String productPrice) {
		setProductDetails(productName, productPrice);
		WebElement product = getProduct();
		actions.moveToElement(product).perform();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		addProductToCart(product);
	}
	
	private void addProductToCart(WebElement product) {
		product.findElement(By.cssSelector("div.right-block a.button[title='Add to cart']")).click();
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
	}

}
