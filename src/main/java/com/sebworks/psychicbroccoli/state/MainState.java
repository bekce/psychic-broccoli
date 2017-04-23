package com.sebworks.psychicbroccoli.state;

import com.sebworks.psychicbroccoli.common.Character;
import com.sebworks.psychicbroccoli.request.Option;
import com.sebworks.psychicbroccoli.request.SelectionRequest;

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
        switch (new SelectionRequest<MainOption>()
                .addOption(new Option<>(MainOption.EXPLORE, "Explore the world"))
                .addOption(new Option<>(MainOption.FIGHT, "Fight monsters"))
                .addOption(new Option<>(MainOption.SAVE, "Save game"))
                .addOption(new Option<>(MainOption.RETURN, "Return to main menu"))
                .ask()) {
            case EXPLORE:
                break;
            case FIGHT:
                break;
            case SAVE:
                break;
            case RETURN:
                break;
        }

        return null;
    }
}
