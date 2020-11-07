package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class TrendingSearchesSectionComponent {
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(elementLocatorFactory, this);
	}

	@FindBy(how = How.CSS, using = "div[class='commonStyles__WidgetContainer-sc-1k17atf-6 goitoQ'] div.style__WidgetContainerHeader-v01ajp-0 div.style__StyledText-tgsgks-0")
	private WebElement sectionHeaderText;
	
	@FindBy(how = How.CSS, using = "div[class^='commonStyles__WidgetContainer-sc-1k17atf-6'] div[class='style__WidgetContainerBody-ldyxjk-0 igabqE']>a")
	private List<WebElement> trendingItemsList;
	
	public boolean atTrendingSearchSection() {
		return getTrendingItemsCount() >= 1;
	}
	
	public int getTrendingItemsCount() {
		System.out.println("Total Trending Items : "+trendingItemsList.size());
		return trendingItemsList.size();
	}
	
	public List<String> getAllTrendingMoviesRightNow() {
		final By movieLabelLocator = By.cssSelector("div[class='style__StyledText-tgsgks-0 bsLTli']");
		final By movieTitleLocator = By.cssSelector("div[class='style__StyledText-tgsgks-0 hjwgHw']");
		final String trendingItem = "Movies";
		if (getTrendingItemsCount() >= 1) {
			Stream<WebElement> webElementStream = trendingItemsList.stream();
			return webElementStream
					.filter(element -> element.findElement(movieLabelLocator).getText().trim().equals(trendingItem))
					.map(element -> element.findElement(movieTitleLocator).getText())
					.collect(Collectors.toList());
		} else {
			throw new RuntimeException("No Trending Items found");
		}
	}
}
