package com.sebworks.psychicbroccoli;

import com.sebworks.psychicbroccoli.common.Character;
import com.sebworks.psychicbroccoli.common.Monster;
import com.sebworks.psychicbroccoli.util.MonsterUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by seb on 23.04.2017.
 */
public class CommonTest {
    private Character character;

    @Before
    public void setUp() throws Exception {
        character = new Character("name");
    }

    @Test
    public void damage() throws Exception {
        character.damage(50);
        assertEquals(50, character.getHpCurrent());
        character.heal(20);
        assertEquals(70, character.getHpCurrent());
        character.damage(200);
        assertEquals(0, character.getHpCurrent());
        character.revive();
        character.heal(100);
        assertEquals(character.getHpMax(), character.getHpCurrent());
    }

    @Test
    public void monster() throws Exception {
        Monster monster = MonsterUtil.generateMonster(character);
        double ratio = (double) monster.getHpMax() / character.getHpMax();
        assertTrue(ratio > 0.5 && ratio < 2.0);
    }
}
