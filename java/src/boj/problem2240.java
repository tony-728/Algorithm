package boj;

import java.io.*;
import java.util.*;

public class problem2240 {
    /*
     * 매 초마다 두 개의 나무 중 하나의 나무에서 열매가 떨어지게 된다.
     * 열매가 떨어지는 순간, 사람이 그 나무의 아래에 서 있으면 그 열매를 받아먹을 수 있다.
     * 하나의 나무 아래에 서 있다가 다른 나무 아래로 빠르게(1초 이내) 움직일 수 있다.
     * 
     * 자두는 T초(최대 1000초) 동안 떨어진다. 사람은 최대 W만큼만 움직이고 싶다. 매 초마다 어느 나무에서 자두가 떨어질지에 대한 정부가 주어질 때
     * 사람이 받을 수 있는 자두의 개수를 구하라
     * 
     * 사람은 1번 나무에 있다.
     * 
     * 재귀함수 -> 시간초과
     * bfs -> 메모리초과
     * 
     * dp
     * - 사람이 움직이는 것을 점화식에 어떻게 녹여야할지 떠올리지 못했다
     * - 2차원 dp [시간][움직인 횟수]
     *  - 움직인 횟수가 짝수면 위치 1에 있다.
     *  - 움직인 횟수가 홀수면 위치 2에 있다.
     *  - 움직인 횟수를 통해 현재 위치를 구한다.
     * 
     * - 3차원 dp [위치][시간][움직인 횟수]
     */

    static int totalPlum;
    static int totalMove;

    static int[] arrOfPlum;
    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        totalPlum = Integer.parseInt(st.nextToken());
        totalMove = Integer.parseInt(st.nextToken());

        arrOfPlum = new int[totalPlum + 1];

        for (int idx = 1; idx < totalPlum + 1; idx++) {
            arrOfPlum[idx] = Integer.parseInt(br.readLine().trim());
        }
    }

    static void solution() {

        // 위치를 표현하는게 직관적이어서 3차원으로 풀이
        int[][][] dp = new int[3][totalPlum + 1][totalMove + 2];

        for (int plumIdx = 1; plumIdx < totalPlum + 1; plumIdx++) {
            for (int moveIdx = 1; moveIdx < totalMove + 2; moveIdx++) {

                // 현재 자두가 위치 1에 있다.
                if (arrOfPlum[plumIdx] == 1) {
                    // 위치 1에 있을 때
                    dp[1][plumIdx][moveIdx] =
                            Math.max(dp[1][plumIdx - 1][moveIdx], dp[2][plumIdx - 1][moveIdx - 1])
                                    + 1;

                    // 위치 2에 있을 때
                    dp[2][plumIdx][moveIdx] =
                            Math.max(dp[2][plumIdx - 1][moveIdx], dp[1][plumIdx - 1][moveIdx - 1]);


                    // 현재 자두가 위치 2에 있다.
                } else {
                    // 사람의 초기 위치는 1이기 때문에 plumIdx==1 && moveIdx==1인 경우는 확인하지 않는다.
                    if (plumIdx == 1 && moveIdx == 1) {
                        continue;
                    }

                    // 위치 1에 있을 때
                    dp[1][plumIdx][moveIdx] =
                            Math.max(dp[1][plumIdx - 1][moveIdx], dp[2][plumIdx - 1][moveIdx - 1]);


                    // 위치 2에 있을 때
                    dp[2][plumIdx][moveIdx] =
                            Math.max(dp[2][plumIdx - 1][moveIdx], dp[1][plumIdx - 1][moveIdx - 1])
                                    + 1;
                }


            }
        }

        for (int idx = 1; idx < totalMove + 2; idx++) {
            answer = Math.max(answer, Math.max(dp[1][totalPlum][idx], dp[2][totalPlum][idx]));
        }
    }

    static void solution2() {
        int[][] dp = new int[totalPlum + 1][totalMove + 1];

        for (int plumIdx = 1; plumIdx < totalPlum + 1; plumIdx++) {
            for (int moveIdx = 0; moveIdx < totalMove + 1; moveIdx++) {
                // 이동하지 않음
                // 초기 위치 1에 사람이 있다.
                if (moveIdx == 0) {
                    if (arrOfPlum[plumIdx] == 1) {
                        dp[plumIdx][moveIdx] = dp[plumIdx - 1][moveIdx] + 1;
                    } else {
                        dp[plumIdx][moveIdx] = dp[plumIdx - 1][moveIdx];
                    }
                    continue;
                }

                // 짝수번 이동 현재 위치 1
                if (moveIdx % 2 == 0) {

                    // 자두가 1에 있다.
                    if (arrOfPlum[plumIdx] == 1) {
                        dp[plumIdx][moveIdx] = Math.max(dp[plumIdx - 1][moveIdx] + 1,
                                dp[plumIdx - 1][moveIdx - 1]);

                        // 자두가 2에 있다.
                    } else {
                        dp[plumIdx][moveIdx] = Math.max(dp[plumIdx - 1][moveIdx],
                                dp[plumIdx - 1][moveIdx - 1] + 1);
                    }

                    // 홀수번 이동 현재 위치 2
                } else {

                    // 자두가 1에 있다.
                    if (arrOfPlum[plumIdx] == 2) {
                        dp[plumIdx][moveIdx] = Math.max(dp[plumIdx - 1][moveIdx] + 1,
                                dp[plumIdx - 1][moveIdx - 1]);

                        // 자두가 2에 있다.
                    } else {
                        dp[plumIdx][moveIdx] = Math.max(dp[plumIdx - 1][moveIdx],
                                dp[plumIdx - 1][moveIdx - 1] + 1);
                    }
                }

                answer = Math.max(answer, dp[plumIdx][moveIdx]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution2();

        System.out.println(answer);

    }
}
