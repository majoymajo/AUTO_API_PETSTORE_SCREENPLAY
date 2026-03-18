package com.petstore.stepdefinitions;

import com.petstore.models.Pet;
import com.petstore.questions.LastResponseStatusCode;
import com.petstore.questions.ThePetDetails;
import com.petstore.tasks.AddPet;
import com.petstore.tasks.ConsultPet;
import com.petstore.tasks.DeletePet;
import com.petstore.tasks.UpdatePet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class PetStepDefinitions {

    private Pet currentPet;

    @Given("that the user wants to manage the pet records")
    public void setupActor() {
    }

    @When("they create a new pet with name {string} and status {string}")
    public void createPet(String name, String status) {
        currentPet = new Pet(name, status);
        theActorInTheSpotlight().attemptsTo(AddPet.withDetails(currentPet));
        currentPet.setId(theActorInTheSpotlight().asksFor(ThePetDetails.inResponse()).getId());
    }

    @Then("the pet should be successfully created")
    public void verifyCreation() {
        theActorInTheSpotlight().should(seeThat(LastResponseStatusCode.is(), equalTo(200)));
    }

    @Then("they consult the pet by its identifier")
    public void consultPet() {
        theActorInTheSpotlight().attemptsTo(ConsultPet.withId(currentPet.getId()));
    }

    @Then("they should see the pet details correctly")
    public void verifyPetDetails() {
        theActorInTheSpotlight().should(
                seeThat(LastResponseStatusCode.is(), equalTo(200)),
                seeThat(ThePetDetails.inResponse().getName(), equalTo(currentPet.getName()))
        );
    }

    @When("they update the pet status to {string}")
    public void updatePetStatus(String newStatus) {
        currentPet.setStatus(newStatus);
        theActorInTheSpotlight().attemptsTo(UpdatePet.withDetails(currentPet));
    }

    @Then("the pet status should be updated successfully")
    public void verifyUpdate() {
        theActorInTheSpotlight().should(
                seeThat(LastResponseStatusCode.is(), equalTo(200)),
                seeThat(ThePetDetails.inResponse().getStatus(), equalTo(currentPet.getStatus()))
        );
    }

    @When("they delete the pet record")
    public void deletePet() {
        theActorInTheSpotlight().attemptsTo(DeletePet.withId(currentPet.getId()));
    }

    @Then("the pet record should no longer exist")
    public void verifyDeletion() {
        theActorInTheSpotlight().should(seeThat(LastResponseStatusCode.is(), equalTo(200)));
        theActorInTheSpotlight().attemptsTo(ConsultPet.withId(currentPet.getId()));
        theActorInTheSpotlight().should(seeThat(LastResponseStatusCode.is(), equalTo(404)));
    }
}
