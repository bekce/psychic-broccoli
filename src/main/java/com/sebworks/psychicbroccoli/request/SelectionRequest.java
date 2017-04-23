package com.sebworks.psychicbroccoli.request;

import com.sebworks.psychicbroccoli.Option;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Presents some options to the user and asks to select one
 * Created by seb on 23.04.2017.
 */
public class SelectionRequest<T> implements Request<T> {

    private List<Option<T>> options;
    private String infoMessage;

    public SelectionRequest(String infoMessage, Option<T>... options) {
        this.options = Arrays.asList(options);
        this.infoMessage = infoMessage;
    }

    public List<Option<T>> getOptions() {
        return options;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    @Override
    public T ask(){
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (infoMessage != null && infoMessage.length() > 0)
                    System.out.println(infoMessage);
                for (int i = 0; i < options.size(); i++) {
                    Option<T> option = options.get(i);
                    System.out.println((i + 1) + ". " + option.getMessage());
                }
                System.out.printf("Choose an option (%d-%d): ", 1, options.size());
                String scan = scanner.next();
                try {
                    int opt = Integer.parseInt(scan);
                    if (opt > 0 && opt <= options.size()) {
                        return options.get(opt - 1).getOption();
                    }
                } catch (NumberFormatException ignored) {
                }
                System.out.println("Bad option, please try again");
            }
        }
    }
}
