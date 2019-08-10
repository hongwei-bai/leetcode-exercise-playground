package hongwei.leetcode.playground.other.monday;

public class AlgoTest1 {

    public String combine(String a, String b) {

        int posA = a.length() - 1;
        int posB = 0;

        while (posB < b.length() && posA >= 0) {
            char tailOfStringA = a.charAt(posA);
            char headOfStringB = b.charAt(posB);

            if (tailOfStringA != headOfStringB) {
                String aPart = a.substring(0, posA + 1);
                String bPart = b.substring(posB, b.length());
                return aPart.concat(bPart);
            }

            posA--;
            posB++;
        }

        if (posA >= 0) {
            return a.substring(0, posA + 1);
        } else {
            return b.substring(posB, b.length());
        }
    }
}
