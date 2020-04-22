package htwb.ai.PABB;

public class InputAnalyze {

    String[] input;

    public InputAnalyze(String[] input) {
        this.input = input;
    }

    public boolean analyze() {
        if(input == null){
            return false;
        }
        if(input.length < 1){
            return false;
        }
        return true;
    }
}
