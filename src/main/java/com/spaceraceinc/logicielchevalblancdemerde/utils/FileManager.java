package com.spaceraceinc.logicielchevalblancdemerde.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void writeFile(String fileName, Object data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/"+fileName+".dat", true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream) {
                @Override
                protected void writeStreamHeader() throws IOException {
                    // empty
                }
            };

            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Object> readFile(String fileName) {
        List<Object> list = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("data/"+fileName+".dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            System.out.println(objectInputStream.readObject());
            System.out.println(objectInputStream.readObject());
            objectInputStream.close();
        } catch(IOException | ClassNotFoundException ignored) {
            ignored.printStackTrace();
        }
        return list;
    }

}
