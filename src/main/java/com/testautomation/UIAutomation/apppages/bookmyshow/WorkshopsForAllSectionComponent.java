package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.List;
import java.util.stream.Collectors;

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
public class WorkshopsForAllSectionComponent {
	
	@Autowired
	private ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	@FindBy(how = How.CSS, using = "div[class='commonStyles__FullWidgetWrapper-sc-1k17atf-4 hOGemf']:nth-child(3)")
	private WebElement workshopsSection;
	
	@FindBy(how = How.CSS, using = "div[class='commonStyles__FullWidgetWrapper-sc-1k17atf-4 hOGemf']:nth-child(3) div[class='style__WidgetContainerBody-sc-10gjjdh-2 guafqu']>a")
	private List<WebElement> workshopsList;
	
	private final By workshopTabLocator = By.cssSelector("div[class='style__WidgetContainerBody-sc-10gjjdh-2 guafqu']>a");
	
	public boolean atWorkshopsSection() {
		return getTotalNumberOfWorkshops() >= 1;
	}
	
	public int getTotalNumberOfWorkshops() {
		List<WebElement> workshops = getAllWorkshops();
		System.out.println("Total Workshops found : "+workshops.size());
		return workshops.size();
	}
	
	private List<WebElement> getAllWorkshops() {
		return workshopsSection.findElements(workshopTabLocator);
	}
	
	public boolean isWorkshopGoingOn(String workshopTitle) {
		return getAllWorkshopsTitle().stream()
							.anyMatch(title -> workshopTitle.equals(title));
	}
	
	public List<String> getAllWorkshopsTitle() {
		if(getTotalNumberOfWorkshops() >= 1) {
			final By locator = By.cssSelector("div[class='style__StyledText-tgsgks-0 cTkfzX']");
			return getAllWorkshops().stream()
							.map(element -> element.findElement(locator))
							.map(element -> element.getText().trim())
							.collect(Collectors.toList());
		}else {
			throw new RuntimeException("No on-going Workshops found");
		}
	}
}
