package org.Java_EAT_Project;

import java.io.*;
import java.nio.*;
import java.util.*;

public class Serialization {
    public static void serializePlaces(Map<Integer, Place> object, String fileName) {
        try (BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(fileName))) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Place> deserializePlaces(String fileName) {
        Map<Integer, Place> object = null;
        try (BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(fileName))) {
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = (Map<Integer, Place>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void serializeFilter(Map<String, Set<Integer>> object, String fileName) {
        try (BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(fileName))) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Set<Integer>> deserializeFilter(String fileName) {
        Map<String, Set<Integer>> object = null;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = (Map<String, Set<Integer>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
