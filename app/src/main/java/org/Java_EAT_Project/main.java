package org.Java_EAT_Project;

//import org.Java_Eat_Project.structure;
import org.Java_EAT_Project.Serialization;

import java.io.*;
import java.nio.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        //解序列並得到存所有資料的HashMap
        //GUI gui = new GUI();
        //SET Set = new SET(gui回傳的條件);
        //getHashMap get = new getHashMap(SET回傳的set)
        //Set.print(getHashMap回傳的HashMap)
        //gui.print(Set.print回傳的東西)

        File placesFile = new File("src/main/resources/places.txt");
        File placesSerFile = new File("places.ser");
        File filterSerFile = new File("filter.ser");

        Read.txtProcessing(placesFile);

        //--------GUI的部分--------//user回傳條件
        GUI gui = new GUI();
        GUI_function function = gui.main();
        infoStore info = function.firstPart();
        //--------GUI的部分--------//user回傳條件

        // ---SET取交集---
        SET set = new SET();
        set.readFilterSet(filterSerFile);
        Set<Integer> userSet = set.filterRestaurant(info.choose, info.cuisineType, info.district, info.priceRange, info.openingHours);
        //---SET取交集---

        //--------GUI的部分--------//demo data set：手動生一個測資給GUI測試用！
        ArrayList<Integer> order = new ArrayList<>();
        order.add(23);
        order.add(2);
        ArrayList<String> num_23 = new ArrayList<>();
        num_23.add("小古巴漢堡");
        num_23.add("4.6");
        num_23.add("中西區城隍街48號");
        num_23.add("Mon. 11:00~18:00 / 17:00~20:00\nTue. 11:00~18:00\nWed. 11:00~18:00\nThu. 11:00~18:00\nFri. 11:00~18:00\nSat. 11:00~18:00\nSun. closed");
        num_23.add("美式");
        ArrayList<String> num_2 = new ArrayList<>();
        num_2.add("SK尚恩美式餐廳");
        num_2.add("4.6");
        num_2.add("中西區城隍街48號");
        num_2.add("Mon. 11:00~18:00\nTue. 11:00~18:00\nWed. 11:00~18:00\nThu. 11:00~18:00\nFri. 11:00~18:00\nSat. 11:00~18:00\nSun. closed");
        num_2.add("咖啡廳");
        Map<Integer, ArrayList<String>> restaurantMap = new HashMap<>();
        restaurantMap.put(23, num_23);
        restaurantMap.put(2, num_2);
        //demo data set

        function.second_part(order, restaurantMap);
        //--------GUI的部分--------//回傳data set給GUI輸出

        //---也算GUI的部分---//data set
        //"Mon. 11:00~18:00\tTue. 11:00~18:00\t"

        //

        
    }
}
