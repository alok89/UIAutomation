package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class BMSLandingPage extends BasePage {
	
	private static final String PAGE_TITLE = "Movie Tickets, Plays, Sports, Events & Cinemas nearby - BookMyShow";
	
	@FindBy(how = How.CSS, using = "span.sc-gipzik.jyyMA")
	private WebElement selectedCityText;
	
	@FindBy(how = How.CSS, using = "div.sc-kEYyzF>div.sc-hMqMXs>a")
	private List<WebElement> headerItemsList;
	
	@FindBy(how = How.XPATH, using = "//div[starts-with(@class, 'sc-hMqMXs')]/a[text()='Offers']")
	private WebElement offersTab;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Search for Movies, Events, Plays, Sports and Activities']")
	private WebElement searchTextbox;
	
	@FindBy(how = How.CSS, using = "div.commonStyles__HorizontalFlexBox-sc-1k17atf-3 div[class='style__StyledText-tgsgks-0 dzGfYt']")
	private List<WebElement> sectionNames;
	
	@Autowired
	private MovieShowsPage movieShowsPage;
	
	@Autowired
	private CitySelectionComponent citySelection;
	
	@Autowired
	private PersonalizedUpdatesComponent personalizedUpdates;
	
	@Autowired
	private OffersPage offersPage;
	
	@Autowired
	private SearchComponent searchSection;
	
	@Autowired
	private TrendingSearchesSectionComponent trendingSection;
	
	@Autowired
	private WorkshopsForAllSectionComponent workshopsSection;
	
	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.titleIs(PAGE_TITLE));
	}
	
	public OffersPage goToOffersPage() {
		offersTab.click();
		return offersPage;
	}
	
	public MovieShowsPage goToMoviesPage() {
		selectAnItemFromTheHeaderList("Movies");
		System.out.println("Current URL : "+driver.getCurrentUrl());
		System.out.println("Current Page Title :"+driver.getTitle());
		return movieShowsPage;
	}
	
	public void selectTheCity(String cityName) {
		citySelection.selectCity(cityName);
	}
	
	public boolean hasCitySelectionPopUpAppeared() {
		return citySelection.hasPopupDisplayed();
	}
	
	public TrendingSearchesSectionComponent moveToTrendingSearchSection() {
		if (isSectionDisplayed("Trending Searches Right Now")) {
			return trendingSection;
		} else {
			throw new RuntimeException("Trending Searches Right Now section not present");
		}
	}

	public WorkshopsForAllSectionComponent moveToWorkshopsSection() {
		if (isSectionDisplayed("Workshops For All")) {
			System.out.println("Workshops For All Section found.");
			return workshopsSection;
		} else {
			throw new RuntimeException("Workshops For All section not present");
		}
	}
	
	public SearchComponent goToSearch() {
		searchTextbox.click();
		return searchSection;
	}
	
	public boolean isCitySelected(String cityName) {
		selectedCityText.click();
		return citySelection.isCitySelected(cityName);
	}
	
	public void enablePersonalizedUpdates(boolean decision) {
		if(personalizedUpdates.at()) {
			if(decision) {
				personalizedUpdates.accept();
			}else {
				personalizedUpdates.dismiss();
			}
		}else {
			System.err.println("No Personalized Updates Pop-up found");
		}
	}
	
	private void selectAnItemFromTheHeaderList(String menuItem) {
		System.out.println("Menu Items Count : "+headerItemsList.size());
		if(!headerItemsList.isEmpty()) {
			Optional<WebElement> menuItemElement = headerItemsList.stream()
														.peek(item -> System.out.println("Item Name : "+item.getText()))
														.filter(item -> item.getText().trim().equals(menuItem))
														.findFirst();
			if(menuItemElement.isPresent()) {
				menuItemElement.get().click();
			}
		}
	}

	public String getSelectedCityFromAppHeader() {
		return selectedCityText.getText();
	}
	
	private int getTotalDisplayedSection() {
		return sectionNames.size();
	}
	
	private boolean isSectionDisplayed(String sectionName) {
		if (getTotalDisplayedSection() >= 1) {
			return sectionNames.stream()
								.anyMatch(element -> sectionName.equals(element.getText().trim()));
		}
		return false;
	}
}
