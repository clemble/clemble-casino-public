package com.clemble.casino.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClembleError {

    final public static ClembleError SERVER_ERROR = ClembleError.withErrors(Collections.singleton(ClembleErrorCode.GeneralError));

    final private Set<ClembleFieldError> fields;
    final private Set<ClembleErrorCode> server;

    @JsonCreator
    public ClembleError(
            @JsonProperty("fields") Set<ClembleFieldError> fields,
            @JsonProperty("server") Set<ClembleErrorCode> server) {
        this.fields = fields;
        this.server = server;
    }

    public Set<ClembleFieldError> getFields() {
        return fields;
    }

    public Set<ClembleErrorCode> getServer() {
        return server;
    }

    static ClembleError withErrorCodes(Collection<String> errorCodes) {
        return withErrors(ClembleErrorCode.forCodes(errorCodes));
    }

    static ClembleError withErrors(Collection<ClembleErrorCode> errors) {
        return new ClembleError(Collections.<ClembleFieldError>emptySet(), new HashSet<ClembleErrorCode>(errors));
    }

    static ClembleError withFieldError(String field, ClembleErrorCode error) {
        return new ClembleError(Collections.singleton(new ClembleFieldError(field, error)), Collections.<ClembleErrorCode>emptySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleError)) return false;

        ClembleError that = (ClembleError) o;

        if (fields != null ? !fields.equals(that.fields) : that.fields != null) return false;
        if (server != null ? !server.equals(that.server) : that.server != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fields != null ? fields.hashCode() : 0;
        result = 31 * result + (server != null ? server.hashCode() : 0);
        return result;
    }
}
