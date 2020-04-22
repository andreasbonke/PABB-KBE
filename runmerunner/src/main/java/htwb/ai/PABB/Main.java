package htwb.ai.PABB;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws Exception {
        InputAnalyze ia = new InputAnalyze(args);
        if (ia.analyzeSyntax() && ia.analyzeClass()) {
            RunMeRunner rmr = new RunMeRunner(args.toString());
        }else{
            throw new Exception("An unknown error occurred x0001");
        }
    }
}
