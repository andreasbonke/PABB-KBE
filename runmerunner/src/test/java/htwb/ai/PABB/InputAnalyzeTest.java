package htwb.ai.PABB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputAnalyzeTest {

    InputAnalyze analyzer;

    @Test
    void nullInputShouldReturnFalse(){
        analyzer = new InputAnalyze(null);
        assertEquals(false,analyzer.analyzeClass());
    }

}