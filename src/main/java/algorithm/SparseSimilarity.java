package algorithm;


import java.util.*;

/**
 * Created by SilverNarcissus on 2019/4/9.
 */
public class SparseSimilarity {
    public static void main(String[] args) {
        SparseSimilarity sparseSimilarity = new SparseSimilarity();
        List<List<Integer>> inputs = new ArrayList<>();
        List<Integer> l13 = new ArrayList<>();
        l13.add(14);
        l13.add(15);
        l13.add(100);
        l13.add(9);
        l13.add(3);

        List<Integer> l16 = new ArrayList<>();
        l16.add(32);
        l16.add(1);
        l16.add(9);
        l16.add(3);
        l16.add(5);

        List<Integer> l19 = new ArrayList<>();
        l19.add(15);
        l19.add(29);
        l19.add(2);
        l19.add(6);
        l19.add(8);
        l19.add(7);

        List<Integer> l24 = new ArrayList<>();
        l24.add(7);
        l24.add(10);

        inputs.add(l13);
        inputs.add(l16);
        inputs.add(l19);
        inputs.add(l24);

        sparseSimilarity.handle(inputs);
    }


    public void handle(List<List<Integer>> documents){
        HashMap<Integer, List<Integer>> reverseIndex = new HashMap<>();
        // O(DW)
        for(int i = 0; i < documents.size(); i++){
            for(int word : documents.get(i)){
                mapAdd(reverseIndex, word, i);
            }
        }

        System.out.println(reverseIndex);
        // O(P)
        HashMap<Integer, Integer> pairIntersect = new HashMap<>();
        for(List<Integer> document : reverseIndex.values()){
            for (int i = 0; i < document.size(); i++) {
                for (int j = i + 1; j < document.size(); j++) {
                    System.out.println("here");
                    int key = buildKey(document.get(i), document.get(j));
                    pairIntersect.put(key, pairIntersect.getOrDefault(key, 0) + 1);
                }
            }
        }

        System.out.println(pairIntersect);
        for(Map.Entry<Integer, Integer> entry: pairIntersect.entrySet()){
            printEntry(entry, documents);
        }
    }

    private void printEntry(Map.Entry<Integer, Integer> entry, List<List<Integer>> documents){
        int a = entry.getKey() >> 16;
        int b = entry.getKey() - (a << 16);
        double union = documents.get(a).size() + documents.get(b).size() - entry.getValue();

        System.out.println(a + " " + b + " similarity: " + entry.getValue() / union);
    }

    private int buildKey(int a, int b){
        return (a << 16) + b;
    }

    private void mapAdd(HashMap<Integer, List<Integer>> reverseIndex, int key, int value){
        if(reverseIndex.containsKey(key)){
            reverseIndex.get(key).add(value);
        }
        else {
            List<Integer> l = new ArrayList<>();
            l.add(value);
            reverseIndex.put(key, l);
        }
    }
}
