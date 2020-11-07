package com.testautomation.UIAutomation.stepdefs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.apppages.webdriveruniversity.ToDoListPage;
import com.testautomation.UIAutomation.apppages.webdriveruniversity.WBDUniversityHomePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestToDoList_WebDriverUniversityApp {
	
	static {
		ThreadContext.put("ROUTINGKEY", "TestToDoList_WebDriverUniversityApp");
	}
	
	private static final Logger logger = LogManager.getLogger(ThreadContext.get("ROUTINGKEY"));
	
	@Autowired
	private WBDUniversityHomePage wbdUniversityHomePage;
	
	private ToDoListPage toDoListPage;
	
	private int presentItemsCount;
	
	@When("I navigate to ToDoList Page")
	public void iNavigateToToDoListPage() {
		logger.info("When - I navigate to ToDoList Page");
	    toDoListPage = wbdUniversityHomePage.goToDoListPage();
	    Assert.assertTrue(toDoListPage.at());
	}

	@Then("I can retreive all the todos which are present in the list")
	public void iCanRetreiveAllTheTodosWhichArePresentInTheList() {
		logger.info("Then - I can retreive all the todos which are present in the list");
	    List<String> itemsList = toDoListPage.getAllAddedItems();
	    itemsList.forEach(System.out::println);
	}

	@When("I enter the todo {string} in the Add new todo textbox")
	public void iEnterTheTodoInTheAddNewTodoTextbox(String toDo) {
		logger.info("When - I enter the todo in the Add new todo textbox");
		presentItemsCount = toDoListPage.itemsCount();
		toDoListPage.addAnItem(toDo);
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
	}

	@Then("The todo {string} should get added in the list")
	public void theTodoShouldGetAddedInTheList(String addedToDo) {
		logger.info("Then - The todo should get added in the list");
	    Assert.assertTrue(toDoListPage.isItemPresentInTheList(addedToDo));
	}

	@Then("The count of the list should get {string} by one")
	public void theCountOfTheListShouldGetByOne(String increasedOrDecreased) {
		logger.info("Then - The count of the list should get {string} by one");
	    if(increasedOrDecreased.equals("increased")) {
	    	Assert.assertEquals(toDoListPage.itemsCount(), presentItemsCount+1);
	    }else if(increasedOrDecreased.equals("decreased")) {
	    	Assert.assertEquals(toDoListPage.itemsCount(), presentItemsCount-1);
	    }
	}

	@When("I hover on the todo {string}")
	public void iHoverOnTheTodo(String toDo) {
		logger.info("When - I hover on the todo");
	    toDoListPage.hoverOnToDoItem(toDo);
	}

	@When("Click on the delete icon of the todo {string}")
	public void clickOnTheDeleteIcon(String toDo) {
		logger.info("When - Click on the delete icon of the todo");
		presentItemsCount = toDoListPage.itemsCount();
	    toDoListPage.removeTheToDoItem(toDo);
	    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
	}

	@Then("The todo {string} should get removed from the list")
	public void theTodoShouldGetRemovedFromTheList(String toDo) {
		logger.info("Then - The todo should get removed from the list");
	    Assert.assertFalse(toDoListPage.isItemPresentInTheList(toDo));
	}

}
