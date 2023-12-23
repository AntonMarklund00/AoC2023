package org.example;

import org.example.solutions.*;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException {
        runDay(args, Objects.requireNonNull(getDay(args)));
    }
    public static ISolution getDay(String[]args) throws IllegalArgumentException {
        return switch (args[0]) {
            case "1" -> new Day1();
            case "2" -> new Day2();
            case "3" -> new Day3();
            case "4" -> new Day4();
            case "6" -> new Day6();
            case "8" -> new Day8();
            case "9" -> new Day9();
            case "13" -> new Day13();
            case "15" -> new Day15();
            case "18" -> new Day18();
            default -> throw new IllegalArgumentException("Illegal Argument");
        };
    }
    public static void runDay(String[] args, ISolution solution){
        System.out.println("Part one: " + solution.solutionPart1());
        System.out.println("Part two: " + solution.solutionPart2());
    }
}