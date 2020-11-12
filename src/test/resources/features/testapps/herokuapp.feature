@heroku
Feature: HerokuApp features
	User Story : As a user, I want to test different fucntionalities of Heroku app. 


# Background section does not have title or description and it should be only one per feature file
Background: 
	Given user is on HerokuApp Landing Page 
	
Scenario: All broken links on the app 
	When user hit the links 
	Then it should show all the broken links by returning the error code 

# Passing a string as an input parameter to the steps
Scenario: Download a file present on the Secure File Download page 
	When user navigates to Secure File Download Page 
	And download the "luminoslogo.png" link 
	Then the "luminoslogo.png" file should get downloaded 
	
Scenario: Fetch all the emailids from the datatable 
	When user navigates to Sortable Data Tables Page 
	Then user should be able to fetch all the emailids from the datatable
	
Scenario: Mouse right click action
	Given user is on context menu page
	When user right clicks in the box
	Then an alert "You selected a context menu" gets triggered
	And context menu should get displayed

# Examples table can have a title and it belongs to immediately preceding scenario outline section
Scenario Outline: Dynamic loading of elements
	Given user is on dynamic loading page
	When user clicks on "<example link>"
	And start the loading process
	Then the element "<element text>" should get rendered

	Examples:
		|example link															 |element text|
		|Example 1: Element on page that is hidden |Hello World!|
		|Example 2: Element rendered after the fact|Hello World!|

# Passing a doc string as an input parameter to the step
Scenario: Appearence of window after moving out of the viewport pane
	Given user is on exit intent page
	When user moves the mouse out of viewport
	Then a modal window gets appeared with the text
		"""
		It's commonly used to encourage a user to take an action (e.g., give their e-mail address to sign up for something).
		"""

Scenario Outline: Horizontal Slider
	Given user is on horizontal slider page
	When user clicks on the slider
	And moves the slider using "<action>"
	Then the "<value>" should get updated accordingly
	
	Examples:
	  |action    |value| 
		|Arrow Keys|4.5	 |
		|Drag			 |3		 |
		
# Passing a step table as an input parameter to the step
Scenario: MouseHover on JQuery menus
	Given user is on jqueryui-menu page
	When user mouse hovers on enabled option
	And user mouse hovers on downloads option 
	Then options inside downloads should get displayed
		|options|
		|PDF		|
		|CSV		|
		|Excel	|

# Examples table provides parameterization and each example becomes a parameter for a single test scenario
Scenario Outline: Different keypresses inside the textbox
	Given user is on key_presses page
	When user enters the "<key>"
	Then "<key>" should get displayed
	
	Examples:
		|key			 |
		|TAB			 |
		|ALT			 |
		|ESCAPE		 |
		|SHIFT		 |
		|ENTER		 |
		|F2				 |
		|PERIOD    |
		|CAPS_LOCK |
		|BACK_SPACE|
		|K				 |
		|8				 |
		|NUMPAD3	 |
		|QUOTE		 |

