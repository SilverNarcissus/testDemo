import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by SilverNarcissus on 2016/12/3.
 */
public class WeakMap {
    static Map<WeakMap, Integer> map = new WeakHashMap<WeakMap, Integer>();
    static WeakMap map1 = new WeakMap();

    public static void main(String[] args) {
        WeakMap c = new WeakMap();
        c.f();
        WeakMap w;
        for (int i = 0; i < 1000; i++) {
            ArrayList<Integer> a = new ArrayList<Integer>(20000);
            System.out.println(map.isEmpty());
        }
    }

    public void f() {
        map.put(map1, 1);
        System.out.println("");
    }
}
