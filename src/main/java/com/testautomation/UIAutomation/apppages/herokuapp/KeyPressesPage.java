package com.testautomation.UIAutomation.apppages.herokuapp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class KeyPressesPage extends BasePage {
	
	private static final String PAGE_HEADER = "Key Presses";
	
	@FindBy(how = How.TAG_NAME, using = "h3")
	private WebElement pageHeader_Text;
	
	@FindBy(how = How.ID, using = "target")
	private WebElement textbox;
	
	@FindBy(how = How.ID, using = "result")
	private WebElement result_Text;

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.textToBePresentInElement(pageHeader_Text, PAGE_HEADER));
	}
	
	public void pressKey(String key) {
		switch(key) {
		case "TAB" :
			textbox.sendKeys(Keys.TAB);
			break;
		case "ALT" :
			textbox.sendKeys(Keys.ALT);
			break;
		case "ESCAPE" :
			textbox.sendKeys(Keys.ESCAPE);
			break;
		case "SHIFT" :
			textbox.sendKeys(Keys.SHIFT);
			break;
		case "ENTER" :
			textbox.sendKeys(Keys.ENTER);
			break;
		case "F2" :
			textbox.sendKeys(Keys.F2);
			break;
		case "EQUALS" :
			textbox.sendKeys(Keys.EQUALS);
			break;
		case "ARROW_RIGHT" :
			textbox.sendKeys(Keys.ARROW_RIGHT);
			break;
		case "BACK_SPACE" :
			textbox.sendKeys(Keys.BACK_SPACE);
			break;
		case "HOME" :
			textbox.sendKeys(Keys.HOME);
			break;
		case "PAGE_UP" :
			textbox.sendKeys(Keys.PAGE_UP);
			break;
		case "NUMPAD3" :
			textbox.sendKeys(Keys.NUMPAD3);
			break;
		default:
				System.err.println("pressed key is not present");
		}
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
	}

	public String getPressedKey() {
		String resultText = result_Text.getText();
		int index = resultText.indexOf(":");
		return resultText.substring(index+2, resultText.length());
	}
}
