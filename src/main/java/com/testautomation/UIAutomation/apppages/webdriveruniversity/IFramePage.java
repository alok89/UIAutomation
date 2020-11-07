package com.testautomation.UIAutomation.apppages.webdriveruniversity;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class IFramePage extends BasePage {
	
	@FindBy(how = How.ID, using = "frame")
	private WebElement iframe;

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.titleIs(WebDriverUniversityPageTitles.IFRAME_PAGE.getPageTitle()));
	}

	public List<String> getAllPageTabsLabel() {
		By locator = By.cssSelector(".container-fluid>ul>li>a");
		final List<String> tabsName = getAllLabels(locator);
		return tabsName;
	}

	public List<String> getAllSectionsLabel() {
		By locator = By.cssSelector(".thumbnail>.section-title>p");
		List<String> sectionsName = getAllLabels(locator);
		return sectionsName;
	}

	private List<String> getAllLabels(By locator) {
		driver.switchTo().frame(iframe);
		List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		final List<String> namesList = new ArrayList<>();
		if(!list.isEmpty()) {
			list.stream().forEach(element -> namesList.add(element.getText()));
		}
		driver.switchTo().defaultContent();
		return namesList;
	}


}
