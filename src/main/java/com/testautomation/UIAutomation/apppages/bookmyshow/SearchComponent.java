package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class SearchComponent {
	
	@Autowired
	private WebDriverWait wait;

	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	@FindBy(how = How.CSS, using = "input[placeholder='Search for Movies, Events, Plays, Sports and Activities']")
	private WebElement searchQueryBar;
	
	@FindBy(how = How.CSS, using = "ul[class='sc-cmjSyW ddmxNK']>li span>strong")
	private List<WebElement> searchElementsList;
	
	@Autowired
	private SpecificEventsPage eventsPage;
	
	public boolean searchValue(String searchText) {
		searchQueryBar.click();
		searchQueryBar.sendKeys(searchText);
		return wait.until(dr -> searchElementsList.size() >= 1);
	}
	
	public List<String> getAllSuggestions() {
		return searchElementsList.stream()
								.map(element -> element.getText().trim())
								.collect(Collectors.toList());
	}
	
	public SpecificEventsPage selectValueFromSuggestions(String value) {
		Optional<WebElement> searchedElement = searchElementsList.stream()
															.filter(element -> element.getText().trim().equals(value))
															.findFirst();
		if(searchedElement.isPresent()) {
			searchedElement.get().click();
			return eventsPage;
		}else {
			throw new RuntimeException("Searched Item not found in the suggestions");
		}
	}
	
}
