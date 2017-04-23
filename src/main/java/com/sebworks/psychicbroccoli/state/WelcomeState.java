package com.sebworks.psychicbroccoli.state;

import com.sebworks.psychicbroccoli.common.Character;
import com.sebworks.psychicbroccoli.request.Option;
import com.sebworks.psychicbroccoli.util.SaveLoadUtil;
import com.sebworks.psychicbroccoli.request.InputRequest;
import com.sebworks.psychicbroccoli.request.SelectionRequest;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static com.sebworks.psychicbroccoli.state.WelcomeState.CreateLoadOption.CREATE;
import static com.sebworks.psychicbroccoli.state.WelcomeState.CreateLoadOption.LOAD;
import static com.sebworks.psychicbroccoli.state.WelcomeState.CreateLoadOption.QUIT;

/**
 * Created by seb on 23.04.2017.
 */
public class WelcomeState implements State {

    enum CreateLoadOption {
        CREATE, LOAD, QUIT
    }

    private static final Pattern characterNamePattern = Pattern.compile("^[a-zA-Z0-9_ -]{3,20}$");

    @Override
    public State outcome() {
        while (true) {
            System.out.println("\nWelcome to psychic broccoli");
            switch (new SelectionRequest<CreateLoadOption>()
                    .addOption(new Option<>(CREATE, "Create a character"))
                    .addOption(new Option<>(LOAD, "Load a character"))
                    .addOption(new Option<>(QUIT, "Quit the game"))
                    .ask()) {
                case CREATE:
                    String name = new InputRequest("Enter character name", characterNamePattern).ask();
                    System.out.println(name + " embarks upon a new adventure!");
                    return new MainState(new Character(name));
                case LOAD:
                    List<Character> characters = new SaveLoadUtil().load();
                    if (characters.isEmpty()) {
                        System.err.println("You don't currently have any characters");
                        break;
                    }
                    Collections.reverse(characters);
                    return new MainState(new SelectionRequest<Character>("Select a character to load")
                            .setOptionValues(characters)
                            .ask());
                case QUIT:
                    System.out.println("Have a nice day!");
                    return null;
            }
        }
    }
}
