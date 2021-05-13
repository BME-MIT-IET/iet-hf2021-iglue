#Az egyes jégtáblákba különféle tárgyak lehetnek belefagyva:
#lapát, kötél, búvárruha, élelem, pisztoly, jelzőfény, patron
Feature: Items can be frozen into ice blocks
  Scenario: A frozen spade
    Given a stable ice block with snow level of 0
    And a spade is frozen into the ice block
    And an eskimo is standing on the block

    When the eskimo tries to dig out the item

    Then the eskimo should have the item


  Scenario: A frozen food
    Given a stable ice block with snow level of 0
    And a food is frozen into the ice block
    And an eskimo is standing on the block

    When the eskimo tries to dig out the item

    Then the eskimo should have the item


  Scenario: A frozen rope
    Given a stable ice block with snow level of 0
    And a food is frozen into the ice block
    And an eskimo is standing on the block

    When the eskimo tries to dig out the item

    Then the eskimo should have the item


  Scenario: A frozen tent
    Given a stable ice block with snow level of 0
    And a tent is frozen into the ice block
    And an eskimo is standing on the block

    When the eskimo tries to dig out the item

    Then the eskimo should have the item


  Scenario: A frozen swimsuit
    Given a stable ice block with snow level of 0
    And a swimsuit is frozen into the ice block
    And an eskimo is standing on the block

    When the eskimo tries to dig out the item

    Then the eskimo should have the item