package com.testautomation.UIAutomation.apppages.herokuapp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class SecureFileDownloadPage extends BasePage {

	@Value("Secure File Downloader")
	private String text;

	@Override
	public boolean at() {
		By locator = By.tagName("h3");
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	
	public void clickLinkToDownLoad(String linkName) {
		clickOnAParticularLink(linkName);
		Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
	}

}
