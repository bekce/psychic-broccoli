package com.sebworks.psychicbroccoli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Created by seb on 23.04.2017.
 */
public class StageTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void welcome() throws Exception {
        systemInMock.provideLines("1","bekce-test","1","1","1","1","1","1","1","1","1","1","1","1","1","2","1","1","2","1","1","1","1","2","1","1","1","2","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","4","3");
        new Application().start();
    }
}
