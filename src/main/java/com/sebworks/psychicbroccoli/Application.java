package com.sebworks.psychicbroccoli;

import com.sebworks.psychicbroccoli.request.InputRequest;
import com.sebworks.psychicbroccoli.request.SelectionRequest;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.sebworks.psychicbroccoli.Application.CreateLoadOption.LOAD;
import static sun.plugin.javascript.navig.JSType.Option;


/**
 * Created by bekce on 4/21/17.
 */
public class Application {

    public enum CreateLoadOption {
        CREATE, LOAD
    }
    private Pattern characterNamePattern = Pattern.compile("^[a-zA-Z0-9_ -]{3,20}$");
    private void println(String msg){
        System.out.println(msg);
    }
    private void print(String msg){
        System.out.print(msg);
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        Random rng = new Random();
        Character character;
        while(true){
            println("Welcome to psychic broccoli");
            switch (new SelectionRequest<CreateLoadOption>()
                    .addOption(new Option<>(CreateLoadOption.CREATE, "Create a character"))
                    .addOption(new Option<>(LOAD, "Load a character")).ask()) {
                case CREATE:
                    String name = new InputRequest("Enter character name", characterNamePattern).ask();
                    character = new Character(name);
                    println(name+" embarks upon a new adventure!");
                    break;
                case LOAD:
                    List<Character> characters = SaveLoadUtil.load();
                    character = new SelectionRequest<Character>("Select a character to load").setOptionValues(characters).ask();
                    break;
            }

        }
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
