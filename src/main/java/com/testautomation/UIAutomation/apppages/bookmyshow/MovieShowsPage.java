package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MovieShowsPage extends BasePage {
	
	@FindBy(how = How.CSS, using = "div[class='style__WidgetContainerBody-ldyxjk-0 kQDiFg'] a[href='#goBack']")
	private WebElement nowShowingLink;
	
	@FindBy(how = How.CSS, using = "div[class='style__WidgetContainerBody-ldyxjk-0 kQDiFg'] a[href*='upcoming-movies']")
	private WebElement comingSoonLink;

	@FindBy(how = How.CSS, using = "div[class='style__WidgetContainerBody-ldyxjk-0 kQDiFg'] a[href*='exclusives']")
	private WebElement exclusiveLink;
	
	@FindBy(how = How.CSS, using = "h1[class^='styles__DesktopTitle-sc-']")
	private WebElement upcomingMoviesText;
	
	@FindBy(how = How.CSS, using = "div[class='style__WidgetContainerBody-ldyxjk-0 kdiZX']>a")
	private List<WebElement> movieTabList;
	
	@FindBy(how = How.XPATH, using = "//div[starts-with(@class,'commonStyles__MobileOnlyContainer-sc-1k17atf-0')]/preceding-sibling::div[@class='style__StyledText-tgsgks-0 bXGgSA']")
	private List<WebElement> selectedFiltersList;
	
	@Value("Movie Tickets Online Booking & Showtimes")
	private String pageTitle;
	
	@Value("Upcoming Movies")
	private String comingSoonSection_Title;
	
	@Autowired
	private CalendarComponent calendar;
	
	@Autowired
	private MovieFiltersComponent languageGenre;
	
	@Autowired
	private MovieDetailsPage movieDetailsPage;
	
	@Override
	public boolean at() {
		String menuItemName = "movies";
		String currentUrl = driver.getCurrentUrl();
		return currentUrl.contains(menuItemName) && wait.until(ExpectedConditions.titleContains(pageTitle)); 
	}
	
	public int getTotalMoviesCount() {
		return movieTabList.size();
	}
	
	public boolean atUpcomingMoviesSection() {
		return wait.until(driver ->  upcomingMoviesText.getText().contains(comingSoonSection_Title));
	}
	
	public void switchToComingSoonMoviesTab() {
		comingSoonLink.click();
	}
	
	public void selectReleaseMonthAndYear(String year, String...months) {
		calendar.selectYears(year);
		calendar.selectMonths(months);
	}
	
	public boolean areAllMonthsSelected(String...months) {
		return calendar.areMonthsSelected(months);
	}
	
	public void selectMovieLanguages(String...languages) {
		languageGenre.selectLanguages(languages);
	}
	
	public void selectMovieGenres(String...genres) {
		languageGenre.expandFilterSection("Genre");
		languageGenre.selectGenres(genres);
	}
	
	public boolean areAllFiltersSelected(String...filters) {
		return getAllAppliedFilterLabels().containsAll(Arrays.asList(filters));
	}
	
	public int getAppliedFiltersCount() {
		return selectedFiltersList.size();
	}
	
	private List<String> getAllAppliedFilterLabels() {
		if(getAppliedFiltersCount() >= 1) {
			return selectedFiltersList.stream()
						.map(element -> element.getText().trim())
						.collect(Collectors.toList());
		}else {
			throw new RuntimeException("No Applied Filters found");
		}
	}

	public MovieDetailsPage selectMovie(String movieName) {
		WebElement movie = getMovieFromUpcomingSection(movieName);
		movie.click();
		return movieDetailsPage;
	}
	
	private WebElement getMovieFromUpcomingSection(String movieName) {
		By locator = By.cssSelector("div[class='style__StyledText-tgsgks-0 gbzvvd']");
		Optional<WebElement> movieElement = movieTabList.stream()
														.map(element -> element.findElement(locator))
														.filter(element -> element.getText().trim().equals(movieName))
														.findFirst();
		if(movieElement.isPresent()) {
			return movieElement.get();
		}else 
			throw new NoSuchElementException("Movie not found : "+movieName);
	}

}
