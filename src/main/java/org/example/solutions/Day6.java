package org.example.solutions;

import org.example.utils.MyFileReader;
import org.example.utils.NumberUtil;
import org.example.utils.Tuple;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day6 implements ISolution {

    @Override
    public int solutionPart1() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day6.txt");

        List<String> times = NumberUtil.GetNumbers(data.get(0));
        List<String> distances = NumberUtil.GetNumbers(data.get(1));

        List<Tuple<Integer, Long>> races = getRaces(times, distances);

        int ans = 1;
        for (Tuple<Integer, Long> race : races) {
            int winningWaits = getWinningWaits(race).size();
            if (winningWaits > 0) {
                ans *= winningWaits;
            }
        }

        return ans;
    }

    @Override
    public int solutionPart2() {
        List<String> data = MyFileReader.ReadFromFile("_Resources/Day6.txt");
        int times = Integer.parseInt(NumberUtil.getAllNumbersInLineCombined(data.get(0)));
        long distances = Long.parseLong(NumberUtil.getAllNumbersInLineCombined(data.get(1)));

        Tuple<Integer, Long> race = new Tuple<>(times, distances);

        return getWinningWaits(race).size();
    }

    private List<Long> getWinningWaits(Tuple<Integer, Long> race) {
        List<Long> waits = new ArrayList<>();
        for (long i = 0; i < race.item1; i++) {
            long time = race.item1 - i;
            long distance = (i * time);
            if(distance > race.item2)
                waits.add(i);
        }
        return waits;
    }

    private List<Tuple<Integer, Long>> getRaces(List<String> times, List<String> distances) {

        List<Tuple<Integer, Long>> races = new ArrayList<>();

        for (int i = 0; i < times.size(); i++) {
            races.add(new Tuple<>(Integer.parseInt(times.get(i)), Long.parseLong(distances.get(i))));
        }
        return races;
    }
}