package org.example.solutions;

import org.example.utils.MyFileReader;

public class Day1 implements ISolution{
    @Override
    public int solution(){
        var data = MyFileReader.ReadFromFile("_Resources/Day1.txt");
        var sum = 0;
        for (String line: data) {
            String numbers = getNumbersInLine(line);
            sum += getFirstAndLast(numbers);
        }

        return sum;
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
