package com.testautomation.UIAutomation.apppages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

public abstract class BasePage {

	@Autowired
	protected WebDriver driver;
	
	@Autowired
	protected WebDriverWait wait;
	
	@Autowired
	protected Actions actionBuilder;
	
	@Autowired
	protected ClickableElementLocatorFactoryService elementLocatorFactory;
	
	//private Logger logger = LogManager.getLogger();
	
	@PostConstruct
	protected void initElements() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	public abstract boolean at();
	
	public void goTo(String url) {
		//logger.info("Maximizing the browser window and hitting the url");
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	protected boolean hasTitleMatched(String pageTitle) {
		return wait.until(ExpectedConditions.titleIs(pageTitle));
	}
	
	protected boolean isElementContainsText(WebElement element, String text) {
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	private boolean isURLWorking(String url) {
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
			int responseCode = urlConnection.getResponseCode();
			if(responseCode >= 200 && responseCode < 300) {
				return true;
			} else if(responseCode >= 400 && responseCode < 500) {
				System.out.println("Client Error : "+responseCode);
				return false;
			}else if(responseCode >= 500 && responseCode < 600) {
				System.out.println("Server Error : "+responseCode);
				return false;
			}
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public List<String> getAllWorkingLinks(List<WebElement> links) {
		List<String> urls = links.stream()
									.map(link -> link.getAttribute("href"))
									.filter(url -> url != null && !url.isEmpty())
									.filter(url -> isURLWorking(url))
									.collect(Collectors.toList());
		return urls;
	}
	
	public List<WebElement> getAllLinksFromThePage() {
		By locator = By.tagName("a");
		List<WebElement> links = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return links;
	}
	
	protected void clickOnAParticularLink(String linkName) {
		List<WebElement> links = getAllLinksFromThePage();
		Optional<WebElement> linkElement = links.stream()
													.filter(element -> element.getText().equals(linkName))
													.findFirst();
		if(linkElement.isPresent()) {
			WebElement element = linkElement.get();
			element.click();
			Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
		}

	}
}
