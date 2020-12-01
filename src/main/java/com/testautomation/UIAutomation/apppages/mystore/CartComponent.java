package com.testautomation.UIAutomation.apppages.mystore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class CartComponent extends UIComponent {
	
	@FindBy(how = How.CSS, using = "a[title='View my shopping cart']")
	private WebElement cart;
	
	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> cart.isDisplayed());
	}
	
	public void hoverOnCart() {
		actionBuilder.moveToElement(cart).perform();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
	}
	
	public int getNumberOfProductsAddedInCart() {
		try {
			WebElement cartQuantity = cart.findElement(By.cssSelector("span.ajax_cart_quantity"));
			return Integer.parseInt(cartQuantity.getText());
		} catch (RuntimeException ex) {
			System.err.println(ex.getMessage());
			return 0;
		}
	}

}
