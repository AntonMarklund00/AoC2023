package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.*;
import java.util.regex.Pattern;

public class Day3 implements ISolution{
    private static final Pattern isSymbol = Pattern.compile("[@#$%^&*\\-=+/]");

    @Override
    public long solutionPart1() {
        var data = MyFileReader.ReadFromFile("_Resources/Day3.txt");
        var matrix = getMatrix(data);
        ArrayList<Symbol> symbols = getSymbols(matrix);

        return getSumOfAllAdjacentNumbers(symbols, matrix);
    }

    @Override
    public long solutionPart2() {
        var data = MyFileReader.ReadFromFile("_Resources/Day3.txt");
        var matrix = getMatrix(data);
        ArrayList<Symbol> symbols = getSymbols(matrix);

        return getSumOfAllAdjacentNumbersWithEngine(symbols, matrix);
    }

    private int getSumOfAllAdjacentNumbers(ArrayList<Symbol> symbols, String[][] matrix) {
        var sum = 0;
        for(var symbol : symbols){
            var adjacentPositions = symbol.getAdjacentPositions();
            for (var position: adjacentPositions) {
                if(Character.isDigit(matrix[position[1]][position[0]].charAt(0))){
                   sum += findAndReplaceNumber(position[0], position[1], matrix);
                }
            }
        }
        return sum;
    }

    private int getSumOfAllAdjacentNumbersWithEngine(ArrayList<Symbol> symbols, String[][] matrix) {
        var sum = 0;
        for(var symbol : symbols){
            List<Integer> sums = new ArrayList<>();
            var adjacentPositions = symbol.getAdjacentPositions();
            for (var position: adjacentPositions) {
                if(Character.isDigit(matrix[position[1]][position[0]].charAt(0))){
                    sums.add(findAndReplaceNumber(position[0], position[1], matrix));
                }
            }
            if(symbol.symbol.equals("*") && sums.size() == 2){
                sum += (sums.get(0) * sums.get(1));
            }
        }
        return sum;
    }

    private int findAndReplaceNumber(int x, int y, String[][] matrix) {
        var xStartValue = x;
        StringBuilder stringBuilder = new StringBuilder();

        while (x < matrix[y].length && Character.isDigit(matrix[y][x].charAt(0))){
            stringBuilder.append(matrix[y][x]);
            matrix[y][x]=".";
            x++;
        }
        x = xStartValue-1;
        while (x >= 0 && Character.isDigit(matrix[y][x].charAt(0))){
            stringBuilder.insert(0, matrix[y][x]);
            matrix[y][x]=".";
            x--;
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    private ArrayList<Symbol> getSymbols(String[][] matrix) {
        ArrayList<Symbol> symbols = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(isSymbol.matcher(matrix[i][j]).matches()){
                    symbols.add(new Symbol(matrix[i][j], j, i));
                }
            }
        }
        return symbols;
    }

    public String[][] getMatrix(List<String> data){
        String[][] matrix = new String[data.size()][data.get(0).length()];
        for (int i = 0; i < data.size(); i++) {
            var splitLine = data.get(i).split("");
            matrix[i] = splitLine;
        }
        return matrix;
    }
    record Symbol(String symbol, int x, int y) {
        int[][] getAdjacentPositions() {
            return new int[][] {
                    {x - 1, y - 1},
                    {x, y - 1},
                    {x + 1, y - 1},
                    {x - 1, y},
                    {x + 1, y},
                    {x - 1, y + 1},
                    {x, y + 1},
                    {x + 1, y + 1}
            };
        }
    }

}
