package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeletePet implements Task {
    private final Long petId;

    public DeletePet(Long petId) {
        this.petId = petId;
    }

    public static DeletePet withId(Long petId) {
        return instrumented(DeletePet.class, petId);
    }

    @Override
    @Step("{0} deletes pet with ID #petId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/pet/{petId}")
                        .with(request -> request.pathParam("petId", petId))
        );
    }
}
