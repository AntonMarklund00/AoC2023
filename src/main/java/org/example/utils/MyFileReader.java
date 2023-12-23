package org.example.utils;

import java.io.*;
import java.util.ArrayList;

public class MyFileReader {
    public static ArrayList<String> ReadFromFile(String path) {
        var file = new File(path);
        var list = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            while ((st = br.readLine()) != null)
                list.add((st));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static String[] ReadFromFileArray(String path) {
        var file = new File(path);
        var list = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            while ((st = br.readLine()) != null)
                list.add((st));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}
