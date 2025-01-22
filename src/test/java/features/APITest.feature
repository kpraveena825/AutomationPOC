@APIValidation
Feature: to verify add location verify

  @addAddress
  Scenario: Verify Add location API response
    Given User post a request to add Location
    Then Verify the response after Adding Location

  @updateAddress
  Scenario Outline: Verify API response for location update
    Given User updates the <Address> in json request
    Then Verify the response of PUT API

    Examples: 
      | Address                 |
      | 70 winter walk, Germany |
      | 70 winter walk, USA     |

  @getAddress
  Scenario: Verify Location fetch API response
    Given User get the API response
    Then Verify the response of GET API
