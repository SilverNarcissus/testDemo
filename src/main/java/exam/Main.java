package exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    List<Pair> list = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < n; i++) {
      String row = scanner.nextLine();
      String[] value = row.split(" ");
      System.out.println(Arrays.toString(value));

      list.add(new Pair(Integer.parseInt(value[0]), value[1]));
    }


    list.sort(new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        return Integer.compare(o1.age, o2.age);
      }
    });

    File out = new File("temp.txt");
    System.out.println(out.getAbsolutePath());

    try{
      FileWriter writer = new FileWriter(out);
      BufferedWriter writer2 = new BufferedWriter(writer);
      for(Pair pair : list){
        writer2.append(pair.toString());
        writer2.newLine();
      }
      writer2.close();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
}

class Pair{
  int age;
  String name;

  public Pair(int age, String name){
    this.age = age;
    this.name = name;
  }

  @Override
  public String toString(){
    return age + " " + name;
  }
}
