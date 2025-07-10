package boj;

import java.io.*;

public class problem17626 {
    /**
     * 자연수는 넷 혹은 그 이하의 제곱수의 합으로 표현할 수 있다.
     * 자연수 n이 주어질 때 n을 최소 개수의 제곱수 합으로 표현하라
     * 
     * 중복조합 4개, 3개, 2개, 1개 -> 시간초과
     * 
     * 완탐도 가능 n이 크지 않고 제곱수를 사용하기 때문에 가능하다
     * dp (오른쪽은 제곱)
     * - 1 = 1, 1개
     * - 2 = 1 + 1, 2개
     * - 3 = 1 + 1 + 1, 3개
     * - 4 = 2, 1개
     * - 5 = 2 + 1, 2개
     * - 6 = 2 + 1 + 1, 3개
     * - 7 = 2 + 1 + 1 + 1, 4개
     * - 8 = 2 + 2, 2개
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        int[] dp = new int[N + 1];

        // 인덱스를 만들 수 있는 제곱수들의 최소 개수값
        dp[1] = 1;

        int min = 0;

        // dp를 만들어감
        for (int idx = 2; idx < N + 1; idx++) {
            min = Integer.MAX_VALUE;

            // 1부터 idx까지 제곱수에 대해서 확인한다.
            for (int value = 1; value * value < idx + 1; value++) {
                min = Math.min(dp[idx - (value * value)], min);
            }

            dp[idx] = min + 1;
        }

        System.out.println(dp[N]);
    }
}
