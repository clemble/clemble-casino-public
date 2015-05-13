package com.clemble.casino.error;

/**
 * Created by mavarazy on 5/13/15.
 */
public class ClembleCasinoFieldError {

    final private String field;
    final private ClembleCasinoError error;

    public ClembleCasinoFieldError(String field, ClembleCasinoError error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public ClembleCasinoError getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleCasinoFieldError)) return false;

        ClembleCasinoFieldError that = (ClembleCasinoFieldError) o;

        if (error != that.error) return false;
        if (!field.equals(that.field)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = field.hashCode();
        result = 31 * result + error.hashCode();
        return result;
    }

}
