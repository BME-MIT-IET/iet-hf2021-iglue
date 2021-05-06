#"Vannak stabil jégtáblák, amelyeken akárhány szereplő állhat"
Feature: Stable ice field
  Scenario: A bunch of actor moves to a stable ice field
    Given a stable ice field
    And 69 eskimos
    And 69 researchers

    When the eskimos move to the field
    And the researchers move to the field

    Then none of the eskimos should be in water
    And none of the researchers should be in water