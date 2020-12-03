package com.testautomation.UIAutomation.apppages.webdriveruniversity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class ToDoListPage extends BasePage {

	@FindBy(how = How.CSS, using = "#container>h1")
	private WebElement toDoList_Label;
	
	@FindBy(how = How.CSS, using = "#container>ul>li")
	private List<WebElement> addedItems_List;
	
	@FindBy(how = How.CSS, using = "input[placeholder='Add new todo']")
	private WebElement addItem_Textbox;

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.titleIs(WebDriverUniversityPageTitles.TODOLIST_PAGE.getPageTitle()));
	}
	
	public int itemsCount() {
		return addedItems_List.size();
	}
	
	public List<String> getAllAddedItems() {
		System.out.println("Total added items : "+addedItems_List.size());
		List<String> list = new ArrayList<>();
		addedItems_List
					.stream()
					.forEach(element -> list.add(element.getText()));
		return list;
	}
	
	public void addAnItem(String itemName) {
		Actions actions = new Actions(driver);
		actions.moveToElement(addItem_Textbox).click().build().perform();
		actions.sendKeys(addItem_Textbox,itemName).build().perform();
		actions.sendKeys(addItem_Textbox,Keys.ENTER).build().perform();
	}
	
	public boolean isItemPresentInTheList(String itemName) {
		for(WebElement item : addedItems_List) {
			String text = item.getText();
			if(text.trim().equals(itemName)) {
				return true;
			}
		}
		return false;
	}

	public void hoverOnToDoItem(String toDo) {
		Optional<WebElement> toDoElement = addedItems_List.stream()
															.filter(item -> item.getText().trim().equals(toDo))
															.findFirst();
		if(toDoElement.isPresent()) {
			WebElement element = toDoElement.get();
			new Actions(driver).moveToElement(element).perform();
		}
	}
	
	public void removeTheToDoItem(String toDo) {
		Optional<WebElement> deleteIconElement = addedItems_List.stream()
																.filter(item -> item.getText().trim().equals(toDo))
																.map(matchedItem -> matchedItem.findElement(By.cssSelector("span>i")))
																.findFirst();
		if(deleteIconElement.isPresent()) {
			deleteIconElement.get().click();
		}
	}

}
