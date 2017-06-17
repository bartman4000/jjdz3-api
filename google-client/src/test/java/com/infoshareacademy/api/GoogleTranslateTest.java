package com.infoshareacademy.api;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GoogleTranslateTest {

    public static final String API_KEY = "AIzaSyATncQL0uaAt0MJtTnIOv2np1GqTanm2Bs";
    public static final String INVALID_API_KEY = "dummykey";

    private GoogleTranslate googleTranslate;

    @Test
    public void shouldTranslate() {
        googleTranslate = new GoogleTranslate(API_KEY);

        final String input = "Hi, how are you?";
        final String source = "en";
        final String target = "pl";

        final String result = googleTranslate.translate(input, source, target);
        assertThat(result, is("Cześć jak się masz?"));
    }

    @Test
    public void shouldHandleFailures() {
        googleTranslate = new GoogleTranslate(INVALID_API_KEY);

        final String input = "Hi, how are you?";
        final String source = "en";
        final String target = "pl";

        final String result = googleTranslate.translate(input, source, target);
        assertThat(result, is("API key not valid. Please pass a valid API key."));
    }
}
