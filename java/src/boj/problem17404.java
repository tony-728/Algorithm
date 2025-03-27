package boj;

import java.io.*;
import java.util.*;

public class problem17404 {
    /*
     * 집이 N개가 있다. 
     * - 거리는 선분으로 나타낼 수 있고 1번부터 N번이 순서대로 있다.
     * 
     * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
     * - 각각의 집을 빨,초,파로 칠하는 비용이 주어졌다.
     * - 모든 집을 칠하는 비용의 최소값을 구하라
     * 
     * 1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
     * N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
     * i(2<= i <= N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.
     * 
     * N이 최대 1000이므로 n^2 가능
     */

    static int numOfHome;
    static int[][] arrOfCost;
    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;

    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 987654321;

        numOfHome = Integer.parseInt(br.readLine().trim());

        arrOfCost = new int[numOfHome + 1][3];

        for (int idx = 1; idx < numOfHome + 1; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int costIdx = 0; costIdx < 3; costIdx++) {
                arrOfCost[idx][costIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solution() {

        int[][] dp = new int[numOfHome + 1][3];

        // 첫번째 집을 색칠하는 기준
        for (int color = 0; color < 3; color++) {

            for (int idx = 0; idx < 3; idx++) {
                // 첫번째 집에 color를 색칠함
                if (color == idx) {
                    dp[1][idx] = arrOfCost[1][idx];

                    // 나머지 집에는 색칠안함
                } else {
                    dp[1][idx] = 987654321;
                }
            }

            for (int idx = 2; idx < numOfHome + 1; idx++) {
                // idx에 빨간 집을 칠했다
                // 빨간색을 칠하면 초록, 파랑
                dp[idx][0] = Math.min(dp[idx - 1][1], dp[idx - 1][2]) + arrOfCost[idx][0];

                // idx에 초록 집을 칠했다
                // 초록을 칠하면 빨강, 파랑
                dp[idx][1] = Math.min(dp[idx - 1][0], dp[idx - 1][2]) + arrOfCost[idx][1];

                // idx에 파란 집을 칠했다
                // 파랑을 칠하면 빨강, 초록
                dp[idx][2] = Math.min(dp[idx - 1][0], dp[idx - 1][1]) + arrOfCost[idx][2];

                // 마지막 집
                if (idx == numOfHome) {
                    if (color == RED) { // 첫번째 집이 빨강, 초록 파랑 중 선택
                        answer = Math.min(answer, Math.min(dp[idx][1], dp[idx][2]));
                    }

                    if (color == GREEN) { // 첫번쨰 집이 초록, 빨강 파랑 중 선택
                        answer = Math.min(answer, Math.min(dp[idx][0], dp[idx][2]));
                    }

                    if (color == BLUE) { // 첫번째 집이 파랑, 빨강 초록 중 선택
                        answer = Math.min(answer, Math.min(dp[idx][0], dp[idx][1]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
