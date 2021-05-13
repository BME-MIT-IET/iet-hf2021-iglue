#"vannak instabil jégtáblák, amik adott létszám felett átfordulnak és ilyenkor a rajtuk állók a vízbe esnek.
# Jégtábla átfordulásnál az adott jégtáblán lévő hóréteg eltűnik"
Feature: Unstable ice field
  Scenario: The field's limit isn't reached
    Given an unstable ice field with a limit of 5 and snow level of 4
    And 4 eskimos

    When the eskimos move to the field

    Then none of the eskimos should be in water


  Scenario: The field's limit gets reached
    Given an unstable ice field with a limit of 3 and snow level of 2
    And 4 researchers

    When the researchers move to the field

    Then all of the researchers should be in water
    And the snow level should be 0