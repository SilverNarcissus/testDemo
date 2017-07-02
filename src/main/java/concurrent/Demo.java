package concurrent;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;

/**
 * Created by SilverNarcissus on 2017/3/13.
 */
public class Demo implements Runnable{
    static Future<Double> f;
    static BlockingQueue<Command> b;
    static ExecutorService e;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton("GO!");

    public static void main(String[] args) {
        e=Executors.newCachedThreadPool();
        b = new ArrayBlockingQueue<Command>(10);
        e.execute(new dispatcher());

        Demo demo = new Demo();
        e.execute(demo);
    }

    public void run() {
        JButton but = new JButton();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("press!");
//                //single
//                double result = 0;
//                for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                    result += Math.sqrt(i);
//                }
//
//                System.out.println("Calculate done!" + result);
//                System.out.println("UI free!");
//                System.err.println(Thread.activeCount());
                //multi
                try {
                    b.put(new Command());
                    System.out.println("UI free!");
                    System.err.println(Thread.activeCount());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                but.setBackground(Color.blue);
                try {
                    b.put(new Command2());
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                but.setBackground(Color.white);
            }
        });

        System.out.println("Here!");
        panel.add(button);
        panel.add(but);
        panel.setVisible(true);
        panel.setSize(300, 300);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(300, 300);
    }

    static class dispatcher implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("dispatcher ok!");
                    System.out.println("size:"+b.size());
                    e.execute(b.take());

                    Future<String> result=e.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "";
                        }
                    });

                    System.out.println("dispatcher free");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    static class printer implements Runnable{
        Future<Double> f;

        public printer(Future<Double> f){
            this.f=f;
        }

        @Override
        public void run() {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        }
    }
}
