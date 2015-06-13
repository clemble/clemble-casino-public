package com.clemble.casino.log;

import com.clemble.casino.error.ClembleErrorCode;
import com.clemble.casino.error.ClembleErrorCodeAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 5/15/15.
 */
public class ClientError implements ClembleErrorCodeAware {

    final private String field;
    final private String location;
    final private String module;
    final private ClientErrorType type;
    final private ClembleErrorCode code;
    final private String error;

    @JsonCreator
    public ClientError(
            @JsonProperty("field") String field,
            @JsonProperty("location") String location,
            @JsonProperty("module") String module,
            @JsonProperty("code") ClembleErrorCode code,
            @JsonProperty("type") ClientErrorType type,
            @JsonProperty("error") String error) {
        this.location = location;
        this.field = field;
        this.module = module;
        this.code = code;
        this.type = type;
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

    @Override
    public ClembleErrorCode getCode() {
        return code;
    }

    public ClientErrorType getType() {
        return type;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientError)) return false;

        ClientError that = (ClientError) o;

        if (code != that.code) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (module != null ? !module.equals(that.module) : that.module != null) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }

}
