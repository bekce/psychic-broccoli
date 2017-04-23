package com.sebworks.psychicbroccoli.util;

import com.sebworks.psychicbroccoli.common.Character;
import com.sebworks.psychicbroccoli.common.Monster;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by seb on 23.04.2017.
 */
public class MonsterUtil {

    private static List<String> monsterNames;

    static {
        try {
            monsterNames = Files.readAllLines(Paths.get(MonsterUtil.class.getResource("/monsters.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Monster generateMonster(Character character){
        String monsterName = monsterNames.get((int) (Math.random() * monsterNames.size()));
        int hp = (int) (character.getHpMax() * (Math.random() * 1.0 + 0.7));
        return new Monster(hp, hp, monsterName, 10 + hp / 10);
    }
}
