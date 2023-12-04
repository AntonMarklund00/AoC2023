package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day4 implements ISolution{
    @Override
    public int solutionPart1() {
        var data = MyFileReader.ReadFromFile("_Resources/Day4.txt");
        var sum = 0;

        for (var line : data) {
            var values = line.split(":")[1].split("\\|");

            List<Integer> winningNumbers = findIntegers(values[0]);
            List<Integer> scratchNumbers = findIntegers(values[1]);
            List<Integer> winners = scratchNumbers.stream().filter(winningNumbers::contains).toList();

            sum += (int) (Math.pow(2, winners.size())/2);
        }
        return sum;
    }

    @Override
    public int solutionPart2() {
        var data = MyFileReader.ReadFromFile("_Resources/Day4.txt");
        Map<Integer, Integer> cardAmounts = new HashMap<>();

        GenerateCardAmountMap(data, cardAmounts);

        for (var line : data) {
            var values = line.split(":")[1].split("\\|");

            List<Integer> winningNumbers = findIntegers(values[0]);
            List<Integer> scratchNumbers = findIntegers(values[1]);
            List<Integer> winners = scratchNumbers.stream().filter(winningNumbers::contains).toList();

            for (int i = 1; i < winners.size()+1; i++) {
                var oldCount = cardAmounts.get(getCardNumber(line)+i);
                cardAmounts.remove(getCardNumber(line)+i);
                cardAmounts.put(getCardNumber(line)+i, oldCount + cardAmounts.get(getCardNumber(line)));
            }

        }
        return cardAmounts.values().stream().reduce(0, Integer::sum);
    }

    private Integer getCardNumber(String line) {
        return findIntegers(line.split(":")[0]).get(0);
    }

    private List<Integer> findIntegers(String stringToSearch) {
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(stringToSearch);

        List<Integer> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(Integer.parseInt(matcher.group()));
        }

        return integerList;
    }

    private void GenerateCardAmountMap(ArrayList<String> data, Map<Integer, Integer> cardAmounts) {
        for (var card : data){
            cardAmounts.put(findIntegers(card.split(":")[0]).get(0), 1);
        }
    }
}
