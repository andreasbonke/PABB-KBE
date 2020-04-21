package htwb.ai;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunMeRunnerTest {


    @Test
    public void firstTest() {
        RunMeRunner rmr = new RunMeRunner("TestClass");
        rmr.checkTheClass();
        //assertTrue(true);
        int i = 5;
        int x = 6;
        assertEquals(11, x);
    }

    @Test
    void checkTheClass() {
    }
}