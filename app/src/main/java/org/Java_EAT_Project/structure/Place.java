package org.Java_Eat_Project.structure;

import java.io.Serializable;

public class Place implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name; //餐廳名字
    private String type; //餐廳or咖啡廳
    private String cuisineType; //ＸＸ料理
    private double rating; //評分
    private String district; //區域
    private String address; //地址
    private String priceRange; //價位
    private String openingHours; //營業時間
    
    public Place(String name, String type, String cuisineType, double rating, String district, String address, String priceRange, String openingHours){
        this.name = name;
        this.type = type;
        this.cuisineType = cuisineType;
        this.rating = rating;
        this.district = district;
        this.address = address;
        this.priceRange = priceRange;
        this.openingHours = openingHours;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getCuisineType(){
        return cuisineType;
    }

    public double getRating(){
        return rating;
    }

    public String getDistrict(){
        return district;
    }
    
    public String getAddress(){
        return address;
    }

    public String getPriceRange(){
        return priceRange;
    }

    public String getOpeningHours(){
        return openingHours;
    }
}
