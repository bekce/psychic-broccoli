package com.sebworks.psychicbroccoli;

import com.sebworks.psychicbroccoli.request.InputRequest;
import com.sebworks.psychicbroccoli.request.SelectionRequest;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


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
        while(true){
            println("Welcome to psychic broccoli");
            switch (new SelectionRequest<>(null, new Option<>(CreateLoadOption.CREATE, "Create a character"),
                    new Option<>(CreateLoadOption.LOAD, "Load a character")).ask()) {
                case CREATE:
                    String name = new InputRequest("Enter character name", characterNamePattern).ask();

                    break;
                case LOAD:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
