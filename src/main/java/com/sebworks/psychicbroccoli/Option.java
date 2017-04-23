package com.sebworks.psychicbroccoli;

/**
 * Created by seb on 23.04.2017.
 */
public class Option<T> {
    private T option;
    private String message;

    public Option(T option, String message) {
        this.option = option;
        this.message = message;
    }

    public T getOption() {
        return option;
    }

    public String getMessage() {
        return message;
    }

}
