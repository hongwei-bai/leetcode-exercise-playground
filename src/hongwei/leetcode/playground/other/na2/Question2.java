package hongwei.leetcode.playground.other.na2;

public class Question2 {
    public int calculateMinimumMove(String input) {
        String[] arrayString = parseInput(input);
        int arrayMove[] = generateMoveArray(arrayString);
        if (null == arrayMove) {
            return -1;
        }
        return bruteMinimum(arrayMove, arrayString[0].length());
    }

    private int bruteMinimum(int[] arrayMove, int stringLength) {
        int[] resultArray = new int[arrayMove.length];
        int tmpCount = 0;
        for (int baseId = 0; baseId < arrayMove.length; baseId++) {
            tmpCount = 0;
            int baseMoveNumber = arrayMove[baseId];
            for (int i = 0; i < arrayMove.length; i++) {
                int move = arrayMove[i] - baseMoveNumber;
                if (move < 0) {
                    move += stringLength;
                }
                tmpCount += move;
            }
            resultArray[baseId] = tmpCount;
        }

        // find minimum result in array
        int min = resultArray[0];
        for (int i = 1; i < resultArray.length; i++) {
            if (min > resultArray[i]) {
                min = resultArray[i];
            }
        }
        return min;
    }

    private String[] parseInput(String input) {
        String[] lines = input.split("\n");
        final int STRING_NUMBER = Integer.valueOf(lines[0]);
        String[] arrayString = new String[STRING_NUMBER];
        for (int i = 0; i < lines.length - 1; i++) {
            arrayString[i] = lines[i + 1];
        }
        return arrayString;
    }

    private int[] generateMoveArray(String[] arrayString) {
        String base = arrayString[0];
        int[] moveArray = new int[arrayString.length];
        moveArray[0] = 0;
        for (int i = 1; i < arrayString.length; i++) {
            moveArray[i] = calcMove(base, arrayString[i]);
            if (-1 == moveArray[i]) {
                return null;
            }
        }
        return moveArray;
    }

    private int calcMove(String base, String s) {
        if (base.equalsIgnoreCase(s)) {
            return 0;
        }
        final int LENGTH = base.length();
        for (int i = 1; i < LENGTH; i++) {
            if (base.charAt(0) == s.charAt(i)) {
                if (equalCompareLoop(base, s, i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean equalCompareLoop(String base, String s, int sStart) {
        String newStringS = s.substring(sStart, s.length()).concat(s.substring(0, sStart));
        return base.equalsIgnoreCase(newStringS);
    }
}
