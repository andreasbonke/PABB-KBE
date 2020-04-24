package htwb.ai.PABB;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class InputAnalyzeTest {


    @Test
    void analyzeSyntaxNullGivenShouldReturnFalse(){
        InputAnalyze analyzer = new InputAnalyze(null);
        assertFalse(analyzer.analyzeSyntax());
    }

    @Test
    void analyzeSyntaxTooManyArgumentsShouldReturnFalse(){
        String[] input = new String[2];
        input[0]= "Test";
        input[1]= "Test";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertFalse(analyzer.analyzeSyntax());
    }

    @Test
    void analyzeSyntaxEmptyStringShouldReturnFalse(){
        String[] input = new String[0];
        InputAnalyze analyzer = new InputAnalyze(input);
        assertFalse(analyzer.analyzeSyntax());
    }

    @Test
    void analyzeSyntaxFunctionCheckShouldReturnTrue(){
        String[] input = new String[1];
        input[0]= "Test";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertTrue(analyzer.analyzeSyntax());
    }

    @Test
    void analyzeClassShouldntBeFoundReturnFalse(){
        String[] input = new String[1];
        input[0] = "Test";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertFalse(analyzer.analyzeClass());
    }

    @Test
    void analyzeClassInstanciationExceptionReturnFalse(){
        String[] input = new String[1];
        input[0] = "java.io.InputStream";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertFalse(analyzer.analyzeClass());
    }

    //funktioniert nicht wie gewollt, darunterliegende Klasse müsste aufgerufen werden
    @Test
    void analyzeClassInvocationTargetExceptionReturnFalse(){
        String[] input = new String[1];
        input[0] = "Test";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertFalse(analyzer.analyzeClass());
    }

    //??
    @Test
    void analyzeClassNoSuchMethodException(){
        String[] input = new String[1];
        input[0] = "java.io.Closeable";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertEquals(false, analyzer.analyzeClass());
    }

    //Überflüssig
    @Test
    void analyzeClassIllegalAccessExceptionReturnFalse(){
        String[] input = new String[1];
        input[0] = "htwb.ai.TestClassIllegalAccess";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertFalse(analyzer.analyzeClass());
    }

    @Test
    void analyzeClassRightClassReturnTrue(){
        String[] input = new String[1];
        input[0] = "htwb.ai.TestClass";
        InputAnalyze analyzer = new InputAnalyze(input);
        assertTrue(analyzer.analyzeClass());
    }



}