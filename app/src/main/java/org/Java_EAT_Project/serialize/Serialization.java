package org.Java_Eat_Project.serialize;

import java.io.*;

public class Serialization {
    public static <Map<Integer, Place>> void serializePlaces(Map<Integer, Place> object, String fileName) {
        try (BufferedOutputStream fileOut = new BufferedOutputStream (new FileOutputStream(fileName))) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <Map<Integer, Place>> Map<Integer, Place> deserializePlaces(String fileName) {
        Map<Integer, Place> object = null;
        try (BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(fileName))) {
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = (Map<Integer, Place>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
    public static <Map<String, Set<Integer>>> void serializeFilter(Map<String, Set<Integer>> object, String fileName) {
        try (BufferedOutputStream fileOut = new BufferedOutputStream (new FileOutputStream(fileName))) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}