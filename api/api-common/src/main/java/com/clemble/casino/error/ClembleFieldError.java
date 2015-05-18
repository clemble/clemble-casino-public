package com.clemble.casino.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 5/13/15.
 */
public class ClembleFieldError implements ClembleErrorCodeAware {

    final private String field;
    final private ClembleErrorCode code;

    public ClembleFieldError(
            @JsonProperty("field") String field,
            @JsonProperty("code") ClembleErrorCode error
    ) {
        this.field = field;
        this.code = error;
    }

    public String getField() {
        return field;
    }

    public ClembleErrorCode getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleFieldError)) return false;

        ClembleFieldError that = (ClembleFieldError) o;

        if (!code.equals(that.code)) return false;
        if (!field.equals(that.field)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = field.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

}
