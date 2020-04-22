package htwb.ai.PABB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunMeRunnerTest {
    private RunMeRunner rmr;

    @Test
    void correctClassNameTest(){
        rmr = new RunMeRunner("htwb.ai.TestClass");
        //rmr = new RunMeRunner("java.lang.Number");
        rmr.checkTheClass();
    }

    @Test
    void interfaceClassTest(){
        rmr = new RunMeRunner("java.io.Closeable");
        rmr.checkTheClass();
    }

    @Test
    void abstractClassTest(){
        rmr = new RunMeRunner("java.lang.reflect.Executable");
        rmr.checkTheClass();
    }

    @Test
    void dontExistClassTest(){
        rmr = new RunMeRunner("htw.ai.FailTestClass");
        rmr.checkTheClass();
    }

    @Test
    void nullClassNameTest(){
        rmr = new RunMeRunner(null);
        rmr.checkTheClass();
    }

}