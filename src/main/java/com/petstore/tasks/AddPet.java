package com.petstore.tasks;

import com.petstore.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddPet implements Task {
    private final Pet pet;

    public AddPet(Pet pet) {
        this.pet = pet;
    }

    public static AddPet withDetails(Pet pet) {
        return instrumented(AddPet.class, pet);
    }

    @Override
    @Step("{0} adds a new pet with name #pet.name")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/pet")
                        .with(request -> request.header("Content-Type", "application/json")
                                .body(pet))
        );
    }
}
