
package com.testautomation.UIAutomation.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.testautomation.UIAutomation.annotations.BeanWithPrototypeScope;
import com.testautomation.UIAutomation.annotations.LazyConfiguration;

@LazyConfiguration
public class ActionsConfig {

	@BeanWithPrototypeScope
	public Actions actions(WebDriver driver) {
		return new Actions(driver);
	}

}
