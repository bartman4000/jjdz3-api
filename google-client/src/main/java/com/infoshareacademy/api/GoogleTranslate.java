package com.infoshareacademy.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GoogleTranslate {

    private final String API_KEY;

    public GoogleTranslate(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String translate(String input, String source, String target) {

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("https://translation.googleapis.com/language/translate/v2");

        Form params = new Form();
        params.param("q", input);
        params.param("source", source);
        params.param("target", target);
        params.param("key", API_KEY);

        Response response = webTarget.request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.form(params));;

        int status = response.getStatus();


        if(status == 200)
        {
            GoogleTranslateResponse responseValue = response
                    .readEntity(GoogleTranslateResponse.class);
            response.close();
            return responseValue.getData().getTranslations().get(0).getTranslatedText();
        }
        else
        {
            GoogleTranslateError errorValue = response
                    .readEntity(GoogleTranslateError.class);
            response.close();
            return errorValue.getMessage();
        }
    }
}
