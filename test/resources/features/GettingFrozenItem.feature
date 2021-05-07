#Befagyott tárgyat csak akkor lehet meglátni és kiásni, ha a jégtábla tiszta,
#nem borítja hó és ekkor látszik, hogy milyen tárgy van a jégtáblában.
#Egy tárgy kiásása kézzel történhet, mely szintén egy munkaegység.
Feature: Frozen items can be found
  Scenario: Player tries to get an item which can't be seen
    Given a stable ice block with snow level of 6
    And a spade is frozen into the ice block
    And an eskimo is standing on the block

    When the eskimo tries to dig out the item

    Then the eskimo should not have the item


  Scenario: Player cleans the block first
    Given a stable ice block with snow level of 2
    And a spade is frozen into the ice block
    And an eskimo is standing on the block with energy level of 4

    When the eskimo cleans the block
    And the eskimo tries to dig out the item

    Then the eskimo should have the item


  Scenario: Digging out a frozen item should take one working unit
    Given a stable ice block with snow level of 0
    And a spade is frozen into the ice block
    And an eskimo is standing on the block with energy level of 4

    When the eskimo tries to dig out the item

    Then the eskimo should have the energy level of 3
    And the eskimo should have the item
