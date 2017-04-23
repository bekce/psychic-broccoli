package com.sebworks.psychicbroccoli.common;

/**
 * Created by seb on 23.04.2017.
 */
public class Monster {
    private int hpCurrent;
    private int hpMax;
    private String name;
    private int xpReward;

    public Monster(int hpCurrent, int hpMax, String name, int xpReward) {
        this.hpCurrent = hpCurrent;
        this.hpMax = hpMax;
        this.name = name;
        this.xpReward = xpReward;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXpReward() {
        return xpReward;
    }

    public void setXpReward(int xpReward) {
        this.xpReward = xpReward;
    }
}
