package htwb.ai.PABB;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class InputAnalyzeTest {

    InputAnalyze analyzer;

    @Test
    void analyzeSyntaxShouldNullPointerException(){
        String[] input = new String[1];
        input[0]= null;
        analyzer = new InputAnalyze(input);
        assertThrows(NullPointerException.class , () ->{analyzer.analyzeSyntax();});
        //assertEquals(false,analyzer.analyzeSyntax());

    }

    @Test
    void analyzeSyntaxTooManyShouldIllegalArgumentException(){
        String[] input = new String[2];
        input[0]= "Test";
        input[1]= "Test";
        analyzer = new InputAnalyze(input);
        assertThrows(IllegalArgumentException.class , () ->{analyzer.analyzeSyntax();});
        //assertEquals(false,analyzer.analyzeSyntax());

    }

    @Test
    void analyzeSyntaxShouldIllegalArgumentException(){
        String[] input = new String[1];
        analyzer = new InputAnalyze(input);
        assertThrows(IllegalArgumentException.class , () ->{analyzer.analyzeSyntax();});
        //assertEquals(false,analyzer.analyzeSyntax());

    }

    @Test
    void analyzeClassShouldClassNotFoundException(){
        String[] input = new String[1];
        input[0] = "Test";
        analyzer = new InputAnalyze(input);
        assertThrows(ClassNotFoundException.class, () ->{analyzer.analyzeClass();});
        assertEquals(false, analyzer.analyzeClass());
    }

    @Test
    void analyzeClassShouldInstanciationException(){
        String[] input = new String[1];
        input[0] = "Test";
        analyzer = new InputAnalyze(input);
        assertThrows(InstantiationException.class, () ->{analyzer.analyzeClass();});
        assertEquals(false, analyzer.analyzeClass());
    }

    @Test
    void analyzeClassShouldInvocationTargetException(){
        String[] input = new String[1];
        input[0] = "Test";
        analyzer = new InputAnalyze(input);
        assertThrows(InvocationTargetException.class, () ->{analyzer.analyzeClass();});
        assertEquals(false, analyzer.analyzeClass());
    }

    @Test
    void analyzeClassShouldNoSuchMethodException(){
        String[] input = new String[1];
        input[0] = "java.io.Closeable";
        analyzer = new InputAnalyze(input);
        assertThrows(NoSuchMethodException.class, () ->{analyzer.analyzeClass();});
        assertEquals(false, analyzer.analyzeClass());
    }

    @Test
    void analyzeClassShouldIllegalAccessException(){
        String[] input = new String[1];
        input[0] = "java.io.Closeable";
        analyzer = new InputAnalyze(input);
        assertThrows(IllegalAccessException.class, () ->{analyzer.analyzeClass();});
        assertEquals(false, analyzer.analyzeClass());
    }



}