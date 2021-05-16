#Egy jégtáblában maximum egy tárgy lehet.
Feature: There should be maximum one item in a block
  Scenario: Somehow another item gets into the block
  Given a stable ice block with snow level of 0
  And a swimsuit is frozen into the ice block

  When a tent freezes into that block

  Then the tent should be in the block
  And the swimsuit should not be in the block