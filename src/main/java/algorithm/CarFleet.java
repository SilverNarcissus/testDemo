package algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class CarFleet {

  public static void main(String[] args) {
    CarFleet carFleet = new CarFleet();
    int[][] input = {{1, 2}, {2, 1}, {4, 3}, {7, 2}};
    int[][] input2 = {{3, 4}, {5, 4}, {6, 3}, {9, 1}};
    int[][] input3 = {{1,4},{4,5},{7,1},{13,4},{14,3},{15,2},{16,5},{19,1}};
    System.out.println(Arrays.toString(carFleet.getCollisionTimes(input3)));
  }

  public double[] getCollisionTimes(int[][] cars) {
    PriorityQueue<Data> queue = new PriorityQueue<>(new Comparator<Data>() {
      public int compare(Data d1, Data d2) {
        return Double.compare(d1.eventTime, d2.eventTime);
      }
    });
    int n = cars.length;

    TreeMap<Integer, Data> dataTreeMap = new TreeMap<>();
    for (int i = 0; i < n - 1; i++) {
      int speed1 = cars[i][1];
      int speed2 = cars[i + 1][1];
      double dis = cars[i + 1][0] - cars[i][0];
      double beforeTime = 0;
      Data data = new Data(speed1, speed2, dis, beforeTime, i, i + 1, 0);
      dataTreeMap.put(i, data);
      queue.add(data);
    }

    int[] versions = new int[n];
    double[] res = new double[n];
    int[] end2Start = new int[n];
    for (int i = 0; i < n; i++) {
      end2Start[i] = i;
    }

    Arrays.fill(res, -1);

    while (!queue.isEmpty()) {
      Data cur = queue.poll();
      System.out.println(cur);
      if (cur.eventTime == Double.MAX_VALUE) {
        break;
      }
      if (cur.version < versions[cur.end]) {
        continue;
      }

      res[cur.end - 1] = cur.eventTime;
      int newSpeed = cur.speed2;
      double now = cur.eventTime;

      Map.Entry<Integer, Data> beforeEntry = dataTreeMap.lowerEntry(cur.start);
      if (beforeEntry != null) {
        Data before = beforeEntry.getValue();
        int speed1 = before.speed1;
        int speed2 = newSpeed;
        double dis = before.dis;
        double time = now - before.beforeTime;
        double beforeTime = now;


        dis -= (before.speed1 - before.speed2) * time;

        int version = ++versions[cur.start];

        Data newBefore = new Data(speed1, speed2, dis, beforeTime, before.start, cur.start,
            version);
        dataTreeMap.put(before.start, newBefore);
        queue.add(newBefore);
      }

      Map.Entry<Integer, Data> afterEntry = dataTreeMap.ceilingEntry(cur.end);
      if (afterEntry != null) {
        Data after = afterEntry.getValue();
        int speed1 = Math.min(after.speed1, newSpeed);
        int speed2 = after.speed2;
        double dis = after.dis;
        double time = now - after.beforeTime;
        double beforeTime = now;

        dis -= (after.speed1 - after.speed2) * time;

        int version = ++versions[after.end];
        Data newAfter = new Data(speed1, speed2, dis, beforeTime, cur.start, after.end, version);
        dataTreeMap.remove(after.start);
        dataTreeMap.put(cur.start, newAfter);
        queue.add(newAfter);
      }
    }

    return res;
  }
}

class Data {

  double eventTime = 0;

  int speed1 = 0;
  int speed2 = 0;
  double dis = 0;
  double beforeTime = 0;

  int start = 0;
  int end = 0;

  int version = 0;

  public Data(int speed1, int speed2, double dis, double beforeTime, int start, int end,
      int version) {
    this.speed1 = speed1;
    this.speed2 = speed2;
    this.dis = dis;
    this.beforeTime = beforeTime;
    this.start = start;
    this.end = end;
    this.version = version;

    if (Math.abs(dis - 0) < 0.0000001) {
      eventTime = beforeTime;
    }

    if (speed1 <= speed2) {
      eventTime = Double.MAX_VALUE;
    } else {
      eventTime = beforeTime + dis / ((double) speed1 - speed2);
    }
  }

  @Override
  public String toString() {
    return "Data{" +
        "eventTime=" + eventTime +
        ", speed1=" + speed1 +
        ", speed2=" + speed2 +
        ", dis=" + dis +
        ", beforeTime=" + beforeTime +
        ", start=" + start +
        ", end=" + end +
        ", version=" + version +
        '}';
  }
}