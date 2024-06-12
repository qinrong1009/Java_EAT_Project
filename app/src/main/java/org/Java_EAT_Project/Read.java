package org.Java_EAT_Project;

import org.Java_Eat_Project.structure;
import org.Java_Eat_Project.serialize;

import java.io.*;
import java.util.*;

public class Read {
    public static void txtProcessing(File file) {
        Map<Integer, Place> placeMap = new HashMap<>();
        Map<String, Set<Integer>> filterMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                /*
                    0: name - 餐廳名字
                    1: type - 咖啡廳,ＸＸ料理
                    2: rating - 評分
                    3: district - 區域
                    4: address - 地址
                    5: priceRange - 價位
                    6: openingHours - 營業時間
                */
                Place place = new Place(attributes[0], attributes[1],Double.parseDouble(attributes[2]), attributes[3], attributes[4], attributes[5], attributes[6]);
                placeMap.put(index, place);

                filterMap.computeIfAbsent(attributes[1], k -> new HashSet<>()).add(index);
                filterMap.computeIfAbsent(attributes[2], k -> new HashSet<>()).add(index);
                filterMap.computeIfAbsent(attributes[4], k -> new HashSet<>()).add(index);
                filterMap.computeIfAbsent(attributes[6], k -> new HashSet<>()).add(index);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Serialization.serializePlaces(placeMap, "places.ser");
        Serialization.serializeFilter(filterMap, "filter.ser"); 
    }
}
