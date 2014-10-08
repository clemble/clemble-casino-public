package com.clemble.casino;

public interface WebMapping {

    String PRODUCES = "application/json";
    // TODO refactor to standard pattern URL with {host} variable & ending
    String URL = "http://{host}/";
    String PROVIDER_ID = "clemble";

}
