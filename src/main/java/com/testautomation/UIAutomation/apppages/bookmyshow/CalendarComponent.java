package com.testautomation.UIAutomation.apppages.bookmyshow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.PageComponent;
import com.testautomation.UIAutomation.utils.ClickableElementLocatorFactoryService;

@PageComponent
public class CalendarComponent {
	
	@Autowired
	protected ClickableElementLocatorFactoryService elementLocatorFactory;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(elementLocatorFactory, this);
	}
	
	@FindBy(how = How.CSS, using = ".__release-calendar-year>li")
	private List<WebElement> calendarYears_List;
	
	@FindBy(how = How.CSS, using = ".__calendar-week-list>li")
	private List<WebElement> calendarMonths_List;

	public void selectYears(String year) {
		for(WebElement element : calendarYears_List) {
			String elementValue = element.getAttribute("data-year");
			if(year.equals(elementValue.trim())) {
				element.click();
				break;
			}
		}
	}
	
	public void selectMonths(String...months) {
		for(String month : months) {
			int monthNo = CalendarMonths.valueOf(month).getMonthNo();
			for(WebElement element : calendarMonths_List) {
				int elementValue = Integer.parseInt(element.getAttribute("data-month"));
				if(monthNo == elementValue)
					element.click();
			}
		}
	}
	
	public boolean areMonthsSelected(String...months) {
		List<String> selectedValues = calendarMonths_List.stream()
											.filter(element -> element.getAttribute("class").contains("_active"))
											.map(element -> element.getAttribute("data-month"))
											.collect(Collectors.toList());
		List<String> inputValues = new ArrayList<>();
		for(String month : months) {
			int monthNo = CalendarMonths.valueOf(month).getMonthNo();
			inputValues.add(String.valueOf(monthNo));
		}
		return (selectedValues.size() == inputValues.size()) && 
				(selectedValues.containsAll(inputValues));
	}

}
