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

    private int faintCount = 0;
    private int hpCurrent;
    private int hpMax;
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
        this.hpCurrent = level.getHp();
        this.hpMax = level.getHp();
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

    public List<Monster> getMonstersAround() {
        return monstersAround;
    }

    public int getFaintCount() {
        return faintCount;
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

    public void damage(int hp){
        if(hp > 0){
            if(hpCurrent - hp > 0){
//                System.out.println("You took "+hp+" damage!");
                hpCurrent -= hp;
            } else {
                int overkill = hp - hpCurrent + level.getLevel();
//                System.out.println("You took "+hp+" damage and fainted! You've lost "+overkill+" XP");
                System.out.println("You have fainted! You've lost "+overkill+" XP");
                xp -= overkill;
                if(xp < 0) xp = 0;
                hpCurrent = 0;
            }
        }
    }

    public void revive(){
        if(hpCurrent == 0){
            faintCount++;
            System.out.println("You woke up and see that all monsters are gone, you are feeling fine again");
            hpCurrent = (int) (hpMax * 0.75);
            this.monstersAround.clear();
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
        return String.format("%s, %s, HP: %d/%d", name, level.getTitle(), hpCurrent, hpMax);
    }
}
