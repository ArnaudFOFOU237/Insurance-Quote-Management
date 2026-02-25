Feature: As an Insurer, I would like to fetch quote

  Scenario: Retrieve an existing Quote
    Given A quote exists in the system with ID "Q-123"
    When I request the quote with ID "Q-123"
    Then The response status should be 200
    And The product type should be "AUTO"
    And The client ID should be 1