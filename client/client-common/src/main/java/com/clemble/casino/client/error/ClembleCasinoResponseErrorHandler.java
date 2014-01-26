package com.clemble.casino.client.error;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.error.ClembleCasinoFailure;
import com.clemble.casino.error.ClembleCasinoFailureDescription;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClembleCasinoResponseErrorHandler implements ResponseErrorHandler {

    final private static Logger LOG = LoggerFactory.getLogger(ClembleCasinoResponseErrorHandler.class);

    final public static String ERROR_CODES_HEADER = "Clemble-Error-Code";

    final ObjectMapper objectMapper;

    public ClembleCasinoResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().value() >= 400;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        int contentLength = Math.max(response.getBody().available(), (int) response.getHeaders().getContentLength()); 
        // Step 1. Reading error string
        ClembleCasinoException exception = null;
        try {
            byte[] buffer = new byte[contentLength];
            response.getBody().read(buffer);
            String errorMessage = new String(buffer);
            // Step 2. Checking that response is of JSON type
            ClembleCasinoFailure[] failures = objectMapper.readValue(errorMessage, ClembleCasinoFailure[].class);
            exception = ClembleCasinoException.fromDescription(new ClembleCasinoFailureDescription().setProblems(Arrays.asList(failures)));
        } catch (Throwable throwable) {
            LOG.error("Failed to read failure descriptions, falling back to error codes", throwable);
            List<String> errorCodes = response.getHeaders().get(ERROR_CODES_HEADER);
            exception = ClembleCasinoException.fromCodes(errorCodes);
        }
        // Step 3. Generating ClembleCasinoException
        throw exception;
    }
}
