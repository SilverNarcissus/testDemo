import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by SilverNarcissus on 2017/3/30.
 */
public class forDating {
    public static void main(String[] args) {
        //output();
        File file =new File("../TestDemo/data");
        DataClass dataClass=new DataClass("没有读取对象哟~");
        //
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
            dataClass=(DataClass) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //
        Method m=null;
        try {
            m=DataClass.class.getMethod("getData",null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //
        try {
            System.out.println(m.invoke(dataClass));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void output(){
        File file=new File("../TestDemo/data");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
            DataClass dataClass=new DataClass("传播 会计");
            objectOutputStream.writeObject(dataClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
