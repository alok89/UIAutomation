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
	
	@FindBy(how = How.CSS, using = "div.certificate-languages>a[itemprop='inLanguage']")
	private List<WebElement> availableLanguages_List;
	
	@FindBy(how = How.CSS, using = "div.certificate-languages>a[itemprop='genre']")
	private List<WebElement> genres_List;
	
	@FindBy(how = How.CSS, using = "div.date-time span.__release-date")
	private WebElement releaseDate;
	
	@FindBy(how = How.CSS, using = "#user-wts-true span.__votes")
	private WebElement totalVotes_Count;
	
	@FindBy(how = How.CSS, using = "div.name-rating>h1[itemprop='name']")
	private WebElement movieName;
	
	@FindBy(how = How.CLASS_NAME, using = "movie-synopsis-content-wrapper")
	private WebElement movieDetails_Wrapper;

	@Override
	public boolean at() {
		return movieDetails_Wrapper.isEnabled();
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
