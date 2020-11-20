package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MovieDetailsPage extends BasePage {

	@Value(" Movie (2020) | Reviews, Cast & Release Date in Bengaluru -  BookMyShow")
	private String pageTitle;
	
	@FindBy(how = How.CSS, using = "div[class='styles__LinkedElementsContainer-cri7zi-3 bDobwC']:nth-child(2)>a")
	private List<WebElement> availableLanguages_List;
	
	@FindBy(how = How.CSS, using = "div[class='styles__EventAttributesContainer-cri7zi-1 chtNjF']>a")
	private List<WebElement> genres_List;
	
	@FindBy(how = How.CSS, using = "div[class='styles__EventAttributesContainer-cri7zi-1 chtNjF']:first-child")
	private WebElement releaseDate;
	
	@FindBy(how = How.CSS, using = "div[class='styles__InterestWrapper-tayyzf-1 ctJdbJ'] span[class='styles__InterestedCount-sc-13dnaf1-2 dQPqvM']")
	private WebElement totalVotes_Count;
	
	@FindBy(how = How.CSS, using = "h1[class^='styles__EventHeading-sc-16qvuq-6']")
	private WebElement movieName;
	
	@FindBy(how = How.CSS, using = "div[class*='styles__BannerContainer-sc-16qvuq-2']")
	private WebElement movieBanner;

	@Override
	public boolean at() {
		return movieBanner.isEnabled();
	}
	
	public List<String> getLanguages() {
		return getItemNames(availableLanguages_List);
	}
	
	public List<String> getGenres() {
		return getItemNames(genres_List);
	}
	
	private List<String> getItemNames(List<WebElement> list) {
		return list.stream()
						.map(element -> element.getText().trim())
						.collect(Collectors.toList());
	}
	
	public String getMovieName() {
		return getItem(movieName);
	}
	
	public String getReleaseDate() {
		return getItem(releaseDate).replace(",", "");
	}
	
	public String getTotalNumberOfVotes() {
		return getItem(totalVotes_Count);
	}
	
	private String getItem(WebElement element) {
		return element.getText().trim();
	}

}
