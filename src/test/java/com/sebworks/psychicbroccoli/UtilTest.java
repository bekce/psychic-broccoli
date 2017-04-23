package com.sebworks.psychicbroccoli;

import com.sebworks.psychicbroccoli.common.Character;
import com.sebworks.psychicbroccoli.util.SaveLoadUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by seb on 23.04.2017.
 */
public class UtilTest {
    private Character character;
    private SaveLoadUtil saveLoadUtil;

    @Before
    public void setUp() throws Exception {
        character = new Character("name");
        saveLoadUtil = new SaveLoadUtil("test-tmp");
    }

    @After
    public void tearDown() throws Exception {
        deleteDir(new File("test-tmp"));
    }

    private void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    @Test
    public void save() throws Exception {
        saveLoadUtil.save(character);
        List<Character> load = saveLoadUtil.load();
        Assert.assertEquals(1, load.size());
        Assert.assertEquals(character, load.get(0));
    }
}
