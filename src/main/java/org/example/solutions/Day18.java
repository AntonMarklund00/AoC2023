package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 implements ISolution{

    private boolean part1 = true;

    @Override
    public long solutionPart1() {
        String[] lines = MyFileReader.ReadFromFileArray("_Resources/Day18.txt");
        long sum = 0;
        List<Instruction> instructions = new ArrayList<>();
        int max = 0;
        for (String line : lines) {
            instructions.add(new Instruction(line, part1));
            max += instructions.get(instructions.size() - 1).distance;
        }
        List<Long> xVals = new ArrayList<>();
        List<Long> yVals = new ArrayList<>();
        long x = 0;
        long y = 0;
        int[] xs = {1, 0, -1, 0};
        int[] ys = {0, -1, 0, 1};
        for (Instruction instruction : instructions) {
            x += instruction.distance * xs[instruction.direction];
            y += instruction.distance * ys[instruction.direction];
            xVals.add(x);
            yVals.add(y);
        }
        for (int i = 0; i < xVals.size(); i++) {
            sum -= xVals.get(i) * yVals.get((i + 1) % xVals.size());
            sum += xVals.get((i + 1) % xVals.size()) * yVals.get(i);
        }
        sum /= 2;
        sum += max / 2 + 1;
        return sum;
    }

    @Override
    public long solutionPart2() {
        part1 = false;
        return solutionPart1();
    }

}

class Instruction {
    public int distance;
    public int direction;

    public Instruction(String line, boolean part1) {
        Map<String, Integer> map = new HashMap<>();
        map.put("R", 0);
        map.put("D", 1);
        map.put("L", 2);
        map.put("U", 3);

        if (part1) {
            distance = Integer.parseInt(line.split(" ")[1]);
            direction = map.get(line.split(" ")[0]);
        } else {
            String hex = line.split("#|\\)")[1];
            direction = Integer.parseInt(hex.substring(hex.length() - 1));
            distance = Integer.parseInt(hex.substring(0, hex.length() - 1), 16);
        }
    }
}