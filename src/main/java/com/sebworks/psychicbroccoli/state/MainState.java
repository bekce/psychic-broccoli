package com.sebworks.psychicbroccoli.state;

import com.sebworks.psychicbroccoli.common.Character;
import com.sebworks.psychicbroccoli.common.Monster;
import com.sebworks.psychicbroccoli.request.Option;
import com.sebworks.psychicbroccoli.request.SelectionRequest;
import com.sebworks.psychicbroccoli.util.MonsterUtil;
import com.sebworks.psychicbroccoli.util.SaveLoadUtil;

import java.io.IOException;

/**
 * Created by seb on 23.04.2017.
 */
public class MainState implements State {

    enum MainOption {
        EXPLORE, FIGHT, SAVE, RETURN
    }

    private Character character;

    public MainState(Character character) {
        this.character = character;
    }

    @Override
    public State outcome() {
        while(true){
            System.out.println("\nCharacter: " + character);
            switch (new SelectionRequest<MainOption>()
                    .addOption(new Option<>(MainOption.EXPLORE, "Explore the world"))
                    .addOption(new Option<>(MainOption.FIGHT, "Fight monsters"))
                    .addOption(new Option<>(MainOption.SAVE, "Save progress"))
                    .addOption(new Option<>(MainOption.RETURN, "Return to main menu"))
                    .ask()) {
                case EXPLORE:
                    if(character.getMonstersAround().size() >= 3){
                        System.err.println("Cannot move, blocked by monsters!");
                        break;
                    }
                    if(Math.random() < 0.4) {
                        Monster monster = MonsterUtil.generateMonster(character);
                        character.getMonstersAround().add(monster);
                        System.out.println("You've encountered a monster: "+monster);
                    } else {
                        int xp = (int) (Math.random() * character.getLevel().getLevel() * 5) + 1;
                        character.addXP(xp);
                        character.heal(xp);
                    }
                    return new AdventureState(character);
                case FIGHT:
                    if(character.getMonstersAround().isEmpty()){
                        System.err.println("No monsters around!");
                        break;
                    }
                    Monster monster = new SelectionRequest<Monster>("Fight which monster")
                            .setOptionValues(character.getMonstersAround()).ask();
                    double hpRatio = monster.getHpMax() / character.getHpMax();
                    double monsterCriticalChance = 0.05;
                    if(hpRatio > 1.2) monsterCriticalChance += 0.05;
                    if(hpRatio > 1.4) monsterCriticalChance += 0.05;
                    if(hpRatio > 1.6) monsterCriticalChance += 0.1;
                    while(monster.getHpCurrent() > 0 && character.getHpCurrent() > 0){
                        int monsterDmg = (int) ((Math.random() * 0.05 + 0.05) * monster.getHpMax());
                        if(Math.random() <= monsterCriticalChance){
                            monsterDmg *= 1.5;
                            System.out.println(monster.getName() + " scores a critical hit!");
                        }

                        int playerDmg = (int) ((Math.random() * 0.1 + 0.1) * character.getHpMax());
                        if(Math.random() <= (0.05 + (character.getLevel().getLevel() / 100.0))){
                            playerDmg *= 2.0;
                            System.out.println("You scored a critical hit!");
                        }
                        monster.setHpCurrent(monster.getHpCurrent() - playerDmg);
                        character.damage(monsterDmg);
                        System.out.println(String.format("%s did %d damage to you (%d/%d)", monster.getName(), monsterDmg, character.getHpCurrent(), character.getHpMax()));
                        System.out.println(String.format("You did %d damage to %s (%d/%d)", playerDmg, monster.getName(), monster.getHpCurrent(), monster.getHpMax()));
                    }
                    if(character.getHpCurrent() == 0){
                        character.revive();
                    } else {
                        System.out.println("Victorious! You've beaten "+monster.getName());
                        character.getMonstersAround().remove(monster);
                        character.addXP(monster.getXpReward());
                        character.heal((int) (character.getHpMax() * 0.25));
                        if(Math.random() < 0.3) {
                            if(!character.getMonstersAround().isEmpty()){
                                Monster fleeingMonster = character.getMonstersAround().get((int) (Math.random() * character.getMonstersAround().size()));
                                character.getMonstersAround().remove(fleeingMonster);
                                System.out.println("After your glory, "+fleeingMonster.getName()+" also flees away");
                            }
                        }
                    }
                    break;
                case SAVE:
                    try {
                        SaveLoadUtil.save(character);
                        System.out.println("Progress saved!");
                    } catch (IOException e) {
                        System.err.println("Problem saving your character! Detail: "+e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case RETURN:
                    return new WelcomeState();
            }
        }
    }
}
