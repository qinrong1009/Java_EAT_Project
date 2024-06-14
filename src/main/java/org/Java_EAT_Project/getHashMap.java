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

        Map<Double, ArrayList<Place>> treeMap =new TreeMap<>();
        for (int i:set){
            Place want = eatMap.get(i);
            double wantRate= want.getRating();
            if (treeMap.get(wantRate)==null){
                ArrayList<Place> getArrayList = new ArrayList<>();
                getArrayList.add(want);
                treeMap.put(wantRate, getArrayList);
            }else{
                ArrayList<Place> getArrayList = treeMap.get(wantRate);
                getArrayList.add(want);
                treeMap.put(wantRate, getArrayList);
            }
            //treeMap.put(want.getRating(), want);
        }
        ArrayList<Place> orderedArrayList = new ArrayList<>();
        //put place in arraylist
        for(Map.Entry<Double, ArrayList<Place>> entry: treeMap.entrySet()){
            ArrayList<Place> rests=entry.getValue();
            for(Place res: rests){
                orderedArrayList.add(res);
            }
            //orderedArrayList.add(entry.getValue());
        }
        return orderedArrayList;

    }

    public void readHashMap(String fileName){
        this.eatMap = Serialization.deserializePlaces(fileName);
    }
}

