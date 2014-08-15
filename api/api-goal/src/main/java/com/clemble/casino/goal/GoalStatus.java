package com.clemble.casino.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by mavarazy on 8/15/14.
 */
public class GoalStatus implements Comparable<GoalStatus> {

    final private String status;
    final private Date statusDate;

    public GoalStatus(String status) {
        this.status = status;
        this.statusDate = new Date();
    }

    @JsonCreator
    public GoalStatus(@JsonProperty("status") String status, @JsonProperty("statusDate") Date statusDate) {
        this.status = status;
        this.statusDate = statusDate;
    }

    public String getStatus() {
        return status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStatus that = (GoalStatus) o;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (statusDate != null ? !statusDate.equals(that.statusDate) : that.statusDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (statusDate != null ? statusDate.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(GoalStatus o) {
        return this.statusDate.compareTo(o.statusDate);
    }
}
