package com.clemble.casino.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 5/13/15.
 */
public class ClembleCasinoFieldError {

    final private String field;
    final private String code;
    final private String description;

    public ClembleCasinoFieldError(String field, ClembleCasinoError error) {
        this.field = field;
        this.code = error.getCode();
        this.description = error.getDescription();
    }

    @JsonCreator
    public ClembleCasinoFieldError(
        @JsonProperty("field") String field,
        @JsonProperty("code") String code,
        @JsonProperty("description") String description) {
        this.field = field;
        this.code = code;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleCasinoFieldError)) return false;

        ClembleCasinoFieldError that = (ClembleCasinoFieldError) o;

        if (!code.equals(that.code)) return false;
        if (!description.equals(that.description)) return false;
        if (!field.equals(that.field)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = field.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

}
