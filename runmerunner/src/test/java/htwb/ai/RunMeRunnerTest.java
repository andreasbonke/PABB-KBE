package htwb.ai;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunMeRunnerTest {

    RunMeRunner rmr = new RunMeRunner("TestClass");

    @Test
    public void firstTest() {

       // rmr.checkTheClass();
        //assertTrue(true);
        int i = 5;
        int x = 6;
        assertEquals(6, x);
    }

    @Test
    void checkTheClass() {
    }

    @Test
    void testCheckTheClass() {
    }

    @Test
    void getNORunMe() {
    }

    @Test
    void getRunMe() {
    }
}