@tag
Feature: Purchase items from Ecomm website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
  
    Given Logged in with username <username> and password <password>
    When I add product <productName> to the cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmationpage

    Examples: 
      | username                | password        | productName      |
      | testuser123@mail.com 		| TestUser@123 		| ZARA COAT 3 		 |
