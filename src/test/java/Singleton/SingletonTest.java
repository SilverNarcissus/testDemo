package Singleton;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/11/26.
 */
public class SingletonTest {
    Printer printer1 = Singleton.INSTANCE;
    Printer printer2 = Singleton.INSTANCE;

    @Test
    public void SerializableTest() throws Exception {
            File file = new File("singletonEnum");
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try {

                oos = new ObjectOutputStream(new FileOutputStream(file));
                OddSingleton singleton = OddSingleton.getInstance();
                oos.writeObject(singleton);
                oos.close();
                ois = new ObjectInputStream(new FileInputStream(file));
                OddSingleton singleton2 = (OddSingleton) ois.readObject();
                System.out.println(singleton == singleton2);//true

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    }
    @Test
    public void singleTest(){
        assertEquals(true,printer1==printer2);
    }

}