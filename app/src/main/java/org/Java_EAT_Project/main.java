package Java_EAT_Project;

import org.Java_Eat_Project.structure;;
import org.Java_Eat_Project.serialize;

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

        GUI gui = new GUI();
    }
}
