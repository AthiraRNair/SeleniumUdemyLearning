#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: PurchaseOrder for ecommerce website
Background:
  Given Launch e-commerce application
  
  @tag2
  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When Add product to cart with productname <productName>
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | username  				 | password 		 | productName  |
      | athusaru@gmail.com | Athira@22 		 | ZARA COAT 3 |
   
