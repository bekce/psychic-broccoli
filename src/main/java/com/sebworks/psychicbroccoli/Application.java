package com.sebworks.psychicbroccoli;

import com.sebworks.psychicbroccoli.state.State;
import com.sebworks.psychicbroccoli.state.WelcomeState;

/**
 * Created by bekce on 4/21/17.
 */
public class Application {

    public void start() {
        State state = new WelcomeState();
        while (state != null) {
            state = state.outcome();
        }
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
