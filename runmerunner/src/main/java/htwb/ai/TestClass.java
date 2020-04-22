package htwb.ai;

import java.util.NoSuchElementException;

public class TestClass {

    @RunMe
    public void testMethod1() {
        //System.out.println("M1 TestClass");
    }

    public void testMethod1NoRunME() {
        //System.out.println("testM void, no RunMe");
    }

    @RunMe
    public void testMethod2(String str) {
        //System.out.println("M2 void, param String");
    }

    public String testMethod3() {
       // System.out.println("In M3() return String, no RunMe");
        return " ";
    }

    @RunMe
    public int testMethod4() {
        //System.out.println("M4 TestClass, param Int");
        return 0;
    }

    @RunMe
    public String testOne(int x, String y) {
        return "One";
    }

    @RunMe
    public int testTwo() {
        return 2;
    }

    @RunMe
    private void testThree() {}

    @RunMe
    public boolean testFour() {
        return true;
    }

    @RunMe
    public boolean testFive() {
        return false;
    }

    @RunMe
    public boolean testSix(int x) {
        return false;
    }

    @RunMe
    public String testSeven(int x, String y) {
        return "One";
    }

    @RunMe
    public boolean testEight() {
        throw new NullPointerException("Haha");
    }

    @RunMe
    public boolean testNine() throws IllegalAccessException {
        throw new IllegalAccessException("");
    }

    @RunMe
    public boolean testTen() throws InstantiationException {
        throw new InstantiationException("Haha");
    }

    //@RunMe
    public boolean testEleven() {
        throw new NoSuchElementException("Jo");
    }

    @RunMe
    protected boolean testTwelve() {
        return true;
    }

}
