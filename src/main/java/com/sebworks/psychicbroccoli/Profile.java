package com.sebworks.psychicbroccoli;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by bekce on 4/21/17.
 */
public class Profile implements Serializable {

    private final String name;
    private final long updatedAt;
    private final long createdAt;
    private final State state;

    public Profile(String name, long updatedAt, long createdAt, State state) {
        this.name = name;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return updatedAt == profile.updatedAt &&
                createdAt == profile.createdAt &&
                Objects.equals(name, profile.name) &&
                Objects.equals(state, profile.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, updatedAt, createdAt, state);
    }
}
