Feature: My Store App
User Story: As a user, I want to test the different shopping functionalities available in the app.

Scenario: Buy a product
Given user is on MyStore home page
When user navigates to dresses section "sectionname"
And selects a product "productname"
Then product should get added to cart
