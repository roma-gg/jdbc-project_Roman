Feature: Books module
  As a students, I should be able to borrow book

  @Test
  Scenario: Student borrow new book
    Given the "student" on the home page
    And the user navigates to "Books" page
    And the user searches for "Unique Book For Test" book
    When the user clicks Borrow Book
    Then verify that book "Unique Book For Test" is shown in Borrowing Books page
    And verify logged student has same "Unique Book For Test" book in database
    When the user returns "Unique Book For Test" book