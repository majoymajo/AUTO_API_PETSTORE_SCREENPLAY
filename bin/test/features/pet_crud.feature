Feature: Pet CRUD Operations

  As a user of the PetStore API
  I want to be able to create, consult, update, and delete pets
  To ensure the integrity of the pet records

  Scenario: Full CRUD cycle for a pet
    Given that the user wants to manage the pet records
    When they create a new pet with name "Pluto" and status "available"
    Then the pet should be successfully created
    And they consult the pet by its identifier
    Then they should see the pet details correctly
    When they update the pet status to "sold"
    Then the pet status should be updated successfully
    When they delete the pet record
    Then the pet record should no longer exist
