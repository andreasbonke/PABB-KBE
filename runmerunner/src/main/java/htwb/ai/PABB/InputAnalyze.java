package htwb.ai.PABB;

import java.lang.reflect.InvocationTargetException;

public class InputAnalyze {

    String[] input;

    public InputAnalyze(String[] input) {
        this.input = input;
    }

    public boolean analyzeSyntax() throws NullPointerException,IllegalArgumentException {
        if(input == null){
            throw new NullPointerException("Error: No classname \r\n Usage: java -jar runmerunner-PABB-KBE.jar className");
        }
        if(input.length > 1){
            throw new IllegalArgumentException("Error: too many arguments \r\n Usage: java -jar runmerunner-PABB-KBE.jar className");
        }
        if(input.length <= 0){
            throw new IllegalArgumentException("Error: not enough arguments \r\n Usage: java -jar runmerunner-PABB-KBE.jar className");
        }
        return true;
    }

    public boolean analyzeClass() {

        try {
            Class<?> clazz = Class.forName(input[0]);
            clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Error: could not find class: " + input[0]);
            return false;
        } catch (InstantiationException e) {
            System.out.println("Error: could not instantiate class: " + input[0]);
            return false;
        } catch (InvocationTargetException e) {
            System.out.println("Error: invocation target:  " + input[0]);
            return false;
        } catch (NoSuchMethodException e) {
            System.out.println("Error: No Such Method: " + input[0]);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println("Error: Illegal Access: " + input[0]);
            return false;
        }

        return true;
    }
}
