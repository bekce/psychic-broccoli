package com.sebworks.psychicbroccoli.request;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Asks the user for an input line
 * Created by seb on 23.04.2017.
 */
public class InputRequest implements Request<String> {

    private String infoMessage;
    private Pattern validRegexPattern;

    public InputRequest(String infoMessage, Pattern validRegexPattern) {
        this.infoMessage = infoMessage;
        this.validRegexPattern = validRegexPattern;
    }

    public InputRequest(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    @Override
    public String ask() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (infoMessage != null && infoMessage.length() > 0)
                    System.out.println(infoMessage);
                String scan = scanner.nextLine();
                if(validRegexPattern != null){
                    Matcher matcher = validRegexPattern.matcher(scan);
                    if(matcher.find()){
                       return scan;
                    }
                } else {
                    return scan;
                }
                System.out.println("Bad entry, please try again");
            }
        }
    }
}
