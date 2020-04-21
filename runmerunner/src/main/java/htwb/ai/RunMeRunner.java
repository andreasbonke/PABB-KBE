package htwb.ai;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunMeRunner {

    private String className;
    private Class<?> clazz;
    private Method[] methods;

    public RunMeRunner(String className) {
        this.className = className;
    }

    public void checkTheClass() {

        try {

            System.out.println("Analyzed class: " + className);
            clazz = Class.forName(className);
            methods = clazz.getDeclaredMethods();


            System.out.println("Methods without @RunMe: ");
            getNORunMe(methods);
            System.out.println("Methods with @RunMe: ");
            getRunMe(methods);
            System.out.println("not invokable: ");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public List<String> getNORunMe(Method[] methods){

        List<String> runNOTMeMethods = new ArrayList<>();

        for(Method m : methods){
            if (m.getAnnotation(RunMe.class) == null) {
                runNOTMeMethods.add(m.getName());
                System.out.println(m.getName());
            }
        }

        return runNOTMeMethods;
    }

    public List<String> getRunMe(Method[] methods){

        List<String> runMeMethods = new ArrayList<>();

        for(Method m : methods){
            if (m.getAnnotation(RunMe.class) != null) {
                runMeMethods.add(m.getName());
                System.out.println(m.getName());
            }
        }

        return runMeMethods;
    }
}
