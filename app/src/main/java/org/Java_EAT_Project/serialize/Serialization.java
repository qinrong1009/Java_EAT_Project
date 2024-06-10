package org.Java_Eat_Project.serialize;

import java.io.*;

public class SerializationUtil {
    public static <T> void serialize(T object, String fileName) {
        try (BufferedOutputStream fileOut = new BufferedOutputStream (new FileOutputStream(fileName))) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut)
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T deserialize(String fileName) {
        T object = null;
        try (BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(fileName))) {
            ObjectInputStream in = new ObjectInputStream(fileIn)
            object = (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
