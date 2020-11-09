package bytedance;

import java.util.HashMap;
import java.util.Scanner;

public class Q2 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();

    HashMap<Integer, Integer> maps[] = new HashMap[32];
    for (int i = 0; i < 32; i++) {
      maps[i] = new HashMap<>();
    }

    for (int i = 0; i < n; i++) {
      int id = in.nextInt();
      String[] part = in.next().split("/");
      int ip = toIp(part[0]);
      int mask = Integer.parseInt(part[1]);

      maps[mask].put(ip, id);
    }

    OUTER:
    for (int i = 0; i < m; i++) {
      int cur = toIp(in.next());
      for (int j = 1; j < 32; j++) {
        int key = cur & (-(1 << j));
        Integer id = maps[32 - j].get(key);
        if (id != null) {
          System.out.println(id);
          continue OUTER;
        }
      }

      System.out.println(-1);
    }
  }

  private static int toIp(String ip) {
    int res = 0;
    String[] part = ip.split("\\.");
    for (int i = 0; i < 4; i++) {
      res |= (Integer.parseInt(part[i]) << (24 - 8 * i));
    }

    return res;
  }
}

