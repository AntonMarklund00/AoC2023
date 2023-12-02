package org.example;

import org.example.solutions.Day1;
import org.example.solutions.ISolution;

import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException {
        System.out.println(Objects.requireNonNull(runDay(args)).solution());
    }

    public static ISolution runDay(String[]args) throws IllegalArgumentException {
        return switch (args[0]) {
            case "1" -> new Day1();
            default -> throw new IllegalArgumentException("Illegal Argument");
        };
    }
}