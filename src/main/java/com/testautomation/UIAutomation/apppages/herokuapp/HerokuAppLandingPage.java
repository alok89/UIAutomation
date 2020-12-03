package com.testautomation.UIAutomation.apppages.herokuapp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;
import com.testautomation.UIAutomation.webelements.Link;

@Page
public class HerokuAppLandingPage extends BasePage {
	
	@Value("Welcome to the-internet")
	private String text;
	
	@LazyAutowired
	private Link link;
	
	@LazyAutowired
	private SecureFileDownloadPage fileDownloadPage;
	
	@LazyAutowired
	private DataTablesPage dataTablesPage;
	
	@LazyAutowired
	private DynamicLoadingPage dynamicLoadingPage;
	
	@LazyAutowired
	private HorizontalSliderPage horizontalSliderPage;
	
	@LazyAutowired
	private KeyPressesPage keyPressesPage;

	@FindBy(how = How.TAG_NAME, using = "h1")
	private WebElement pageHeading_Text;
	
	@FindBy(how = How.LINK_TEXT, using = "Sortable Data Tables")
	private WebElement dataTables_Link;
	
	@FindBy(how = How.LINK_TEXT, using = "Dynamic Loading")
	private WebElement dynamicLoading_Link;
	
	@FindBy(how = How.LINK_TEXT, using = "Horizontal Slider")
	private WebElement horizontalSlider_Link;
	
	@FindBy(how = How.LINK_TEXT, using = "Key Presses")
	private WebElement keyPresses_Link;

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.
									textToBePresentInElement(pageHeading_Text, text));
	}

	public DataTablesPage goToDataTablesPage() {
		new Actions(driver).moveToElement(dataTables_Link).click().perform();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		return dataTablesPage;
	}
	
	public DynamicLoadingPage goToDynamicLoadingPage() {
		dynamicLoading_Link.click();
		return dynamicLoadingPage;
	}
	
	public HorizontalSliderPage goToHorizontalSliderPage() {
		horizontalSlider_Link.click();
		return horizontalSliderPage;
	}
	
	public KeyPressesPage goToKeyPressesPage() {
		keyPresses_Link.click();
		return keyPressesPage;
	}
	
	public List<WebElement> getAllLinks() {
		return link.getAllLinksFromThePage();
	}
	
	public List<String> getAllWorkingLinks() {
		return link.getAllWorkingLinks(getAllLinks());
	}
	
	public SecureFileDownloadPage goToSecureFileDownloadPage() {
		By locator = By.xpath("//a[text()='Secure File Download']");
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		String url = element.getAttribute("href");
		driver.navigate().to(buildNewURL(url));
		return fileDownloadPage;
	}
	
	private String buildNewURL(String url) {
		String firstPart = url.substring(0, 7);
		String lastPart = url.substring(7);
		url = new StringBuilder().append(firstPart).append("admin:admin@").append(lastPart).toString();
		System.out.println("URL : "+url);
		return url;
	}

}
