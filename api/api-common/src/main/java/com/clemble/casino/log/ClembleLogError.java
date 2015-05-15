package com.clemble.casino.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 5/15/15.
 */
public class ClembleLogError {

    final private String field;
    final private String location;
    final private String module;
    final private String code;
    final private String error;

    @JsonCreator
    public ClembleLogError(
            @JsonProperty("field") String field,
            @JsonProperty("location") String location,
            @JsonProperty("module") String module,
            @JsonProperty("code") String code,
            @JsonProperty("error") String error) {
        this.location = location;
        this.field = field;
        this.module = module;
        this.code = code;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getLocation() {
        return location;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleLogError)) return false;

        ClembleLogError that = (ClembleLogError) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (module != null ? !module.equals(that.module) : that.module != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }

}
