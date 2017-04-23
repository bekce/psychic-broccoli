package com.sebworks.psychicbroccoli;

/**
 * Created by seb on 23.04.2017.
 */
public class Option<T> {
    private T option;
    private String message;

    public Option(T option) {
        this.option = option;
    }

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

    @Override
    public String toString() {
        if(message != null && !message.isEmpty())
            return message;
        else return String.valueOf(option);
    }
}
