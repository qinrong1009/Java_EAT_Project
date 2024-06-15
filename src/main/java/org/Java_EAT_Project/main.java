package org.Java_EAT_Project;

//import org.Java_EAT_Project.Read;
//import org.Java_Eat_Project.structure;
//import org.Java_EAT_Project.Serialization;

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

        File placesFile = new File("/Users/yeqinrong/Desktop/Java_EAT_Project/src/main/resources/places.txt");
        File placesSerFile = new File("places.ser");
        File filterSerFile = new File("filter.ser");

        Read.txtProcessing(placesFile);
        SET SET = new SET(filterSerFile);
        getHashMap getHashMap = new getHashMap();
        getHashMap.readHashMap(placesSerFile.getName());
        
        /*
        //--------GUI的部分--------demo data set：手動生一個測資給GUI測試用！
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
        num_2.add("腐城");
        num_2.add("4.6");
        num_2.add("中西區城隍街48號");
        num_2.add("Mon. 11:00~18:00\nTue. 11:00~18:00\nWed. 11:00~18:00\nThu. 11:00~18:00\nFri. 11:00~18:00\nSat. 11:00~18:00\nSun. closed");
        num_2.add("咖啡廳");
        Map<Integer, ArrayList<String>> restaurantMap = new HashMap<>();
        restaurantMap.put(23, num_23);
        restaurantMap.put(2, num_2);
        //--------GUI的部分--------回傳data set給GUI輸出
        */


        //--------GUI的部分--------//user回傳條件
        GUI gui = new GUI();
        GUI_function function = gui.main();
        infoStore info = function.firstPart();//第一次user回傳
        // System.out.println("arrarlist:" + info.district.get(0));

        while(info.END == false){//第二次後無限迴圈，直到按結束
            //---SET---
            Set<Integer> userSet = SET.filterRestaurant(info.choose, info.cuisineType, info.district, info.priceRange, info.openingHours);
            //System.out.println(userSet);
            ArrayList<Place> restaurantList = getHashMap.getHashMap(userSet);
            // System.out.println(restaurantList);
            ArrayList<Integer> order = new ArrayList<>();
            for(int i = restaurantList.size()-1; i >= 0 ; i--){
                order.add(i);
            }
            //---SET---
            HashMap<Integer, ArrayList<String>> restaurantMap = orderRestaurant(restaurantList);
            // System.out.println(restaurantMap);
            info = function.second_part(order, restaurantMap, function);
            //第二部分顯示結果後，若再來一次會直接做第一部分的問題，然後回傳新的info。
            // System.out.println("arrarlist:" + info.district.get(0));
        }
        System.exit(0);
        //--------GUI的部分--------user回傳條件

        // ---SET取交集---
        //SET set = new SET(filterSerFile);
        //set.readFilterSet(filterSerFile);
        
        //---SET取交集---


        //---也算GUI的部分---//data set
        //"Mon. 11:00~18:00\tTue. 11:00~18:00\t"

        //
    }

    private static HashMap<Integer, ArrayList<String>> orderRestaurant(ArrayList<Place> restaurantList){
        HashMap<Integer, ArrayList<String>> restaurantMap = new HashMap<>();
            
        for(int i = 0; i < restaurantList.size(); i++){
            Place place = restaurantList.get(i);
            ArrayList<String> restaurant = new ArrayList<>();
            restaurant.add(place.getName());
            restaurant.add(Double.toString(place.getRating()));
            restaurant.add(place.getAddress());
            restaurant.add(place.getOpeningHours().replaceAll(";","\n"));
            restaurant.add(place.getType());

            restaurantMap.put(i, restaurant);
        }

        return restaurantMap;
    }  
}
