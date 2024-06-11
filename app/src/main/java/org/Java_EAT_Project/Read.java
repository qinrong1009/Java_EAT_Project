package org.Java_EAT_Project;

import org.Java_Eat_Project.structure;;
import org.Java_Eat_Project.serialize;

import java.io.*;
import java.util.*;

public class Read {
    public static void txtProcessing(File file) {
        Map<Integer, Restaurant> restaurantMap = new HashMap<>();
        Map<String, Set<Integer>> filterMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                /*
                    0: name - 餐廳名字
                    1: type - 餐廳or咖啡廳
                    2: cuisineType - ＸＸ料理
                    3: rating - 評分
                    4: district - 區域
                    5: address - 地址
                    6: priceRange - 價位
                    7: openingHours - 營業時間
                */
                Restaurant restaurant = new Restaurant(attributes[0], attributes[1], attributes[2], Double.parseDouble(attributes[3]), attributes[4], attributes[5], attributes[6], attributes[7]);
                restaurantMap.put(index, restaurant);

                filterMap.computeIfAbsent(attributes[1], k -> new HashSet<>()).add(index);
                filterMap.computeIfAbsent(attributes[2], k -> new HashSet<>()).add(index);
                filterMap.computeIfAbsent(attributes[4], k -> new HashSet<>()).add(index);
                filterMap.computeIfAbsent(attributes[6], k -> new HashSet<>()).add(index);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Serialization.serialize(restaurantMap, "places.ser");
        Serialization.serialize(filterMap, "filter.ser"); 
    }
}
