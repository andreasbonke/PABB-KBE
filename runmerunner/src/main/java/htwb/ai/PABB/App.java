package htwb.ai.PABB;

public class App {
    public static void main(String[] args) {

        InputAnalyze ia = new InputAnalyze(args);

        if (ia.analyzeSyntax() && ia.analyzeClass()) {
            RunMeRunner rmr = new RunMeRunner(args[0]);
            rmr.checkTheClass();
        }else{
            System.exit(-1);
        }
    }
}
