package myUtil.Annotation;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by SilverNarcissus on 2017/1/26.
 * version 1.0 on2017/1/26.
 */
public class RunTestForAnnotation {
    public static void main(String[] args) {

        //check all methods in assigned class
        for(Method m:RunTestForAnnotation.class.getDeclaredMethods()){
            //select methods which has annotation
            if(m.isAnnotationPresent(TestAnnotation.class)){
                try {
                    m.invoke(null);
                    //do things you want
                } catch (IllegalAccessException e) {
                    //access exception,do something
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    //method throw exception，do something
                    e.printStackTrace();
                }
            }
        }

    }

    @TestAnnotation
    void f1(){
    }

    @TestAnnotation
    void f2(){

    }
}
