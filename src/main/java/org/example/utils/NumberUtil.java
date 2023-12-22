package org.example.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberUtil {
    public static List<String> GetNumbers(String v){
        v = v.replaceAll("[^0-9]+", " ");
        return new ArrayList<>(Arrays.asList(v.trim().split(" ")));
    }

    public static String getAllNumbersInLineCombined(String line){
        return line.replaceAll("[^0-9]+", "");
    }
}
