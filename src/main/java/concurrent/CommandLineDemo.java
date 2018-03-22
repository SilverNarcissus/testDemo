package concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by SilverNarcissus on 2017/3/22.
 */
public class CommandLineDemo {
        static Future<Double> f;
        static BlockingQueue<Command> b;
        static ExecutorService e;


        public static void main(String[] args) {
            e= Executors.newCachedThreadPool();
            b = new ArrayBlockingQueue<Command>(10);
            e.execute(new dispatcher());

            CommandLineDemo demo = new CommandLineDemo();
            demo.run();
        }

        public void run() {
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

            while(true){
                String command;
                try {
                    command=reader.readLine();

                    if(command.equals("1")){
                        b.put(new Command());
                    }
                    else {
                        break;
                    }
                } catch (IOException | InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
            System.out.println("Done!");
        }

        static class dispatcher implements Runnable {

            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("dispatcher ok!");
                        System.out.println("size:"+b.size());
                        e.execute(b.take());

                        System.out.println("dispatcher free");
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }



}
