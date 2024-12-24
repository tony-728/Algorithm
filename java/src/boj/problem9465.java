package boj;

import java.io.*;
import java.util.*;

public class problem9465 {

    /*
     * 2n개 스티커를 구해
     * 스티커는 2행 n열로 배치되어 있다.
     * 
     * 스티커 한장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다.
     * 스티커 점수의 합이 최대가 되게 스티커를 떼어내려고 한다.
     * 
     * 뗄 수 있는 스티커의 점수의 최대값을 구하라
     * - 즉, 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유하지 않는 스티커 집합을 구해야한다.
     * 
     * 떼어낸 스티커의 최대값을 구하는 문제
     * 
     * 완탐이 아니고 dp
     * - 완탐으로 풀면 시간초과 발생함
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfSticker;
    static int[][] sticker;
    static int[][] dp;
    static int answer;

    static void inputTestCase() throws IOException {

        answer = 0;

        numOfSticker = Integer.parseInt(br.readLine().trim());

        sticker = new int[2][numOfSticker + 1];
        dp = new int[2][numOfSticker + 1];

        for (int rowIdx = 0; rowIdx < 2; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 1; colIdx <= numOfSticker; colIdx++) {

                int val = Integer.parseInt(st.nextToken());

                sticker[rowIdx][colIdx] = val;

            }
        }

    }

    static void solution() {

        /*
         * 현재 위치에 스티커를 떼기 위해서는 1. 왼쪽 대각선 2. 왼쪽왼쪽 대각선에 있는 스티커가 붙어있을 수 있다.
         * dp[0][i] = Max(dp[1][i-1], dp[1][i-2]) + sticker[0][i]
         * dp[1][i] = Max(dp[0][i-1], dp[0][i-2]) + sticker[1][i]
         * 
         */

        dp[0][1] = sticker[0][1];
        dp[1][1] = sticker[1][1];

        for(int idx=2; idx<= numOfSticker; idx++){
            dp[0][idx] = Math.max(dp[1][idx-1], dp[1][idx-2]) + sticker[0][idx];
            dp[1][idx] = Math.max(dp[0][idx-1], dp[0][idx-2]) + sticker[1][idx];
        }

        answer = Math.max(dp[0][numOfSticker], dp[1][numOfSticker]);

    }


    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int testIdx = 0; testIdx < testCase; testIdx++) {

            inputTestCase();

            solution();

            sb.append(answer).append("\n");

        }

        System.out.println(sb);

    }
}
