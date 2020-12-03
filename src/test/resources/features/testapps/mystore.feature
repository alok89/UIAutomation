@mystore
Feature: My Store App
	User Story: As a user, I want to test the different shopping functionalities available in the app.


 Scenario Outline: Buy a summer dress
	Given user is on home page
	When user navigates to section "<sectionname>"
	And selects a product by "<productname>" and "<price>"
	Then product should get added to cart
	
	Examples:
		|sectionname|productname					|price |
		|Dresses		|Printed Chiffon Dress|$16.40|
		#|Women			|Printed Summer Dress |$30.50|

	