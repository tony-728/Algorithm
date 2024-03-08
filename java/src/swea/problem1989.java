package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase <= T; testCase++) {
            int answer = 0;
            String str = br.readLine().trim();
            char[] charS = new char[str.length()];

            int reverseIdx = 0;

            // 입력받은 문자열을 뒤집음
            for (int idx = str.length() - 1; idx > -1; idx--) {
                charS[reverseIdx++] = str.charAt(idx);
            }

            String reversStr = String.valueOf(charS);

            if (str.equals(reversStr)) {
                answer = 1;
            }

            System.out.println("#" + testCase + " " + answer);
        }
    }
}
