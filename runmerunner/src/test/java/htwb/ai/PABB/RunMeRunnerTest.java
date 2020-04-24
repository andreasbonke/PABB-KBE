package htwb.ai.PABB;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunMeRunnerTest {


    @Test
    void containsRunMeMethodsTest() {
        RunMeRunner rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
        List<Method> result = rmr.getRunMeMethods();

        List<String> expected = new ArrayList<>();
        expected.add("testMethod1");
        expected.add("testMethod2");
        expected.add("testMethod4");
        expected.add("testMethod5");
        expected.add("testMethod6");
        expected.add("testMethod7");
        expected.add("testMethod8");
        expected.add("testMethod9");
        expected.add("testMethod10");
        expected.add("testMethod11");
        expected.add("testMethod12");

        List<String> resultOnlyNames = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            resultOnlyNames.add(result.get(i).getName());
        }

        resultOnlyNames.sort(Comparator.comparing(String::toString));
        expected.sort(Comparator.comparing(String::toString));

        assertEquals(expected, resultOnlyNames);
        // assertEquals(expected.size(),resultOnlyNames.size());
    }

   @Test
    void containsNORunMeMethodsTest() {
        RunMeRunner rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
        List<Method> result = rmr.getRunMeNOTMethods();

        List<String> expected = new ArrayList<>();
        expected.add("testMethod3");
        expected.add("testMethod1NoRunME");

        List<String> resultOnlyNames = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            resultOnlyNames.add(result.get(i).getName());
        }
        resultOnlyNames.sort(Comparator.comparing(String::toString));
        expected.sort(Comparator.comparing(String::toString));

        assertEquals(expected, resultOnlyNames);
    }

    @Test
    void containsNORunMeMethodsTestSizeComparisation() {
        RunMeRunner rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
        List<Method> result = rmr.getRunMeNOTMethods();
        assertEquals(2, result.size());
    }


    @Test
    void invokeRunMeANDGetNOTinvokeMethodsTest() {
        RunMeRunner rmr = new RunMeRunner("htwb.ai.TestClass");
        rmr.checkTheClass();
        List<Method> result = rmr.getNotInvoked();

        List<String> expected = new ArrayList<>();
        expected.add("testMethod2");
        expected.add("testMethod5");
        expected.add("testMethod7");
        expected.add("testMethod8");
        expected.add("testMethod9");
        expected.add("testMethod10");
        expected.add("testMethod11");
        expected.add("testMethod12");


        List<String> resultOnlyNames = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            resultOnlyNames.add(result.get(i).getName());
        }

        resultOnlyNames.sort(Comparator.comparing(String::toString));
        expected.sort(Comparator.comparing(String::toString));

        assertEquals(expected, resultOnlyNames);
        //assertEquals(expected.size(),resultOnlyNames.size());
    }


}