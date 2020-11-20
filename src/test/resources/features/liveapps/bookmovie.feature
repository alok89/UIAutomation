@bookmyshow
Feature: BookMyShowApp features
	User Story : As a user, I want to test all the major functionalities of BookMyShow App
	

  Scenario: Select the city as per the user's choice
    Given User is on home page
    And City Selection popup is displayed
    When User selects the city as "Pune"
    Then "Pune" city should get appeared on the app header
    
  Scenario: List all the upcoming movies
    Given User is on movies page
    When User selects the Coming Soon tab
    Then More than 1 upcoming movie should be seen listed
    
 #Passing data through EXAMPLES which is applicable to whole scenario
  Scenario Outline: Filter out movies based on different conditions
    Given User is on movies page
    When User selects the Coming Soon tab
    And Selects language "<language>" filter condition
    And Selects genre "<genre>" filter condition
    Then All the movies "<count>" should be listed based on the conditions
    
    Examples: 
      | language | genre            | count |
      | Hindi    | Romantic,Crime   |    5  |
      | English  | Adventure,Sci-Fi |    4  |

 #Passing data through DATA TABLE which is applicable to only one preceded step
  Scenario: List all the movie details
    Given User is on movies page
    When User selects the Coming Soon tab
    And User selects the movie "The Big Bull"
    Then User should get navigated to "The Big Bull" movie details page
    And All the details of the movie should be mentioned
      |Hindi       |
      |Crime Drama |
      |Dec 2020		 |
      |3.9K    		 |

  Scenario: List all the on-going trending movies
    When User moves to "Trending Searches Right Now" Section to search
    Then More than 1 trending movie should be listed

 #Need to update the workshop name as per the latest workshop
  Scenario: Search for an on going workshop
    When User moves to "Workshops For All" Section to search
    Then "Using Ayurveda for a Healthy Lifestyle" workshop should be listed under all workshops

  Scenario Outline: List all the offers running on different Debit Cards
    Given User is on offers page
    When User selects "Debit Card" as payment option
    Then "<offers>" offers on the "<bank>" payment option should get displayed
    
    Examples: 
      | bank     | offers |
      | ICICI    |      1 |
      | AXIS     |      7 |
      | CITI     |      2 |
      | INDUSIND |      2 |
      | YES			 | 			1 |
      | IDFC		 | 			1 |
       
  Scenario: Search for an event
    When User searches for an event "Art & Drawing - Junior Kids - Hobbystation"
    Then The matching event "Art & Drawing - Junior Kids - Hobbystation" should get listed in the suggestion box
    And User should get navigated to the specific events page "Art & Drawing - Junior Kids - Hobbystation" after clicking on the event
