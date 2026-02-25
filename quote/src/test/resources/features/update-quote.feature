Feature: As an Insurer, I would like to update quote
  Scenario: Update an existing Quote
    Given A quote exists with ID "Q-123"
    And The Insurer provides the following updated information:
    | PRODUCT-TYPE | CLIENT-ID | PERCENTAGE-INSURED |
    | AUTO         | 1         | 75.0               |
    When The Insurer updates the quote "Q-123" with this information
    Then The quote is successfully updated
    And the status code is 200