package com.sebworks.psychicbroccoli.util;

import com.sebworks.psychicbroccoli.common.Character;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for save/load character files
 * Created by seb on 23.04.2017.
 */
public class SaveLoadUtil {

    @SuppressWarnings("unchecked")
    public static List<Character> load() {
        File saveFile = getSaveFile();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))){
            return (List<Character>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void save(Character character) throws IOException {
        List<Character> list = load();
        if(list.contains(character)){
            list.remove(character);
        }
        list.add(character);
        File saveFile = getSaveFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            oos.writeObject(list);
        }
    }

    private static File getSaveFile() {
        File folder = new File(System.getProperty("user.home"), ".psychic-broccoli");
        folder.mkdirs();
        return new File(folder, "save.dat");
    }
}
