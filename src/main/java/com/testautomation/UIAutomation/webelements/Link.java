package com.testautomation.UIAutomation.webelements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.LazyComponent;

@LazyComponent
public class Link {

	@Autowired
	private ApplicationContext appContext ;

	private boolean isURLWorking(String url) {
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
			int responseCode = urlConnection.getResponseCode();
			if (responseCode >= 200 && responseCode < 300) {
				return true;
			} else if (responseCode >= 400 && responseCode < 500) {
				return false;
			} else if (responseCode >= 500 && responseCode < 600) {
				return false;
			}
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
		System.out.println("Total working links on the page : " + urls.size());
		return urls;
	}

	public List<WebElement> getAllLinksFromThePage() {
		By locator = By.tagName("a");
		List<WebElement> links = appContext.getBean(WebDriverWait.class)
												.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		System.out.println("Total links on the page : " + links.size());
		return links;
	}

	public void clickOnAParticularLink(String linkName) {
		List<WebElement> links = getAllLinksFromThePage();
		Optional<WebElement> linkElement = links.stream().filter(element -> element.getText().equals(linkName))
				.findFirst();
		if (linkElement.isPresent()) {
			WebElement element = linkElement.get();
			element.click();
			Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
		}
	}

}
