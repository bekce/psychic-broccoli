package com.sebworks.psychicbroccoli.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by bekce on 4/21/17.
 */
public class Character implements Serializable {

    private String id;
    private String name;
    private long updatedAt;
    private long createdAt;
    private long xp;
    private Level level;

    private int hpCurrent = 100;
    private int hpMax = 100;
    private int noOfMonstersAround = 0;
    private List<Monster> monstersAround = new ArrayList<>();

    public Character(String name) {
        this(name, System.currentTimeMillis(), System.currentTimeMillis(), 0, Level.LEVEL_1);
    }

    public Character(String name, long updatedAt, long createdAt, long xp, Level level) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.xp = xp;
        this.level = level;
    }

    public String getId() {
        return id;
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

    public int getHpCurrent() {
        return hpCurrent;
    }

    public void setHpCurrent(int hpCurrent) {
        this.hpCurrent = hpCurrent;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getNoOfMonstersAround() {
        return noOfMonstersAround;
    }

    public void setNoOfMonstersAround(int noOfMonstersAround) {
        this.noOfMonstersAround = noOfMonstersAround;
    }

    public List<Monster> getMonstersAround() {
        return monstersAround;
    }

    public void addXP(int xp){
        this.xp += xp;
        System.out.println("You have gained "+xp+" XP");
        Level nextLevel = this.level.nextLevel();
        if(nextLevel != null && nextLevel.getXp() <= this.xp){
            System.out.println("Leveled up! You are now a "+nextLevel.getTitle());
            this.level = nextLevel;
            this.hpMax = level.getHp();
            this.hpCurrent = level.getHp();
        }
    }

    public void heal(int hp){
        if(hpCurrent < hpMax){
            int heal = Math.min(hp, hpMax - hpCurrent);
            hpCurrent += heal;
            if(heal > 0){
                System.out.println("You have healed for "+hp+" HP");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", name, level.getTitle());
    }
}
