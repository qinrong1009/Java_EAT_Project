package org.Java_EAT_Project;
import java.util.*;
import java.util.Map;

//import java.lang.*;

public class getHashMap {
    Map<Integer, Place> eatMap = new HashMap<>();
     
    public ArrayList<Place> getHashMap(Set<Integer> set){
        //deserialize restaurant map
        //Map<Integer, Place> eatMap = Serialization.deserializePlaces("places.ser");
        //set tree map to get rating order
        Map<Double, Place> treeMap =new TreeMap<>();
        for (int i:set){
            Place want = eatMap.get(i);
            treeMap.put(want.getRating(), want);
        }
        ArrayList<Place> orderedArrayList = new ArrayList<>();
        //put place in arraylist
        for(Map.Entry<Double, Place> entry: treeMap.entrySet()){
            orderedArrayList.add(entry.getValue());
        }
        return orderedArrayList;

    }

    public void readHashMap(String fileName){
        this.eatMap = Serialization.deserializePlaces(fileName);
    }
}

