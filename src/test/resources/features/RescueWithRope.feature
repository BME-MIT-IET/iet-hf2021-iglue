# A vízbe esett szereplők egy köteles szomszédjuk által menthetők meg
# Kötéllel egy munkaegység ráfordításával egy szereplő menthető meg.
# A kimentett szereplő arra a jégtáblára kerül, amelyiken az őt kimentő szereplő tartózkodik.
Feature: Rescue with rope
  Scenario: A successful rescue
    Given an unstable ice field with a limit of 0
    And a stable ice field to which the rescue should be made
    And these fields are neighboring
    Given a careless researcher
    And he moves to the unstable field
    Given a helpful eskimo
    And the eskimo has rope
    And the eskimo is on the rescuer field
    And the energy level of the eskimo is 5

    When the eskimo tries to rescue his friend

    Then both of them should be on the rescuer field
    And the energy level of the eskimo should be 4


  Scenario: Too ambitious rescuer
    Given an unstable ice field with a limit of 0
    And a stable ice field to which the rescue should be made
    And these fields are not neighboring
    Given a careless researcher
    And he moves to the unstable field
    Given a helpful eskimo
    And the eskimo has rope
    And the eskimo is on the rescuer field
    And the energy level of the eskimo is 5

    When the eskimo tries to rescue his friend

    Then the eskimo should stay in water
    And the energy level of the eskimo should be 5


  Scenario: From bad to worse
    Given an unstable ice field with a limit of 0
    And an unstable ice field with a limit of 1 to which the rescue should be made
    And these fields are neighboring
    Given a careless researcher
    And he moves to the unstable field
    Given a helpful eskimo
    And the eskimo has rope
    And the eskimo is on the rescuer field
    And the energy level of the eskimo is 5

    When the eskimo tries to rescue his friend

    Then both of them should be in water
    And the energy level of the eskimo should be 4