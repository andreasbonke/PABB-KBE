package htwb.ai.PABB;

import htwb.ai.RunMe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RunMeRunner {

    private String className;
    private Class<?> clazz;
    private Method[] methods;
    private Object testObject;
    private List<Method> runMeMethods;
    private List<Method> runMeNOTMethods;
    private List<Method> notInvoked;


    public RunMeRunner(String className) {
        this.className = className;
    }

    public boolean checkTheClass() {

        try {

            System.out.println("Analyzed class: " + className);
            clazz = Class.forName(className);

            methods = clazz.getDeclaredMethods();
            testObject = clazz.getDeclaredConstructor().newInstance();


            System.out.println("Methods without @RunMe: ");
            runMeNOTMethods = getNORunMe(methods);

            System.out.println("Methods with @RunMe: ");
            runMeMethods = getRunMe(methods);
            System.out.println("not invokable: ");
            notInvoked = invokeRunMeANDGetNOTinvoke(runMeMethods);
            return true;

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            //e.printStackTrace();
            return false;
        }
    }


    public List<Method> getNORunMe(Method[] methods) {

        List<Method> runNOTMeMethods = new ArrayList<>();

        for (Method m : methods) {
            if (m.isSynthetic()) {
                continue;
            }
            if (m.getAnnotation(RunMe.class) == null) {
                runNOTMeMethods.add(m);
                System.out.println(m.getName());
            }
        }

        return runNOTMeMethods;
    }


    public List<Method> getRunMe(Method[] methods) {

        List<Method> runMeMethods = new ArrayList<>();

        for (Method m : methods) {
            if (m.isSynthetic()) {
                continue;
            }
            if (m.getAnnotation(RunMe.class) != null) {
                runMeMethods.add(m);
                System.out.println(m.getName());
            }
        }

        return runMeMethods;
    }

    public List<Method> invokeRunMeANDGetNOTinvoke(List<Method> methods) {

        List<Method> notInvokableList = new ArrayList<>();

        for (Method m : methods) {
            if (m.isSynthetic()) {
                continue;
            }
            try {
                m.invoke(testObject);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                notInvokableList.add(m);
                System.out.println(m.getName()+": "+ e.getClass().getSimpleName());
            }
        }
        return notInvokableList;
    }

    public List<Method> getRunMeMethods() {
        return runMeMethods;
    }

    public List<Method> getRunMeNOTMethods() {
        return runMeNOTMethods;
    }

    public List<Method> getNotInvoked() {
        return notInvoked;
    }
}
