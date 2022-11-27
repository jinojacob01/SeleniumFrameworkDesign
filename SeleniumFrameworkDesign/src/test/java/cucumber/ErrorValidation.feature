@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Verify Error Validation
    Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." error should be displayed

    Examples: 
      | username                | password        |
      | testuser123@mail.com 		| TestUser@1 		|
