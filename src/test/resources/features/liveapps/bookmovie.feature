Feature: Test all the major functionalities of Book My Show App

@BMS-Smoke
  Scenario: As a user, I want to select the city as per my choice
    Given User is on home page
    And City Selection popup is displayed
    When User selects the city as "Pune"
    Then "Pune" city should get appeared on the app header
    
@BMS-Smoke @BMS-UAT
  Scenario: As a user, I want to see all the upcoming movies
    Given User is on movies page
    When User selects the Coming Soon tab
    Then More than 1 upcoming movie should be seen listed
    
 #Passing data through EXAMPLES which is applicable to whole scenario
@BMS-UAT
  Scenario Outline: 
    As a user, I want to filter out movies based on different conditions
    Given User is on movies page
    When User selects the Coming Soon tab
    And Selects language "<language>" filter condition
    And Selects genre "<genre>" filter condition
    Then All the movies "<count>" should be listed based on the conditions
    Examples: 
      | language | genre            | count |
      | Hindi    | Romantic,Crime   |    19 |
      | English  | Adventure,Sci-Fi |    24 |

 #Passing data through DATA TABLE which is applicable to only one preceded step
@BMS-UAT
  Scenario: As a user, I want to get all the movie details
    Given User is on movies page
    When User selects the Coming Soon tab
    And User selects the movie "Chhalaang"
    Then User should get navigated to "Chhalaang" movie details page
    And All the details of the movie should be mentioned
      | HINDI        |
      | COMEDY DRAMA |
      | 13 Nov 2020  |
      | 3278 				 |

@BMS-Smoke @BMS-UAT
  Scenario: As a user, I want to see all the on-going trending movies
    When User moves to "Trending Searches Right Now" Section to search
    Then More than 1 trending movie should be listed

 #Need to update the workshop name
@BMS-UAT
  Scenario: As a user, I want to search an on going workshop
    When User moves to "Workshops For All" Section to search
    Then "Certified Digital Marketing Course" workshop should be listed under all workshops

@BMS-UAT
  Scenario Outline: 
    As a user, I want to see all the offers running on different Debit Cards
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
      
 @BMS-UAT   
    Scenario: As a user, I want to search an event
    When User searches for an event "Art & Drawing - Junior Kids"
    Then The matching event "Art & Drawing - Junior Kids" should get listed in the suggestion box
    And User should get navigated to the specific events page "Art & Drawing - Junior Kids" after clicking on the event
