package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class CitySelectionComponent {
	
	@Autowired
	private WebDriverWait wait;
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(elementLocatorFactory, this);
	}

	@FindBy(how = How.CSS, using = "div[direction='column'].sc-jTzLTM")
	private WebElement citiesListPopUp;
	
	@FindBy(how = How.CSS, using = "ul.sc-iybRtq.fYDbAn>li span")
	private List<WebElement> popularCitiesName;
	
	@FindBy(how = How.CSS, using = "ul.sc-iybRtq.fYDbAn>li>div")
	private List<WebElement> popularCities_List;
	
	public boolean hasPopupDisplayed() {
		return wait.until(driver -> {
			Dimension dimension = citiesListPopUp.getSize();
			return (dimension.getHeight() > 0 && dimension.getWidth() > 0);
		});
	}
	
	public void selectCity(String cityName) {
		WebElement cityElement = getCityElement(cityName);
		if(cityElement != null) {
			cityElement.click();
		}
	}
	
	protected boolean isCitySelected(String cityName) {
		WebElement cityElement = getCityElement(cityName);
		boolean isSelected = false;
		if(cityElement != null) {
			WebElement cityImageElement = cityElement.findElement(By.tagName("img"));
			String imageUrl = cityImageElement.getAttribute("src");
			isSelected = imageUrl.endsWith("selected.png");
			cityImageElement.click();
		}
		return isSelected;
	}
	
	private WebElement getCityElement(String cityName) {
		if (!popularCities_List.isEmpty()) {
			Optional<WebElement> webElement = popularCities_List.stream()
								.filter(element -> cityName.equalsIgnoreCase(element.findElement(By.tagName("span")).getText().trim()))
								.findFirst();
			if(webElement.isPresent()) {
				return webElement.get();
			}
		}
		return null;
	}
}
