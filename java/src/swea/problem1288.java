package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class problem1288 {
    public static boolean numberCheck(boolean[] check) {
        for (int i = 0; i < check.length; i++) {
            if (check[i] == false) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase <= T; testCase++) {
            boolean[] numCheck = new boolean[10];

            int N = Integer.parseInt(br.readLine().trim());
            int temp = N;
            while (true) {
                String strNumber = Integer.toString(temp);

                // N * loop 에서 있는 숫자 확인
                for (int idx = 0; idx < strNumber.length(); idx++) {
                    numCheck[(int) strNumber.charAt(idx) - '0'] = true;
                }

                if (numberCheck(numCheck)) {
                    break;
                }

                // N의 배수
                temp += N;
            }
            System.out.println("#" + testCase + " " + temp);
        }
    }
}
