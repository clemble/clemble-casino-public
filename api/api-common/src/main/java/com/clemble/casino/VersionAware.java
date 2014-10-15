package com.clemble.casino;

import org.springframework.data.annotation.Version;

import java.io.Serializable;

public interface VersionAware extends Serializable {

    @Version
    public Integer getVersion();

}
