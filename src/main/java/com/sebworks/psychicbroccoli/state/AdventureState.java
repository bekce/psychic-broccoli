package com.sebworks.psychicbroccoli.state;

import com.sebworks.psychicbroccoli.common.Character;

/**
 * Created by seb on 23.04.2017.
 */
public class AdventureState implements State {

    private Character character;

    public AdventureState(Character character) {
        this.character = character;
    }

    @Override
    public State outcome() {
        return new MainState(character);
    }
}
