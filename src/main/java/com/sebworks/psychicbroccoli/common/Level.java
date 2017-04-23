package com.sebworks.psychicbroccoli.common;

/**
 * Created by seb on 23.04.2017.
 */
public enum Level {
    LEVEL_1("Neophyte", 1, 0),
    LEVEL_2("Novice", 2, 100),
    LEVEL_3("Apprentice", 3, 200),
    LEVEL_4("Journeyman", 4, 500),
    LEVEL_5("Expert", 5, 1000),
    LEVEL_6("Adept", 6, 2000),
    LEVEL_7("Master", 7, 5000),
    LEVEL_8("Grand Master", 8, 10000),
    LEVEL_9("Elder", 9, 25000),
    LEVEL_10("Legendary", 10, 50000),
    ;

    private String title;
    private int level;
    private long xp;

    Level(String title, int level, long xp) {
        this.title = title;
        this.level = level;
        this.xp = xp;
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
}
