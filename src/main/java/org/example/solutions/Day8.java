package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.ArrayList;
import java.util.List;

public class Day8 implements ISolution{
    @Override
    public long solutionPart1() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day8.txt");
        String directions = data.get(0);

        List<Node> nodes = populateNodes(data, false);

        return getAmountOfStepsToFindZ(nodes.stream().filter(x -> x.value.equals("AAA")).findFirst().orElse(null), nodes, directions, false);
    }

    @Override
    public long solutionPart2() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day8.txt");
        String directions = data.get(0);

        List<Node> nodes = populateNodes(data, false);
        List<Node> startNodes = populateNodes(data, true);

        List<Long> cycles = new ArrayList<>();
        for (Node item : startNodes) {
            cycles.add(getAmountOfStepsToFindZ(item, nodes, directions, true));
        }

        return calculateLCM(cycles.stream().mapToLong(Long::longValue).toArray());
    }

    public static class Node {
        public String value;
        public String left;
        public String right;
    }

    private List<Node> populateNodes(List<String> inputArray, boolean isGhost) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 2; i <= inputArray.size() - 1; i++) {
            if (isGhost && inputArray.get(i).charAt(2) != 'A') {
                continue;
            }

            Node node = new Node();
            node.value = inputArray.get(i).substring(0, 3);
            node.left = inputArray.get(i).substring(7, 10);
            node.right = inputArray.get(i).substring(12, 15);
            nodes.add(node);
        }
        return nodes;
    }

    public long getAmountOfStepsToFindZ(Node node, List<Node> nodes, String directions, boolean isGhost) {
        long length = 0;
        int directionIndex = 0;

        while (!nodeIsZ(node, isGhost)) {

            node = getNextNode(node, nodes, directions.charAt(directionIndex));

            length++;
            directionIndex++;
            if (directionIndex == directions.length()) {
                directionIndex = 0;
            }
        }

        return length;
    }

    private boolean nodeIsZ(Node node, boolean isGhost) {
        if (isGhost) {
            return node.value.endsWith("Z");
        } else {
            return node.value.equals("ZZZ");
        }
    }

    private Node getNextNode(Node currentNode, List<Node> nodes, char direction) {
        if (direction == 'L') {
            return nodes.stream().filter(x -> x.value.equals(currentNode.left)).findFirst().orElse(null);
        }

        return nodes.stream().filter(x -> x.value.equals(currentNode.right)).findFirst().orElse(null);
    }

    private long calculateLCM(long[] numbers) {
        long lcm = numbers[0];

        for (long i = 1; i < numbers.length; i++) {
            lcm = calculateLCM(lcm, numbers[(int)i]);
        }

        return lcm;
    }

    private long calculateLCM(long a, long b) {
        return (a * b) / calculateGCD(a, b);
    }

    private long calculateGCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
