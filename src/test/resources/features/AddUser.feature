Feature: Users module
  As a librarian, I should be able to add new user into library

  @homework
  Scenario Outline: Verify added user able to login libraryApp
    Given the "librarian" on the home page
    And the librarian navigates to "Users" page
    When the librarian clicks to Add User
    And the librarian enters fullname "<FullName>"
    When the librarian enters password "<Password>"
    And the librarian enters email "<Email>"
    And the librarian clicks to save changes
    Then verify "The user has been created" message is displayed
    And verify created user with "<Email>" and "<Password>" able to login
    And verify created user should be able to see "<FullName>"
    And created user should be deleted from library

    Examples:
      | FullName       | Password    | Email             |
      | Iryna Nichveda | libraryUser | iryna1502@library |