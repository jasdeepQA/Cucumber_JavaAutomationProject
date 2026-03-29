Feature: User Registration and Authentication Flow
  @register
  Scenario: Register a new user successfully

    Given I navigate to automationexercise.com
    Then I should see the home page successfully
    When I click on the Signup Login button
    Then I should see "New User Signup!" text
    When I enter a name and email address
    And I click on the Signup button
    Then I should see "Enter Account Information" text
    When I fill in account details:
    And I select "Sign up for our newsletter!"
    And I select "Receive special offers from our partners!"
    And I fill in personal details
    And I click the Create Account button
    Then I should see "Account Created!" text
    When I click on the Continue button
    Then I should see "Logged in as" text
    Given I am logged in as "Jasdeep Singh"
    When I click the Delete Account button
    Then I should see 'Account Deleted!' text
    When I click on the Continue button
    Then I should see the home page successfully
    Then I should see the home page successfully



  @register
  Scenario: User attempts to register with an already existing email
    Given I navigate to automationexercise.com
    Then I should see the home page successfully
    When I click on the Signup Login button
    Then I should see "New User Signup!" text
    When I enter a name and an already registered email address
    And I click on the Signup button
    Then I should see the error message "Email Address already exist!"