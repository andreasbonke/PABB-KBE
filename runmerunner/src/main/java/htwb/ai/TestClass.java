package htwb.ai;

import java.util.NoSuchElementException;

public class TestClass {

    @RunMe
    public void testMethod1() {
    }

    public void testMethod1NoRunME() {
    }

    @RunMe
    public void testMethod2(String str) {
    }

    public String testMethod3() {
        return "";
    }

    @RunMe
    public int testMethod4() {
        return 0;
    }

    @RunMe
    public String testMethod5(int x, String y) {
        return "";
    }

    @RunMe
    public boolean testMethod6() {
        return true;
    }

    @RunMe
    public String testMethod7(int x, String y) {
        return "";
    }

    @RunMe
    public boolean testMethod8() {
        throw new NullPointerException();
    }

    @RunMe
    public boolean testMethod9() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @RunMe
    public boolean testMethod10() throws InstantiationException {
        throw new InstantiationException();
    }

    @RunMe
    public boolean testMethod11() {
        throw new NoSuchElementException();
    }

    @RunMe
    protected boolean testMethod12() {
        return true;
    }



}
