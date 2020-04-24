package htwb.ai.PABB;

import java.lang.reflect.InvocationTargetException;

public class InputAnalyze {


    private String[] input;

    public InputAnalyze(String[] input) {
        this.input = input;
    }

    public boolean analyzeSyntax() {
        String newLine = System.getProperty("line.separator");

        if(input == null){
            //throw new NullPointerException("Error: No classname \r\n Usage: java -jar runmerunner-PABB-KBE.jar className");
            System.err.println("Error: No classname" + newLine + "Usage: java -jar runmerunner-PABB-KBE.jar className ");
            return false;
        }
        if(input.length > 1){
            //throw new IllegalArgumentException("Error: too many arguments \r\n Usage: java -jar runmerunner-PABB-KBE.jar className");
            System.err.println("Error: too many arguments" + newLine + "Usage: java -jar runmerunner-PABB-KBE.jar className");
            return false;
        }
        if(input.length <= 0){
            //throw new IllegalArgumentException("Error: not enough arguments \r\n Usage: java -jar runmerunner-PABB-KBE.jar className");
            System.err.println("Error: not enough arguments " + newLine + "Usage: java -jar runmerunner-PABB-KBE.jar className");
            return false;
        }
        return true;
    }

    public boolean analyzeClass() {

        try {
            Class<?> clazz = Class.forName(input[0]);
            clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Error: could not find class: " + input[0]);
            return false;
        } catch (InstantiationException e) {
            System.err.println("Error: could not instantiate class: " + input[0]);
            return false;
        } catch (InvocationTargetException e) {
            System.err.println("Error: invocation target:  " + input[0]);
            return false;
        } catch (NoSuchMethodException e) {
            System.err.println("Error: No Such Method: " + input[0]);
            return false;
        } catch (IllegalAccessException e) {
            System.err.println("Error: Illegal Access: " + input[0]);
            return false;
        }

        return true;
    }
}
