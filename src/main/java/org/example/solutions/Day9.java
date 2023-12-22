package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.ArrayList;
import java.util.List;

public class Day9 implements ISolution{

    @Override
    public long solutionPart1() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day9.txt");
        int sum = 0;

        for (String input : data) {
            List<List<Integer>> rows = buildRows(input);

            int appendAfter = 0;

            for (int i = rows.size() - 1; i >= 0; i--) {
                if (i == rows.size() - 1) {
                    rows.get(i).add(0);
                    appendAfter = 0;
                    continue;
                }

                appendAfter = rows.get(i).get(rows.get(i).size() - 1) + appendAfter;
                rows.get(i).add(appendAfter);
            }

            sum += rows.get(0).get(rows.get(0).size() - 1);
        }
        return sum;
    }

    @Override
    public long solutionPart2() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day9.txt");
        int sum = 0;

        for (String input : data) {
            List<List<Integer>> rows = buildRows(input);

            int below = 0;

            for (int i = rows.size() - 1; i >= 0; i--) {
                if (i == rows.size() - 1) {
                    rows.get(i).add(0);
                    below = 0;
                    continue;
                }

                below = rows.get(i).get(0) - below;
                rows.get(i).add(0, below);
            }

            sum += rows.get(0).get(0);
        }
        return sum;
    }

    private List<List<Integer>> buildRows(String line) {
        List<List<Integer>> value = new ArrayList<>();
        List<Integer> row = getNumbers(line);

        value.add(row);

        while (row.stream().anyMatch(e -> e != 0)) {
            row = getNextRow(row);
            value.add(row);
        }

        return value;
    }

    private List<Integer> getNextRow(List<Integer> line) {
        List<Integer> value = new ArrayList<>();

        for (int i = 1; i < line.size(); i++) {
            value.add(line.get(i) - line.get(i - 1));
        }

        return value;
    }

    private List<Integer> getNumbers(String line) {
        String[] numbers = line.split("\\s+");
        List<Integer> result = new ArrayList<>();

        for (String number : numbers) {
            result.add(Integer.parseInt(number));
        }

        return result;
    }
}
