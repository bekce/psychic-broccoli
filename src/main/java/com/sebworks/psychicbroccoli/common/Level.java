package com.sebworks.psychicbroccoli.common;

/**
 * Created by seb on 23.04.2017.
 */
public enum Level {
    LEVEL_1("Neophyte", 1, 0, 100),
    LEVEL_2("Novice", 2, 100, 120),
    LEVEL_3("Apprentice", 3, 200, 150),
    LEVEL_4("Journeyman", 4, 500, 200),
    LEVEL_5("Expert", 5, 1000, 275),
    LEVEL_6("Adept", 6, 2000, 400),
    LEVEL_7("Master", 7, 5000, 600),
    LEVEL_8("Grand Master", 8, 10000, 1000),
    LEVEL_9("Elder", 9, 25000, 1500),
    LEVEL_10("Legendary", 10, 50000, 2500),
    ;

    private String title;
    private int level;
    private long xp;
    private int hp;

    Level(String title, int level, long xp, int hp) {
        this.title = title;
        this.level = level;
        this.xp = xp;
        this.hp = hp;
    }

    public String getTitle() {
        return title;
    }

    public int getLevel() {
        return level;
    }

    public long getXp() {
        return xp;
    }

    public int getHp() {
        return hp;
    }

    public Level nextLevel(){
        if(this.ordinal() == values().length-1) return null;
        return values()[this.ordinal()+1];
    }
}
