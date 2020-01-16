import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by SilverNarcissus on 2019/9/18.
 */
public class BuildInsert {

  private static String pattern = "insert into root.vehicle.%s(timestamp,s0,s1,s2,s3,s4) values(%d, %d, %d, %f, \"%s\", %b); \n";

  public static void main(String[] args) throws IOException {
    File f = new File("insert_one_device.txt");
    FileWriter fileWriter = new FileWriter(f);
    for (int i = 0; i < 100000; i++) {
      fileWriter.write(String.format(pattern,"d0", i, i, i, (float)i, "str " + i, (i & 1) == 1));
    }
//    for (int i = 0; i < 100000; i++) {
//      fileWriter.write(String.format(pattern,"d1", i, i, i, (float)i, "str " + i, (i & 1) == 1));
//    }
//    for (int i = 0; i < 100000; i++) {
//      fileWriter.write(String.format(pattern,"d2", i, i, i, (float)i, "str " + i, (i & 1) == 1));
//    }
    fileWriter.close();
  }

}
