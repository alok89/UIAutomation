package com.testautomation.UIAutomation.apppages.mystore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.testautomation.UIAutomation.annotations.PageComponent;

@PageComponent
public class ProductsComponent extends UIComponent {
	
	@FindBy(how = How.ID, using = "center_column")
	private WebElement productsSection;
	
	@FindBy(how = How.CSS, using = "h1.page-heading>span.cat-name")
	private WebElement productCategoryName;
	
	@FindBy(how = How.CSS, using = "h1.page-heading>span.heading-counter")
	private WebElement productsCount;
	
	private ProductContainerComponent productContainerComponent;

	@Override
	public boolean isDisplayed() {
		return wait.until(driver -> productsSection.isDisplayed());
	}
	
	public String getProductCategoryName() {
		return productCategoryName.getText().trim();
	}
	
	public int getProductsCount() {
		String text = productsCount.getText();
		char[] characters = text.toCharArray();
		int count = 0;
		for(char ch : characters) {
			if(Character.isDigit(ch)) {
				count = Character.getNumericValue(ch);
			}
		}
		return count;
	}
	
	public void addProductToCart(String productName, String productPrice) {
		productContainerComponent = new ProductContainerComponent(productName, productPrice);
		if(productContainerComponent.isDisplayed()) {
			productContainerComponent.clickAddToCart();
		}else {
			System.err.println("Product not listed");
		}
	}

}
