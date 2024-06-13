package org.Java_EAT_Project;

//import org.Java_EAT_Project.Serialization.*;

import java.util.*;
import com.google.common.collect.Sets;
import java.io.File;

public class SET {
    Map<String, Set<Integer>> filterSet = new HashMap<>();
    List<List<String>> filterName = List.of(List.of("中", "美", "韓", "日", "泰", "咖啡廳"),
                                    List.of("東區", "中西", "北區"),
                                    List.of("100", "200", "400", "600"),
                                    List.of("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));

    public void readFilterSet(File file){
        this.filterSet = Serialization.deserializeFilter(file.getName());
    }

    public Set<Integer> filterRestaurant(int choose, ArrayList<Boolean> cuisineType, ArrayList<Boolean> district, ArrayList<Boolean> priceRange, ArrayList<Boolean> openingHour){
        ArrayList<ArrayList<Boolean>> userfilter = new ArrayList<>();
        if(choose == 2){
            cuisineType.add(false);
        }
        else{
            cuisineType.add(true);
        }
        userfilter.add(cuisineType);
        userfilter.add(district);
        userfilter.add(priceRange);
        userfilter.add(openingHour);

        return filterRestaurant(userfilter);

    }

    private Set<Integer> filterRestaurant(ArrayList<ArrayList<Boolean>> userfilter){
        Set<Integer> userSet = new HashSet<>();
        //type: 中 美 韓 日 泰
        //area: 東 中西 北
        //money: 100 200 400 600

        for(int i = 0; i < filterName.size(); i++){
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < filterName.get(i).size(); j++){
                if(userfilter.get(i).get(j)){
                    set = Sets.union(set, filterSet.get(filterName.get(i).get(j)));
                }
            }
            if(i == 0){
                userSet = set;
            }
            else{
                userSet = Sets.intersection(userSet, set);
            }
        }

        return userSet;
    }
    
}
