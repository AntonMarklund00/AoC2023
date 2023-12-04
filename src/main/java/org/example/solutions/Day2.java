package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.HashMap;
import java.util.Map;

public class Day2 implements ISolution{
    @Override
    public int solutionPart1() {
        var data = MyFileReader.ReadFromFile("_Resources/Day2.txt");
        var sum = 0;
        for (String line : data){
            var values = GetNumberByColorInGame(line);
            if(isGameOk(values)){
                sum += GetGameValue(line);
            }
        }
        return sum;
    }


    @Override
    public int solutionPart2() {
        var data = MyFileReader.ReadFromFile("_Resources/Day2.txt");
        var sum = 0;
        for (String line : data){
            sum += GetMaxCubesByColors(line);
        }
        return sum;
    }

    private boolean isGameOk(Map<String, Integer> values) {
        for (var value : values.keySet()){
            if((value.equals("red") && values.get(value) > 12) || (value.equals("green") && values.get(value) > 13) || (value.equals("blue") && values.get(value) > 14))
                return false;
        }
        return true;
    }

    private int GetGameValue(String line) {
        var gameSplitString = line.split(":");
        return getNumbersInString(gameSplitString[0]);
    }

    private Map<String, Integer> GetNumberByColorInGame(String line) {
        var map = new HashMap<String, Integer>();

        var gameSplitString = line.split(":");
        var setSplitString = gameSplitString[1].split(";");

        for (var set : setSplitString){
            var colorSplitString = set.split(",");

            for (var colorString : colorSplitString){
                var number = getNumbersInString(colorString);
                var color = getCharsInString(colorString);
                var value = map.getOrDefault(color, -1);

                if(value == -1 || value < number){
                    map.remove(color);
                    map.put(color, number);
                }
            }
        }
        return map;
    }

    private int GetMaxCubesByColors(String line) {
        int maxRed = Integer.MIN_VALUE;
        int maxGreen = Integer.MIN_VALUE;
        int maxBlue = Integer.MIN_VALUE;

        var gameSplitString = line.split(":");
        var setSplitString = gameSplitString[1].split(";");
        for (var set : setSplitString){
            var colorSplitString = set.split(",");
            for (var colorString : colorSplitString){
                var number = getNumbersInString(colorString);
                var color = getCharsInString(colorString);

                if(color.equals("red") && number > maxRed){
                    maxRed = number;
                }else if(color.equals("green") && number > maxGreen){
                    maxGreen = number;
                }else if(color.equals("blue") && number > maxBlue){
                    maxBlue = number;
                }
            }
        }
        return maxRed * maxBlue * maxGreen;
    }

    private int getNumbersInString(String value){
        return Integer.parseInt(value.replaceAll("[^0-9]", ""));
    }

    private String getCharsInString(String value){
        return value.replaceAll("[0-9]", "").trim();
    }
}
