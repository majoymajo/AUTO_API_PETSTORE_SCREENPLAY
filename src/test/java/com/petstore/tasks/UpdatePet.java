package com.petstore.tasks;

import com.petstore.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdatePet implements Task {
    private final Pet pet;

    public UpdatePet(Pet pet) {
        this.pet = pet;
    }

    public static UpdatePet withDetails(Pet pet) {
        return instrumented(UpdatePet.class, pet);
    }

    @Override
    @Step("{0} updates pet with ID #pet.id")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/pet")
                        .with(request -> request.header("Content-Type", "application/json")
                                .body(pet))
        );
    }
}
