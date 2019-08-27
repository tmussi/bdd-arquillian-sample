Feature: User features

  Scenario: Create user with all data
    When create user:
      | name             | email                         | password     |
      | Jon Snow         | jon.snow@gameofthrones.com    | fireAndBlood |
      | Cersei Baratheon | cersei.lanister@gameofthrones | shame!!!     |
    Then user should exists:
      | name             | email                         | password     |
      | Jon Snow         | jon.snow@gameofthrones.com    | fireAndBlood |
      | Cersei Baratheon | cersei.lanister@gameofthrones | shame!!!     |
