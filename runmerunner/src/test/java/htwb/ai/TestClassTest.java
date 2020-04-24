package htwb.ai;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

//General Function Tests, just that everything works
class TestClassTest {

    @Test
    void testMethod3FunctionTestResultString() {
        TestClass testClass = new TestClass();
        assertEquals("",testClass.testMethod3());
    }

    @Test
    void testMethod4FunctionTestResultInt() {
        TestClass testClass = new TestClass();
        assertEquals(0,testClass.testMethod4());
    }

    @Test
    void testMethod5FunctionTestResultInt() {
        TestClass testClass = new TestClass();
        assertEquals("",testClass.testMethod5(0,"hallo"));
    }

    @Test
    void testMethod6FunctionTestResultBoolean() {
        TestClass testClass = new TestClass();
        assertEquals(true,testClass.testMethod6());
    }

    @Test
    void testMethod7FunctionTestResultString() {
        TestClass testClass = new TestClass();
        assertEquals("",testClass.testMethod7(0,"hallo"));
    }

    @Test
    void testMethod8CheckThrow() {
        TestClass testClass = new TestClass();
        Assertions.assertThrows(NullPointerException.class, () -> testClass.testMethod8());
    }

    @Test
    void testMethod9CheckThrow() {
        TestClass testClass = new TestClass();
        Assertions.assertThrows(IllegalAccessException.class, () -> testClass.testMethod9());
    }

    @Test
    void testMethod10CheckThrow() {
        TestClass testClass = new TestClass();
        Assertions.assertThrows(InstantiationException.class, () -> testClass.testMethod10());
    }

    @Test
    void testMethod11CheckThrow() {
        TestClass testClass = new TestClass();
        Assertions.assertThrows(NoSuchElementException.class, () -> testClass.testMethod11());
    }

    @Test
    void testMethod12FunctionTestResultTrue() {
        TestClass testClass = new TestClass();
        assertTrue(testClass.testMethod12());
    }
}