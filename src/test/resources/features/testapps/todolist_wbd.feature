@webdriveruniversity2
Feature: Test WebDriverUniversity App ToDoList
Description: This feature file consists of scenarios to test TODOList Feature of WebDriverUniversity App.

  Background: 
    Given I am on the WebDriverUniversity App Home Page
    When I navigate to ToDoList Page

  Scenario: I can fetch all the todos from the list
    Then I can retreive all the todos which are present in the list

  Scenario: I can add a new todo in the list
    When I enter the todo "My First To-Do" in the Add new todo textbox
    Then The todo "My First To-Do" should get added in the list
    And The count of the list should get "increased" by one

  Scenario: I can remove a todo from the list
    When I hover on the todo "Buy new robes"
    And Click on the delete icon of the todo "Buy new robes"
    Then The todo "Buy new robes" should get removed from the list
    And The count of the list should get "decreased" by one