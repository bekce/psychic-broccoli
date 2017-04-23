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
                    //TODO
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
