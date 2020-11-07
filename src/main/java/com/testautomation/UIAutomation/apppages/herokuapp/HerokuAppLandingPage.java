package com.testautomation.UIAutomation.apppages.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class HerokuAppLandingPage extends BasePage {
	
	@Value("Welcome to the-internet")
	private String text;
	
	@Autowired
	private SecureFileDownloadPage fileDownloadPage;
	
	@Autowired
	private DataTablesPage dataTablesPage;

	@FindBy(how = How.TAG_NAME, using = "h1")
	private WebElement pageHeading_Text;
	
	@FindBy(how = How.LINK_TEXT, using = "Sortable Data Tables")
	private WebElement dataTables_Link;

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.
									textToBePresentInElement(pageHeading_Text, text));
	}

	public DataTablesPage goToDataTablesPage() {
		dataTables_Link.click();
		return dataTablesPage;
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
