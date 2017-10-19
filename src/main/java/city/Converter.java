package city;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by SilverNarcissus on 2017/10/12.
 */
public class Converter {
    static List<Place> safeList = new LinkedList<>();
    static List<Place> dangerList = new LinkedList<>();
    static Set<Place> redSet = new HashSet<>();
    static Set<Place> total = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/SilverNarcissus/Desktop/result(1).csv")));
        reader.readLine();
        String line = "";
        while(!(line = reader.readLine()).equals(",,,")){
            String[] s = line.split(",");
            Place place = new Place(Double.valueOf(s[0]), Double.valueOf(s[1]), Integer.valueOf(s[3]));
            if(place.type > 1){
                dangerList.add(place);
                redSet.add(place);
                total.add(place);
            }
            else {
                safeList.add(place);
            }
        }

        System.out.println("lat,lng,value,type");
        int count = 0;
        for(Place safe : safeList){
            for (Place danger : dangerList){
                if((safe.x - 0.03) < danger.x && (safe.x + 0.03) > danger.x && (safe.y - 0.05) < danger.y && (safe.y + 0.05) > danger.y){
                    redSet.remove(danger);
                    count ++;
                    //System.out.println("\"" + safe.y + "," + safe.x + "\"" + "," + "\"" + danger.y + "," + danger.x + "\"");
                }
            }
        }
//        //System.out.println(redSet.size());
        total.removeAll(redSet);

        for(Place place : safeList){
            System.out.println(place.x + "," + place.y + ",1," + "1");
        }
        for(Place place : total){
            System.out.println(place.x + "," + place.y + ",1," + "2");
        }
        for(Place place : redSet){
            System.out.println(place.x + "," + place.y + ",1," + "3");
        }
    }


}
