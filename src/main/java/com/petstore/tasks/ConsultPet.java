package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultPet implements Task {
    private final Long petId;

    public ConsultPet(Long petId) {
        this.petId = petId;
    }

    public static ConsultPet withId(Long petId) {
        return instrumented(ConsultPet.class, petId);
    }

    @Override
    @Step("{0} consults pet with ID #petId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/pet/{petId}")
                        .with(request -> request.pathParam("petId", petId))
        );
    }
}
