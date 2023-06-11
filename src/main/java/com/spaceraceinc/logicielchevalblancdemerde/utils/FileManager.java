package com.spaceraceinc.logicielchevalblancdemerde.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileManager {

    private static final HashMap<String, ObjectOutputStream> list = new HashMap<>();

    public static void writeFile(String fileName, Object data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/" + fileName + ".dat", true);
            ObjectOutputStream out;

            if (!list.containsKey(fileName)) {
                out = new ObjectOutputStream(fileOutputStream);
                list.put(fileName, out);
            } else
                out = list.get(fileName);

            out.writeObject(data);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Object> readFile(String fileName) {
        List<Object> list = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("data/"+fileName+".dat");
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            Object obj;

            do {
                obj = in.readObject();
                list.add(obj);
            } while(obj != null);
        } catch(IOException | ClassNotFoundException ignored) {
        }
        return list;
    }

}
