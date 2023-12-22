package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day15 implements ISolution{
    @Override
    public long solutionPart1() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day15.txt");
        int sum = 0;

        for (String line : data.get(0).split(",")) {
            sum += hash(line);
        }

        return sum;
    }

    @Override
    public long solutionPart2() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day15.txt");
        String[] inputs = data.get(0).split(",");
        HashMap<Long, List<Lens>> boxes = new HashMap<>();

        for (String step : inputs) {
            String label = step.replaceAll("[^a-zA-Z]", "");
            String operation = step.substring(label.length(), label.length() + 1);
            String focalLen = step.substring(label.length() + 1);

            long box = hash(label);
            Lens item;
            switch (operation) {
                case "-":
                    if (boxes.containsKey(box)) {
                        item = boxes.get(box).stream().filter(i -> i.getLabel().equals(label)).findFirst().orElse(null);
                        if (item != null) {
                            boxes.get(box).remove(item);
                        }
                    }
                    break;
                case "=":
                    if (!boxes.containsKey(box)) {
                        boxes.put(box, new ArrayList<>());
                    }
                    item = boxes.get(box).stream().filter(i -> i.getLabel().equals(label)).findFirst().orElse(null);
                    if (item == null) {
                        boxes.get(box).add(new Lens(label, Integer.parseInt(focalLen)));
                    } else {
                        item.setValue(Integer.parseInt(focalLen));
                    }
                    break;
            }
        }

        long power = 0;
        for (HashMap.Entry<Long, List<Lens>> box : boxes.entrySet()) {
            int i = 1;
            for (Lens item : box.getValue()) {
                power += (box.getKey() + 1) * i * item.getValue();
                i++;
            }
        }

        return power;
    }

    private int hash(String input) {
        int hash = 0;
        for (char c : input.toCharArray()) {
            hash += c;
            hash *= 17;
            hash %= 256;
        }

        return hash;
    }

    static class Lens {
        private final String label;
        private int value;

        public Lens(String label, int value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
