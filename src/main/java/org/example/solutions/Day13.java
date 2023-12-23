package org.example.solutions;

import org.example.utils.MyFileReader;

import java.util.ArrayList;
import java.util.List;

public class Day13 implements ISolution{
    @Override
    public long solutionPart1() {
        String[] lines = MyFileReader.ReadFromFileArray("_Resources/Day13.txt");
        List<List<String>> grids = generateMap(lines);

        int mirrorCols = 0;
        int mirrorRows = 0;

        for (List<String> grid : grids) {
            int maybeCol = findMirrorColumns(grid, 0);
            if (maybeCol > -1) {
                mirrorCols += maybeCol + 1;
                continue;
            }
            int row = getMirrorRows(grid, 0);
            mirrorRows += row + 1;
        }

        return mirrorCols + (100 * mirrorRows);
    }

    @Override
    public long solutionPart2() {
        String[] lines = MyFileReader.ReadFromFileArray("_Resources/Day13.txt");
        List<List<String>> grids = generateMap(lines);

        int mirrorCols = 0;
        int mirrorRows = 0;

        for (List<String> grid : grids) {
            int maybeCol = findMirrorColumns(grid, 1);
            if (maybeCol > -1) {
                mirrorCols += maybeCol + 1;
                continue;
            }
            int row = getMirrorRows(grid, 1);
            mirrorRows += row + 1;
        }

        return mirrorCols + (100 * mirrorRows);
    }

    private int getMirrorRows(List<String> grid, int diff) {
        int numRows = grid.size() - 1;
        for (int i = 0; i < numRows; i++) {
            if (hasMirrorRow(grid, i, diff)) {
                return i;
            }
        }
        return -1;
    }

    private boolean hasMirrorRow(List<String> grid, int row, int diff) {
        int top = row;
        int bottom = row + 1;
        var off = 0;

        while (top >= 0 && bottom < grid.size()) {
            var topRow = grid.get(top);
            var bottomRow = grid.get(bottom);
            if (diff > 0) {
                for (int i = 0; i < topRow.length(); i++) {
                    if (topRow.charAt(i) != bottomRow.charAt(i)) {
                        if (off >= diff) {
                            return false;
                        }
                        off++;
                    }
                }
            } else if (!topRow.equals(bottomRow)) {
                return false;
            }

            top--;
            bottom++;
        }

        if (diff > 0) {
            return off == diff;
        }
        return true;
    }

    private int findMirrorColumns(List<String> grid, int diff) {
        for (int i = 0; i < grid.get(0).length() - 1; i++) {
            if (hasMirrorColumn(grid, i, diff)) {
                return i;
            }
        }
        return -1;
    }

    private boolean hasMirrorColumn(List<String> grid, int column, int diff) {
        var off = 0;
        for (var row : grid) {
            int left = column;
            int right = column + 1;
            while (left >= 0 && right < row.length()) {
                if (row.charAt(left) != row.charAt(right)) {
                    if (off >= diff) {
                        return false;
                    }
                    off++;
                }
                left--;
                right++;
            }
        }

        if (diff > 0) {
            return off == diff;
        }
        return true;
    }

    private List<List<String>> generateMap(String[] lines) {
        var res = new ArrayList<List<String>>();

        var current = new ArrayList<String>();
        for (String s : lines) {
            if (s.equals("")) {
                res.add(new ArrayList<>(current));
                current.clear();
            } else {
                current.add(s);
            }
        }
        res.add(new ArrayList<>(current));

        return res;
    }
}
