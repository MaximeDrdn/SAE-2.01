package com.spaceraceinc.logicielchevalblancdemerde.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void writeFile(String fileName, Object data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/"+fileName, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Object> readFile(String fileName) {
        List<Object> list = new ArrayList<>();
        Object object;

        try {
            FileInputStream fileInputStream = new FileInputStream("data/"+fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            do {
                object = objectInputStream.readObject();
                list.add(object);
            } while(object != null);
            objectInputStream.close();
        } catch(IOException | ClassNotFoundException ignored) {
        }
        return list;
    }

}
