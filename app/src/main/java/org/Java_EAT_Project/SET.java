package org.Java_EAT_Project;

import org.Java_EAT_Project.serialize.*;

import java.util.*;
import com.google.common.collect.Sets;

public class SET {
    Map<String, Set<Integer>> filterSet = new HashMap<>();
    List<List<String>> filterName = List.of(List.of("中", "美", "韓", "日", "泰"),
                                    List.of("東區", "中西", "北區"),
                                    List.of("100", "200", "400", "600"));

    public void readFilterSet(){
        this.filterSet = Serialization.deserializeFilter("filter.ser");
    }

    public void filterRestaurant(List<List<Boolean>> userfilter){
        Set<Integer> userSet = new HashSet<>();
        //type: 中 美 韓 日 泰
        //area: 東 中西 北
        //money: 100 200 400 600

        for(int i = 0; i < 3; i++){
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
    }
    
}
