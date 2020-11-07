package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class MovieFiltersComponent {
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	@FindBy(how = How.CSS, using = "div[class='style__FilterWrapper-uh5pb7-6 cmTres']")
	private List<WebElement> filtersList;
	
	private final By filterHeaderTitleLocator = By.cssSelector("div[class*='style__FilterHeaderTitle-uh5pb7-8']");
	
	private final By filterOptionsTitleLocator = By.cssSelector("div[class='style__StyledText-tgsgks-0 cAIFpf']");

	public void selectLanguages(String...languages) {
		for(String language : languages) {
			WebElement languageOption = getFilterOptionElement("Language", language);
			languageOption.click();
		}
	}
	
	public void selectGenres(String...genres) {
		for(String genre : genres) {
			WebElement genreOption = getFilterOptionElement("Genre", genre);
			genreOption.click();
		}
	}
	
	public boolean isFilterSectionInCollpasedState(String filterSection) {
		WebElement section = getFilterSectionElement(filterSection);
		By locator = By.cssSelector("div[class*='style__FilterBody-uh5pb7']");
		try {
			WebElement filterSectionBody = section.findElement(locator);
			return !filterSectionBody.isDisplayed();
		}catch(NoSuchElementException ex) {
			System.out.println(filterSection +" filter Section is in collapsed state...");
			return true;
		}
	}
	
	public void expandFilterSection(String filterSection) {
		if(isFilterSectionInCollpasedState(filterSection)) {
			WebElement filterElement = getFilterSectionElement(filterSection);
			filterElement.findElement(filterHeaderTitleLocator).click();
		}
	}
	
	private WebElement getFilterSectionElement(String filterSection) {
		Optional<WebElement> filterSectionElement = filtersList.stream().filter(element -> {
							WebElement textElement = element.findElement(filterHeaderTitleLocator);
							return textElement.getText().trim().equals(filterSection);
							})
							.findFirst();
		if (filterSectionElement.isPresent()) {
			return filterSectionElement.get();
		} else
			throw new RuntimeException(filterSection + " filter section not found");
	}
	
	public WebElement getFilterOptionElement(String filterSection, String filterOption) {
		WebElement filterSectionElement = getFilterSectionElement(filterSection);
		List<WebElement> filterOptionElements = filterSectionElement.findElements(filterOptionsTitleLocator);
		Optional<WebElement> filterOptionElement = filterOptionElements.stream()
															.filter(element -> element.getText().trim().equals(filterOption))
															.findFirst();
		if(filterOptionElement.isPresent()) {
			return filterOptionElement.get();
		} else
			throw new RuntimeException(filterOption+" filter option not found");
	}

}
