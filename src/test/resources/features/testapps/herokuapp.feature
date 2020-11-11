@heroku 
Feature: Test different functionalities of HerokuApp 

Background: 
	Given I am on the HerokuApp Landing Page 
	
Scenario: Check for all broken links 
	When I hit the links 
	Then It should show all the broken links by returning the error code 
	
Scenario: Download any file present on the Secure File Download page using the links 
	When I navigate to Secure File Download Page 
	And Click on the "luminoslogo.png" link 
	Then The "luminoslogo.png" file should get downloaded 
	
Scenario: Fetch all the emailids from the datatable 
	When I navigate to Sortable Data Tables Page 
	Then User should be able to fetch all the emailids from the datatable
	
Scenario: Test right click action inside context menu

Scenario: Test dynamic loading of elements

Scenario: Test Appearence of window based on mouse movement - exit intent

Scenario: Test Horizontal Slider

Scenario: Test MouseHover action using JQuery Menus

Scenario: Test different keyboard keys

Scenario: Test notification messages

