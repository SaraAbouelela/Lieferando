Feature: As tester i want to validate set of APIs ( Token generation , Unlocking the barn , Feeding the chicken , Putting the toiletseat down )

  @TestScenario2
  Scenario Outline: Testing getting token API with valid ClientSecret
    Given User has "<ValidClientSecret>"
    When Call get token API
    Then Response Code is <ResponseCode>
    And Token is Received successfully
    Examples:
      | ValidClientSecret | ResponseCode |
      | 72eed00746ffa1ad5c909b7d0aa919db | 200 |

  @TestScenario2
  Scenario Outline: Testing getting token API with invalid ClientSecret
    Given User has "<InvalidClientSecret>"
    When Call get token API
    Then Response Code is <ResponseCode>
    And No Token is generated
    And Validate error msg
    Examples:
      | InvalidClientSecret | ResponseCode |
      | 72eed00746ffa1ad5c909b7d0aa91911 | 400 |

  @TestScenario2
  Scenario: Testing unlocking the barn with valid key
    Given Farmer has valid key
    When Unlock Barn
    Then Barn is unlocked successfully

  @TestScenario2
  Scenario: Testing unlocking the barn with invalid key
    Given Farmer has Invalid key
    When Unlock Barn
    Then Validate Theft alarm ringing

  @TestScenario2
  Scenario: Testing feeding the chicken action
    Given Farmer has valid key
    When Farmer feed the chicken
    Then The chicken now is full and happy!

  @TestScenario2
  Scenario: Testing feeding the chicken action
    Given Farmer has Invalid key
    When Farmer feed the chicken
    Then Validate Theft alarm ringing

  @TestScenario2
  Scenario: Testing toilet seat-down action
    Given Farmer has valid key
    When Farmer use the toilet and seat it down
    Then Tell him encouragement words

  @TestScenario2
  Scenario: Testing toilet seat-down action with invalid key
    Given Farmer has Invalid key
    When Farmer use the toilet and seat it down
    Then Validate Theft alarm ringing
