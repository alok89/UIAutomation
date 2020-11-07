@heroku 
Feature: Test different functionalities of HerokuApp 

Background: 
	Given I am on the HerokuApp Landing Page 
	
Scenario: Check for all broken links 
	When I hit the links 
	Then It should show all the broken links by returning the error code 
	
Scenario: Download any file present on the Secure File Download page using the links 
	When I navigate to File Download Page by clicking on Secure File Download Link 
	And Click on the "webdriverIO.png" link 
	Then The "webdriverIO.png" file should get downloaded 
	
Scenario: Fetch all the emailids from the datatable 
	When I navigate to Data Tables Page by clicking on Sortable Data Tables link 
	Then User should be able to fetch all the emailids from the datatable