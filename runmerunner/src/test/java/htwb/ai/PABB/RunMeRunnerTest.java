package htwb.ai.PABB;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunMeRunnerTest {
    RunMeRunner rmr;

    @Test
    void containsRunMeMethodsTest() {
        rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
        List<Method> result = rmr.getRunMeMethods();

        List<String> expected = new ArrayList<>();
        expected.add("testOne");
        expected.add("testMethod1");
        expected.add("testMethod2");
        expected.add("testMethod4");

        List<String> rs = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {

            rs.add(result.get(i).getName());
        }

        assertTrue(rs.contains(expected));
    }

    @Test
    void containsNORunMeMethodsTest() {
        rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
        List<Method> result = rmr.getRunMeNOTMethods();

        List<String> expected = new ArrayList<>();
        expected.add("testMethod1NoRunME");
        expected.add("testMethod3");
        expected.add("testEleven");

        List<String> rs = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {

            rs.add(result.get(i).getName());
        }

        assertTrue(rs.contains(expected));
    }


    @Test
    void invokeRunMeANDGetNOTinvokeMethodsTest() {
        rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
        List<Method> result = rmr.getNotInvoked();

        List<String> expected = new ArrayList<>();
        expected.add("testOne");
        expected.add("testMethod2");
        expected.add("testThree");

        List<String> rs = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {

            rs.add(result.get(i).getName());
        }

        assertTrue(rs.contains(expected));
    }


}