package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.HashMap;
import java.util.Map;

public class Day1 implements ISolution{
    private final Map<String, String> DIGITMAP = new HashMap<>(){{
        put("one", "o1e");
        put("two", "t2o");
        put("three", "t3e");
        put("four", "f4r");
        put("five", "f5e");
        put("six", "s6x");
        put("seven", "s7n");
        put("eight", "e8t");
        put("nine", "n9e");
    }};
    @Override
    public int solutionPart1(){
        var data = MyFileReader.ReadFromFile("_Resources/Day1.txt");
        var sum = 0;
        for (String line: data) {
            String numbers = getNumbersInLine(line);
            sum += getFirstAndLast(numbers);
        }

        return sum;
    }

    @Override
    public int solutionPart2() {
        var data = MyFileReader.ReadFromFile("_Resources/Day1.txt");
        var sum = 0;
        for (String line: data) {
            line = replaceStringDigitsWithNumbers(line);
            String numbers = getNumbersInLine(line);
            sum += getFirstAndLast(numbers);
        }

        return sum;
    }

    private String replaceStringDigitsWithNumbers(String line) {
        var newLineValue = line;
        for(Map.Entry<String, String> entry : DIGITMAP.entrySet()){
            if(newLineValue.contains(entry.getKey())){
                newLineValue = newLineValue.replace(entry.getKey(), entry.getValue());
            }
        }
        return newLineValue;
    }

    private int getFirstAndLast(String numbers) {
        String sb = String.valueOf(
                numbers.charAt(0)) +
                numbers.charAt(numbers.length() - 1
        );
        return Integer.parseInt(sb);
    }

    private String getNumbersInLine(String line) {
        StringBuilder numbers = new StringBuilder();
        for (char value : line.toCharArray()){
            if (Character.isDigit(value)){
                numbers.append(value);
            }
        }
        return numbers.toString();
    }

}
