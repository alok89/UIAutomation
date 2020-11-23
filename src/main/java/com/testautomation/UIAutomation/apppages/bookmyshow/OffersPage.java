package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class OffersPage extends BasePage {
	
	private static final String TITLE = "Movie Ticket Offers - Promo Codes, Deals & Discount Coupons Today – BookMyShow";
	
	@FindBy(how = How.CSS, using = "div.offer-list-page .filters ul>li")
	private List<WebElement> optionsList;
	
	private List<WebElement> cardOffers;
	
	private final By locator = By.cssSelector("span:nth-child(3)");

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.titleIs(TITLE));
	}
	
	public WebElement selectPaymentOption(String paymentOption) {
		WebElement option = null;
		Optional<WebElement> payOption = optionsList.stream()
												.filter(element -> paymentOption.equals(element.findElement(locator).getText().trim()))
												.findFirst();
		if(payOption.isPresent()) {
			option = payOption.get();
			option.click();
		}
		return option;
	}
	
	public boolean isPaymentOptionSelected(String paymentOption) {
		final String locator = "//span[text()='" + paymentOption + "']/parent::li";
		WebElement selectedOption = wait.until(driver -> driver.findElement(By.xpath(locator)));
		return selectedOption.getAttribute("class").equals("_active");
	}
	
	public int getTotalNumberOfCardOffers() {
		return getAllCardOffers().size();
	}
	
	public List<String> getAllCardOffers() {
		cardOffers = wait.until(driver -> driver.findElements(By.cssSelector("div.offer-list-page .list aside")));
		return cardOffers.stream()
					.filter(cardOffer -> cardOffer.isDisplayed())
					.map(cardOffer -> cardOffer.findElement(By.tagName("h4")).getText())
					.collect(Collectors.toList());
	}

}
