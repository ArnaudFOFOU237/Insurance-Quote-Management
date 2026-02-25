Feature: As an Insurer, I would like to perform the following actions

  Scenario: Create Quote
    Given The Insure give the followings information:
    | PRODUCT-TYPE | CLIENT-ID | PERCENTAGE-INSURE|
    |    AUTO      |     1     |     50.6         |
    When The Insure save the quote with those information
    Then The quote is save
    And the status code is created