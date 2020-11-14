package com.testautomation.UIAutomation.apppages.herokuapp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.LazyAutowired;
import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;
import com.testautomation.UIAutomation.webelements.Link;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Page
public class SecureFileDownloadPage extends BasePage {

	@LazyAutowired
	private Link link;
	
	@Value("Secure File Downloader")
	private String text;

	@Override
	public boolean at() {
		By locator = By.tagName("h3");
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	
	public void downLoadLink(String linkName) {
		link.clickOnAParticularLink(linkName);
		Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
	}

}
