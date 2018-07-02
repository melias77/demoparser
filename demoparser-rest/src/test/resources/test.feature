Feature: the version can be retrieved
  Scenario: client makes call to GET /kunde/cpr/all
    When the client calls /kunde/cpr/all
    Then the client receives status code of 200
#    And the client receives server version 1.0