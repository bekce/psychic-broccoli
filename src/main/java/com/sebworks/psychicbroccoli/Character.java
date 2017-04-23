package com.sebworks.psychicbroccoli;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by bekce on 4/21/17.
 */
public class Character implements Serializable {

    private String name;
    private long updatedAt;
    private long createdAt;
    private long xp;
    private Level level;

    public Character(String name, long updatedAt, long createdAt, long xp, Level level) {
        this.name = name;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.xp = xp;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return updatedAt == character.updatedAt &&
                createdAt == character.createdAt &&
                xp == character.xp &&
                Objects.equals(name, character.name) &&
                level == character.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, updatedAt, createdAt, xp, level);
    }
}
