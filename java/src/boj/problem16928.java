package boj;

import java.util.*;
import java.io.*;

public class problem16928 {
    /*
     * 주사위를 조작해 내가 원하는 수가 나오게 만들면 최소 몇 번만에 도착점에 도착할 수 있을까?
     * 
     * 게임은 크기가 10x10
     * - 보드판에는 1부터 100까지 수가 하나씩 순서대로 적혀져 있다.
     * 
     * 주사위를 굴려 나온 수만큼 이동한다.
     * - 현재 i칸에 있고 4가 나오면 i+4로 이동
     * - 주사위를 굴린 결과가 100번칸을 넘기면 이동할 수 없다.
     * - 도착칸이 사다리면 사다리를 타고 위로 올라간다.
     * - 뱀이 있는 칸에 도착하면 뱀을 따라서 내려가게 된다.
     * 
     * 1번칸에서 100번칸에 도착하는 것이 목표
     * 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최소값
     * 
     * dp로 해결할 수 있다.
     * - 초기 워프 세팅을 한 이유: 뱀을 이용한 최단일 경우가 있다.
     * 워프 세팅을 한 번만 하면 뱀으로 이동한 후 이동을 고려할 수 없다.(내가 짠 로직에서는...)
     * 
     */

    static class Warp {
        int start;
        int end;

        public Warp(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX_SIZE = 101;

    static int numOfLadder;
    static int numOfSnake;

    static Warp[] warpList = new Warp[MAX_SIZE];

    static final int[] dp = new int[MAX_SIZE];

    static int answer;

    static void inputData() throws IOException {

        Arrays.fill(dp, 100);

        st = new StringTokenizer(br.readLine().trim());

        numOfLadder = Integer.parseInt(st.nextToken());
        numOfSnake = Integer.parseInt(st.nextToken());

        int start;
        int end;

        // 사다리 위치 저장
        for (int idx = 0; idx < numOfLadder; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            warpList[start] = new Warp(start, end);
        }

        // 뱀 위치 저장
        for (int idx = 0; idx < numOfSnake; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            warpList[start] = new Warp(start, end);

        }
    }

    static void solution() {

        // 초기 세팅
        for (int idx = 2; idx <= 7; idx++) {
            // 워프가 있는 경우
            if (warpList[idx] != null && warpList[idx].start < warpList[idx].end) {
                dp[warpList[idx].end] = 1;
            }
            dp[idx] = 1;
        }

        // 초기 워프 세팅
        for (int idx = 8; idx < MAX_SIZE; idx++) {

            // 워프가 있는 경우
            if (warpList[idx] != null) {
                for (int inner = 1; inner < 7; inner++) {
                    dp[warpList[idx].end] = Math.min(1 + dp[idx - inner],
                            Math.min(dp[warpList[idx].start], dp[warpList[idx].end]));
                }
            } else {
                for (int inner = 1; inner < 7; inner++) {
                    dp[idx] = Math.min(1 + dp[idx - inner], dp[idx]);
                }
            }
        }


        for (int idx = 8; idx < MAX_SIZE; idx++) {
            // 워프가 있는 경우
            if (warpList[idx] != null) {
                for (int inner = 1; inner < 7; inner++) {
                    dp[warpList[idx].end] = Math.min(1 + dp[idx - inner],
                            Math.min(dp[warpList[idx].start], dp[warpList[idx].end]));
                }
            } else {
                for (int inner = 1; inner < 7; inner++) {
                    dp[idx] = Math.min(1 + dp[idx - inner], dp[idx]);
                }

            }
        }


    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        // for (int idx = 1; idx < MAX_SIZE; idx++) {
        //     System.out.print(dp[idx] + "\t");
        //     if (idx % 10 == 0) {
        //         System.out.println();
        //     }
        // }

        System.out.println(dp[100]);

    }
}
