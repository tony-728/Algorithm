package boj;

import java.io.*;

public class problem9252 {
    /*
     * LCS: 최장 공통 부분 수열 문제
     * 
     * dp를 활용한 문제
     * - 최장 공통 부분 수열의 길이를 구하는 문제였으면 더 간단할 수도 있었다.
     * - 최장 공통 부분 수열의 문자열까지 구해야하기 때문에 dp table에서 백트래킹으로 문자을 찾아가야 한다.
     * 
     * 기본 점화식 아이디어
     * - i, j 시점에서 character를 추가하나 마냐
     * 1. 추가하는 경우: s1 문자와 s2 문자가 같다
     * - dp[i][j] = dp[i-1][j-1] + 1
     * - 왜냐하면 ABC, GBC에 경우 C라는 문자가 새롭게 추가된다. 
     * - C는 두 문자열에 공통으로 추가된다 따라서 AB, GB에 문자열의 최장 공통 부분수열을 확인하면 된다.
     * 2. 추가하지 않는 경우: 문자가 서로 다르다
     * - dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * - 왜냐하면 부분수열은 연속된 값이 아니다 때문에 현재의 문자를 비교하는 과정 이전의 최대 공통부분수열은 계속 유지된다.
     * - 현재 문자를 비교하는 과정 이전의 과정이 dp[i-1][j], dp[i][j-1]이다.
     *
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] dp;
    static String left, right;

    static int answer;

    public static void main(String[] args) throws IOException {
        left = br.readLine().trim();
        right = br.readLine().trim();

        left = " " + left;
        right = " " + right;

        dp = new int[left.length()][right.length()];

        // 최장 공통 부분 수열의 길이를 구하는 과정
        for (int leftIdx = 1; leftIdx < left.length(); leftIdx++) {
            for (int rightIdx = 1; rightIdx < right.length(); rightIdx++) {
                if (left.charAt(leftIdx) == right.charAt(rightIdx)) {
                    dp[leftIdx][rightIdx] = dp[leftIdx - 1][rightIdx - 1] + 1;
                } else {
                    dp[leftIdx][rightIdx] =
                            Math.max(dp[leftIdx - 1][rightIdx], dp[leftIdx][rightIdx - 1]);
                }
            }
        }

        // 최장 공통 부분 수열의 문자열을 구하는 과정
        int leftIdx = left.length() - 1;
        int rightIdx = right.length() - 1;

        answer = dp[leftIdx][rightIdx];

        while (dp[leftIdx][rightIdx] != 0) {
            if (dp[leftIdx][rightIdx] == dp[leftIdx - 1][rightIdx]) {
                leftIdx--;
            } else if (dp[leftIdx][rightIdx] == dp[leftIdx][rightIdx - 1]) {
                rightIdx--;
            } else {
                sb.append(left.charAt(leftIdx));
                leftIdx--;
                rightIdx--;
            }
        }

        System.out.println(answer);
        System.out.println(sb.reverse());
    }
}
