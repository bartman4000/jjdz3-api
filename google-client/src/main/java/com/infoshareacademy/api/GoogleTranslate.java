package com.infoshareacademy.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class GoogleTranslate {

    private final String API_KEY;

    public GoogleTranslate(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String translate(String input, String source, String target) {
        final String endpoint = "https://translation.googleapis.com/language/translate/v2";

        final Form params = new Form();
        params.param("key", API_KEY);
        params.param("q", input);
        params.param("source", source);
        params.param("target", target);

        final Client client = ClientBuilder.newClient();
        final WebTarget webTarget = client.target(endpoint);
        final Response response = webTarget.request().post(Entity.form(params));
        final String result = response.readEntity(String.class);
        response.close();
        return result;
    }
}
