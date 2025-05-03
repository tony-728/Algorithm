package boj;

import java.io.*;

public class problem15988 {

    /*
     * 
     * 주어진 정수를 1,2,3의 합으로 나타낼 수 있는 방법을 구하라
     * 
     * 처음에는 재귀로 문제를 풀려고 했으나 시간초과발생
     * 
     * dp...
     * 
     */

    static final int INF = 1_000_000_009;
    static long[] dp;

    static StringBuilder sb = new StringBuilder();

    static void solution() {

        // dp[i] = a , 인덱스 i를 만들 수 있는 방법
        // dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        dp = new long[1_000_000 + 1];

        dp[1] = 1L;
        dp[2] = 2L;
        dp[3] = 4L;

        for (int idx = 4; idx <= 1_000_000; idx++) {
            dp[idx] = (dp[idx - 1] + dp[idx - 2] + dp[idx - 3]) % INF;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine().trim());

        solution();

        for (int tc = 0; tc < testCase; tc++) {
            int value = Integer.parseInt(br.readLine().trim());

            sb.append(dp[value]).append("\n");
        }

        System.out.println(sb);
    }
}
