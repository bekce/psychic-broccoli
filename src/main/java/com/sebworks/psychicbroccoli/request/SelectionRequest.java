package com.sebworks.psychicbroccoli.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Presents some options to the user and asks to select one
 * Created by seb on 23.04.2017.
 */
public class SelectionRequest<T> implements Request<T> {

    private List<Option<T>> options = new ArrayList<>();
    private String infoMessage;

    public SelectionRequest() {
    }

    public SelectionRequest(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public SelectionRequest<T> setOptions(Option<T>... options){
        this.options = Arrays.asList(options);
        return this;
    }

    public SelectionRequest<T> setOptions(List<Option<T>> options){
        this.options = options;
        return this;
    }
    public SelectionRequest<T> addOption(Option<T> option){
        this.options.add(option);
        return this;
    }

    public SelectionRequest<T> setOptionValues(List<T> values){
        this.options = values.stream().map(Option::new).collect(Collectors.toList());
        return this;
    }

    public List<Option<T>> getOptions() {
        return options;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    @Override
    public T ask(){
        if(options == null || options.isEmpty()){
            throw new IllegalStateException("options is null");
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (infoMessage != null && infoMessage.length() > 0)
                System.out.println(infoMessage);
            for (int i = 0; i < options.size(); i++) {
                Option<T> option = options.get(i);
                System.out.println((i + 1) + ". " + option.toString());
            }
            System.out.printf("Choose an option (%d-%d): ", 1, options.size());
            String scan = scanner.nextLine();
//            System.out.println();
            try {
                int opt = Integer.parseInt(scan);
                if (opt > 0 && opt <= options.size()) {
                    return options.get(opt - 1).getOption();
                }
            } catch (NumberFormatException ignored) {
            }
            System.err.println("Bad option, please try again");
        }
    }
}
