package htwb.ai.PABB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunMeRunnerTest {

    @Test
    void correctOutput(){
        RunMeRunner rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
    }

}