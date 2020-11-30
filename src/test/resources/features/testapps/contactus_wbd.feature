@webdriveruniversity 
Feature: WebDriverUniversity App
	User Story: As a user, I want to test contact us and to-do list features of webdriveruniversity app.
	

Background: 
	Given I am on the WebDriverUniversity App Home Page 
	
 Scenario: User should be able to successfully submit the ContactUs Form
 		Given I navigate to ContactUs Page
		When I enter all the details i.e. FirstName, LastName, EmailId, Comments 
		And Click on Submit button
		Then A successful message "Thank You for your Message!" should get displayed 
	
 Scenario: User should not submit the form successfully by missing email address field
 		Given I navigate to ContactUs Page
		When I enter the details i.e. FirstName, LastName, Comments by leaving the email address blank 
		And Click on Submit button
		Then An error message "Error: all fields are required" should get displayed 
	
 Scenario: After entering the details, I should be able to reset all the fields 
		Given I navigate to ContactUs Page
		When I enter all the details i.e. FirstName, LastName, EmailId, Comments
		And Click on Reset button
		Then All the entered values should get removed from the fields
	
 Scenario: I can fetch all the todos from the list
 		When I navigate to ToDoList Page
    Then I can retreive all the todos which are present in the list

 Scenario: I can add a new todo in the list
 		When I navigate to ToDoList Page
    When I enter the todo "My First To-Do" in the Add new todo textbox
    Then The todo "My First To-Do" should get added in the list
    And The count of the list should get "increased" by one

 Scenario: I can remove a todo from the list
 		When I navigate to ToDoList Page
    When I hover on the todo "Buy new robes"
    And Click on the delete icon of the todo "Buy new robes"
    Then The todo "Buy new robes" should get removed from the list
    And The count of the list should get "decreased" by one