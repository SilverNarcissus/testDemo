package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by SilverNarcissus on 2019/5/26.
 * Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)

 Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).

 When you get an instruction "A", your car does the following: position += speed, speed *= 2.

 When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)

 For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.

 Now for some target position, say the length of the shortest sequence of instructions to get there.
 */
public class RaceCar {
    private int[] dis = {0, 1, 2, 4, 8, 16, 32, 64, 128,
            256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
    public int racecar(int target) {
        
        if(target == 0){
            return 0;
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.step, o2.step);
            }
        });

        int n = target << 2;
        boolean[][] reach = new boolean[n][2];
        target = target * 3;
        priorityQueue.add(new Node(0, n >> 1, 1));

        while (!priorityQueue.isEmpty()){
            Node cur = priorityQueue.poll();
            if(cur.loc == target){
                return cur.step - 1;
            }
            if(reach[cur.loc][cur.face > 0 ? 0 : 1]){
                continue;
            }
            reach[cur.loc][cur.face > 0 ? 0 : 1] = true;

            int step = cur.step;
            int loc = cur.loc;
            for (int i = 0; i < 16; i++) {
                loc += cur.face * dis[i];
                if(step < 0 || step >= n){
                    break;
                }
                priorityQueue.add(new Node(step + i + 1, loc, - cur.face));
            }
        }

        // can't reach
        return -1;
    }

    class Node{
        int step;
        int loc;
        int face;

        public Node(int step, int loc, int face) {
            this.step = step;
            this.loc = loc;
            this.face = face;
        }
    }
}
