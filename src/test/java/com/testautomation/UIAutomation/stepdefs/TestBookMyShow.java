package com.testautomation.UIAutomation.stepdefs;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import com.testautomation.UIAutomation.apppages.bookmyshow.BMSLandingPage;
import com.testautomation.UIAutomation.apppages.bookmyshow.MovieDetailsPage;
import com.testautomation.UIAutomation.apppages.bookmyshow.MovieShowsPage;
import com.testautomation.UIAutomation.apppages.bookmyshow.OffersPage;
import com.testautomation.UIAutomation.apppages.bookmyshow.SearchComponent;
import com.testautomation.UIAutomation.apppages.bookmyshow.SpecificEventsPage;
import com.testautomation.UIAutomation.apppages.bookmyshow.TrendingSearchesSectionComponent;
import com.testautomation.UIAutomation.apppages.bookmyshow.WorkshopsForAllSectionComponent;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestBookMyShow {
	
	@Before(value = "@BMS-UAT", order = 1)
	public void beforeEveryBMSScenario() {
		System.out.println("Before Specific Scenario Hook...");
		bmsLandingPage.goTo(cityUrl);
	}

	/*
	 * static { ThreadContext.put("ROUTINGKEY", "TestBookMyShow"); }
	 * 
	 * //private static final Logger logger =
	 * LogManager.getLogger(ThreadContext.get("ROUTINGKEY"));
	 */
	
	@Autowired
	private BMSLandingPage bmsLandingPage;

	@Value("${application.bookmyshow_url}") 
	private String url; 
	
	@Value("${application.bookmyshow_url_city}") 
	private String cityUrl; 

	private MovieShowsPage movieShowsPage;

	private MovieDetailsPage movieDetailsPage;
	
	private OffersPage offersPage;
	
	private TrendingSearchesSectionComponent trendingSection;
	
	private WorkshopsForAllSectionComponent workshopsSection;
	
	private SearchComponent searchSection;
	
	private SpecificEventsPage eventsPage;

	@Given("User is on movies page")
	public void userIsOnMoviesPage() {
		movieShowsPage = bmsLandingPage.goToMoviesPage();
		Assert.assertTrue(movieShowsPage.at());
	}

	@When("User selects the Coming Soon tab")
	public void userSelectsTheComingSoonTab() {
		movieShowsPage.switchToComingSoonMoviesTab();
		Assert.assertTrue(movieShowsPage.atUpcomingMoviesSection());
	}

	@Then("More than {int} upcoming movie should be seen listed")
	public void moreThanOneUpcomingMovieShouldBeSeenListed(int movieCount) {
		int totalMoviesCount = movieShowsPage.getTotalMoviesCount();
		Assert.assertTrue(totalMoviesCount >= movieCount);
	}

	@And("Selects language {string} filter condition")
	public void selectsLanguageFilterConditions(String languageFilter) {
		movieShowsPage.selectMovieLanguages(languageFilter);
		Assert.assertTrue(movieShowsPage.areAllFiltersSelected(languageFilter));
	}
	
	@And("Selects genre {string} filter condition")
	public void selectsGenreFilterConditions(String genreFilter) {
		String[] genres = genreFilter.split(",");
		movieShowsPage.selectMovieGenres(genres);
		Assert.assertTrue(movieShowsPage.areAllFiltersSelected(genres));
	}

	@Then("All the movies {string} should be listed based on the conditions")
	public void allTheMoviesShouldBeListedBasedOnTheConditions(String count) {
		int actualMoviesCount = movieShowsPage.getTotalMoviesCount();
		int expectedCount = Integer.parseInt(count);
		System.out.println("Actual Movies Count : "+actualMoviesCount);
		Assert.assertTrue(actualMoviesCount >= expectedCount);
	}

	@And("User selects the movie {string}")
	public void userSelectsTheMovie(String movieName) {
		movieDetailsPage = movieShowsPage.selectMovie(movieName);
		Assert.assertTrue(movieDetailsPage.at());
	}

	@Then("User should get navigated to {string} movie details page")
	public void userShouldGetNavigatedToMovieDetailsPage(String movieName) {
		Assert.assertEquals(movieDetailsPage.getMovieName(), movieName);
	}

	@And("All the details of the movie should be mentioned")
	public void allTheDetailsOfTheMovieShouldBeMentioned(DataTable testData) {
		List<String> data = testData.asList();
		int i = 0;
		Assert.assertEquals(movieDetailsPage.getLanguages().get(0), data.get(i++));
		Assert.assertEquals(movieDetailsPage.getGenres(), Arrays.asList(data.get(i++).split(" ")));
		Assert.assertEquals(movieDetailsPage.getReleaseDate(), data.get(i++));
	}
	
	@Given("User is on home page")
	public void userIsOnHomePage() {
		bmsLandingPage.goTo(url);
	    Assert.assertTrue(bmsLandingPage.at());
	}

	@Given("User is on offers page")
	public void userIsOnOffersPage() {
		offersPage = bmsLandingPage.goToOffersPage();
		Assert.assertTrue(offersPage.at());
	}

	@When("User selects {string} as payment option")
	public void userSelectsAsPaymentOption(String paymentOption) {
	    offersPage.selectPaymentOption(paymentOption);
	    Assert.assertTrue(offersPage.isPaymentOptionSelected(paymentOption));
	}

	@Then("{string} offers on the {string} payment option should get displayed")
	public void offersOnThePaymentOptionShouldGetDisplayed(String offerCount, String bankCardOffer) {
	    List<String> cardOffers = offersPage.getAllCardOffers();
	    System.out.println("Total Card Offers : "+cardOffers.size());
	    int count = 0;
	    for(String cardOffer : cardOffers) {
	    	if(cardOffer.startsWith(bankCardOffer)) {
	    		System.out.println("Card Offer Title : "+cardOffer);
	    		count++;
	    	}
	    }
	    Assert.assertEquals(count, Integer.parseInt(offerCount));
	}

	@When("User moves to {string} Section to search")
	public void userMovesToSectionToSearch(String sectionName) {
		boolean atSection = false;
		if(sectionName.equals("Trending Searches Right Now")) {
			trendingSection = bmsLandingPage.moveToTrendingSearchSection();
			atSection = trendingSection.atTrendingSearchSection();
		}else if(sectionName.equals("Workshops For All")) {
			workshopsSection = bmsLandingPage.moveToWorkshopsSection();
			atSection = workshopsSection.atWorkshopsSection();
		}
		Assert.assertTrue(atSection);
	}

	@Then("{string} workshop should be listed under all workshops")
	public void workshopShouldBeListedUnderAllWorkshops(String workshopName) {
		workshopsSection
					.getAllWorkshopsTitle()
					.forEach(System.out::println);
	    Assert.assertTrue(workshopsSection.isWorkshopGoingOn(workshopName));   
	}

	@Given("City Selection popup is displayed")
	public void citySelectionPopupIsDisplayed() {
	    Assert.assertTrue(bmsLandingPage.hasCitySelectionPopUpAppeared());
	}

	@When("User selects the city as {string}")
	public void userSelectsTheCityAs(String city) {
		bmsLandingPage.selectTheCity(city);
	    Assert.assertTrue(bmsLandingPage.isCitySelected(city));
	}

	@Then("{string} city should get appeared on the app header")
	public void cityShouldGetAppearedOnTheAppHeader(String cityName) {
	    String city = bmsLandingPage.getSelectedCityFromAppHeader();
	    Assert.assertEquals(city, cityName);
	}

	@Then("More than {int} trending movie should be listed")
	public void moreThanOneTrendingMovieShouldBeListed(int count) {
		trendingSection = bmsLandingPage.moveToTrendingSearchSection();
		List<String> moviesList = trendingSection.getAllTrendingMoviesRightNow();
	    moviesList.forEach(System.out::println);
	    Assert.assertTrue(moviesList.size() >= count);
	}

	@When("User searches for an event {string}")
	public void userSearchesForAnEvent(String text) {
	   searchSection = bmsLandingPage.goToSearch();
	   Assert.assertTrue(searchSection.searchValue(text));
	}

	@Then("The matching event {string} should get listed in the suggestion box")
	public void theMatchingEventShouldGetListedInTheSuggestionBox(String event) {
	    List<String> suggestions = searchSection.getAllSuggestions();
	    Assert.assertTrue(suggestions.contains(event));
	}

	@Then("User should get navigated to the specific events page {string} after clicking on the event")
	public void userShouldGetNavigatedToTheSpecificEventsPageAfterClickingOnTheEvent(String event) {
	    eventsPage = searchSection.selectValueFromSuggestions(event);
	    String title = "";
	    if(eventsPage.at()) {
	    	title = eventsPage.getEventTitle();
	    }
	    Assert.assertEquals(title, event);
	}


}
