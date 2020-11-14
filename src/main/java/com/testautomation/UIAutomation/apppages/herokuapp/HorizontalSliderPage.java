package com.testautomation.UIAutomation.apppages.herokuapp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Page
public class HorizontalSliderPage extends BasePage {
	
	private static final String PAGE_HEADER = "Horizontal Slider";
	private static final String PAGE_TEXT = "click and drag the slider with your mouse";

	@FindBy(how = How.CSS, using = ".example>h3")
	private WebElement pageHeader;
	
	@FindBy(how = How.TAG_NAME, using = "h4")
	private WebElement pageText;
	
	@FindBy(how = How.CSS, using = ".sliderContainer>input")
	private WebElement slider;
	
	@FindBy(how = How.ID, using = "range")
	private WebElement sliderValue;
	
	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.and(
									ExpectedConditions.textToBePresentInElement(pageText, PAGE_TEXT),
									ExpectedConditions.textToBePresentInElement(pageHeader, PAGE_HEADER)
									));
	}
	
	public double getSliderValue() {
		return Double.parseDouble(sliderValue.getText());
	}
	
	public void moveSliderTo(double range) {
		slider.click();
		double currentValue = getSliderValue();
		while(currentValue != range) {
			if(currentValue < range) {
				slider.sendKeys(Keys.ARROW_RIGHT);
			}else {
				slider.sendKeys(Keys.ARROW_LEFT);
			}
			Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
			currentValue = getSliderValue();
		}
	}

}
