package com.infoshareacademy.api;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class GoogleTranslate {

    private final String API_KEY;

    public GoogleTranslate(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    private String generateQueryParams(String url, Map<String, String> params) {
        final StringBuilder builder = new StringBuilder();
        builder.append(url).append("?");
        params.forEach((key, value) -> {
            builder.append(key).append("=").append(value).append("&");
        });
        return builder.toString();
    }

    public String translate(String input, String source, String target) {
        final String endpoint = "https://translation.googleapis.com/language/translate/v2";

        final Map<String, String> params = new HashMap<>();
        params.put("key", API_KEY);
        params.put("q", input);
        params.put("source", source);
        params.put("target", target);

        final String url = generateQueryParams(endpoint, params);

        final Client client = ClientBuilder.newClient();
        final WebTarget webTarget = client.target(url);
        final Response response = webTarget.request().get();
        final String result = response.readEntity(String.class);
        response.close();
        return result;
    }
}
