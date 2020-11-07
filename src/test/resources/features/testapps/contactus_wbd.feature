@webdriveruniversity1 
Feature: WebDriverUniversity App ContactUs Form 

Background: 
	Given I am on the WebDriverUniversity App Home Page 
	And I navigate to ContactUs Page 
	
Scenario: User should be able to successfully submit the ContactUs Form 
	When I enter all the details i.e. FirstName, LastName, EmailId, Comments 
	And Click on Submit button 
	Then A successful message "Thank You for your Message!" should get displayed 
	
Scenario: User should not submit the form successfully by missing email address field 
	When I enter the details i.e. FirstName, LastName, Comments by leaving the email address blank 
	And Click on Submit button 
	Then An error message "Error: all fields are required" should get displayed 
	
Scenario: After entering the details, I should be able to reset all the fields 
	When I enter all the details i.e. FirstName, LastName, EmailId, Comments 
	And Click on Reset button 
	Then All the entered values should get removed from the fields