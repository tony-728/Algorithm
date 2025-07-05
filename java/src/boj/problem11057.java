package boj;

import java.io.*;
import java.util.*;

public class problem11057 {
    /**
     * 오르막 수를 구하라
     * 
     * dp[자리수][0~9]
     * 
     *         0 1 2 3 4 5 6 7 8 9
     * 자리수 1 1 1 1 1 1 1 1 1 1 1
     * 자리수 2 10 9 8 7 6 5 4 3 2 1
     * 자리수 3 55 45 ...
     * 
     * dp
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[][] dp = new int[n + 1][10];

        Arrays.fill(dp[1], 1);

        for (int idx = 2; idx < n + 1; idx++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[idx][j] = (dp[idx][j] + dp[idx - 1][k]) % 10007;
                }
            }
        }

        int answer = 0;
        for (int idx = 0; idx < 10; idx++) {
            answer = (answer + dp[n][idx]) % 10007;
        }

        System.out.println(answer);
    }
}
