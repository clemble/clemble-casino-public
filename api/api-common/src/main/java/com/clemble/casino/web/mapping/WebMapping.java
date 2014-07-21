package com.clemble.casino.web.mapping;

public interface WebMapping {

    String PRODUCES = "application/json";
    // TODO refactor to standard pattern URL with {host} variable & ending
    String URL = "http://{host}/";

}
