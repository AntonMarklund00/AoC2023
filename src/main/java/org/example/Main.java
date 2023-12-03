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
            case "3" -> new Day3();
            default -> throw new IllegalArgumentException("Illegal Argument");
        };
    }
    public static void runDay(String[] args, ISolution solution){
        if(Objects.equals(args[1], "1")){
            System.out.println(solution.solutionPart1());
        }else if (Objects.equals(args[1], "2")){
            System.out.println(solution.solutionPart2());
        }
    }
}