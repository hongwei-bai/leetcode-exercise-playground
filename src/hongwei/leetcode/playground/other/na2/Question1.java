package hongwei.leetcode.playground.other.na2;

public class Question1 {
    public String canBePalindromeByChangingOneCharacter(String s) {
        int halfLen = s.length() / 2;
        int unsymmetryCounter = 0;
        for (int i = 0; i < halfLen; i++) {
            char cLelf = s.charAt(i);
            char cRight = s.charAt(s.length() - i - 1);
            if (cLelf != cRight) {
                unsymmetryCounter++;
                if (unsymmetryCounter > 1) {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}
