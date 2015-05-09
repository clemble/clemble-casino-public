package com.clemble.casino.tag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 2/3/15.
 */
public class ClembleTag implements TagAware, Comparable<ClembleTag>{

    final private String tag;
    final private int power;

    @JsonCreator
    public ClembleTag(@JsonProperty("tag") String tag, @JsonProperty("power") int power) {
        this.tag = tag;
        this.power = power;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public int getPower() {
        return power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClembleTag)) return false;

        ClembleTag that = (ClembleTag) o;

        if (power != that.power) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + power;
        return result;
    }

    @Override
    public int compareTo(ClembleTag o) {
        return this.tag.compareTo(o.getTag());
    }

}
