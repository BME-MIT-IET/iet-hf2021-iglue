#"Vannak stabil jégtáblák, amelyeken akárhány szereplő állhat"
Feature: Stable ice field
  Scenario: A bunch of actor moves to a stable ice field
    Given a stable ice field
    Given 69 eskimo
    Given 69 researcher
    When the eskimos move to the field
    And the researchers move to the field
    Then none of them should be in water
