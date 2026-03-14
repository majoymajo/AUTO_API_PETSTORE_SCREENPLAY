package com.petstore.questions;

import com.petstore.models.Pet;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ThePetDetails implements Question<Pet> {

    public static ThePetDetails inResponse() {
        return new ThePetDetails();
    }

    @Override
    public Pet answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Pet.class);
    }

    public Question<String> getName() {
        return actor -> answeredBy(actor).getName();
    }

    public Question<String> getStatus() {
        return actor -> answeredBy(actor).getStatus();
    }
}
