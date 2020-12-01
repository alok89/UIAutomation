Feature: My Store App
	User Story: As a user, I want to test the different shopping functionalities available in the app.


 Scenario Outline: Buy a summer dress
	Given user is on home page
	When user navigates to section "<sectionname>"
	And selects a product "<productname>"
	Then product should get added to cart
	
	Examples:
		|sectionname|productname					|
		|Dresses		|Printed Chiffon Dress|
		|Women			|Printed Summer Dress |

	