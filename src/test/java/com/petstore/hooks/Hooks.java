package com.petstore.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.core.di.SerenityInfrastructure;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Hooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        EnvironmentVariables environmentVariables = SerenityInfrastructure.getEnvironmentVariables();
        String baseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("https://petstore.swagger.io/v2");
        theActorCalled("User").can(CallAnApi.at(baseUrl));
    }
}
