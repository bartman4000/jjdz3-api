package com.infoshareacademy.api;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import com.infoshareacademy.api.model.StateDetails;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StatesClientTest {

    private StatesClient statesClient;

    @Before
    public void setUp() {
        statesClient = new StatesClient();
    }

    @Test
    public void getAllStates() throws Exception {
        final List<StateDetails> states = statesClient.getAllStates();

        assertNotNull(states);
        assertThat(states.size(), is(56));
    }

    @Test
    public void getStateDetails() throws Exception {
        final StateDetails stateDetails = statesClient.getStateDetails("AR");

        assertNotNull(stateDetails);
        assertThat(stateDetails.getAbbr(), is("AR"));
        assertThat(stateDetails.getName(), is("Arkansas"));
    }

}