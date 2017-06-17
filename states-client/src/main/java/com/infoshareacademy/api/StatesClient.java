package com.infoshareacademy.api;

import com.infoshareacademy.api.model.StateDetails;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class StatesClient {

    private static final String ALL_STATES_URL = "http://services.groupkt.com/state/get/USA/all";

    public List<StateDetails> getAllStates() {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(ALL_STATES_URL);
        final Response response = target.request().get();
        //TODO:
        throw new UnsupportedOperationException("Not implemented yer");
    }

    public StateDetails getStateDetails(String stateCode) {
        throw new UnsupportedOperationException("Not implemented yer");
    }
}
