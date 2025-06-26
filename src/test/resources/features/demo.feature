Feature: Jugador Creation Test
  As a developer
  I want to test the jugador creation endpoint
  So that I can ensure it works correctly

  Scenario: Create a new jugador
    Given the server is running
    When I create a jugador with name "martin" mail "martin@qualita.studio" and phone "221568476"
    Then I should receive a valid response with name "martin" mail "martin@qualita.studio" and phone "221568476"

  Scenario: Create a new jugador 1
    Given the server is running
    When I create a jugador with name "jose" mail "jose@qualita.studio" and phone "221568478"
    Then I should receive a valid response with name "jose" mail "jose@qualita.studio" and phone "221568478"
