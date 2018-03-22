package algorithm;

import java.util.Map;
import java.util.TreeMap;

class RangeModule {
    TreeMap<Integer, Integer> range = new TreeMap<>();

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        int newLeft = left;
        int newRight = right;
        Map.Entry<Integer, Integer> before = range.floorEntry(left);
        if(before != null && before.getValue() >= left){
            newLeft = before.getKey();
        }

        Map.Entry<Integer, Integer> last = range.floorEntry(right);

        if(last != null && last.getValue() > right){
            newRight = last.getValue();
        }

        Integer now = Integer.valueOf(newLeft);
        Integer end = Integer.valueOf(newRight);
        while(now != null && now < end){
            int temp = now;
            now = range.higherKey(now);
            range.remove(temp);
        }

        range.put(newLeft, newRight);
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> before = range.floorEntry(left);
        //System.out.println(range);
        if(before != null && before.getValue() >= right){
            return true;
        }
        return false;
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> before = range.lowerEntry(right);

        while(before != null && before.getValue() > left){
            if(before.getKey() < left && before.getValue() > right){
                range.put(before.getKey(), left);
                range.put(right, before.getValue());
            }
            else if(before.getKey() >= left && before.getValue() > right){
                range.remove(before.getKey());
                range.put(right, before.getValue());
            }
            else if(before.getKey() < left && before.getValue() <= right){
                range.put(before.getKey(), left);
            }
            else{
                range.remove(before.getKey());
            }

            before = range.lowerEntry(before.getKey());
        }

    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */