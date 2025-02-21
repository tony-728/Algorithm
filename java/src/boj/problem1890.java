package boj;

import java.io.*;
import java.util.*;

public class problem1890 {
    /*
     * NxN 게임판이 있다.
     * - 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 점프를 해서 가는 것
     * - 각 칸에 적혀있는 수는 현재 칸에서 갈 수 있는 거리를 의미한다.
     * - 반드시 오른쪽이나 아래쪽으로만 이동해야 한다.
     * - 0은 종착점
     * - 항상 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로 가야 한다.
     * 
     * 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 이동할 수 있는 경로의 개수를 구하라
     * 
     * dp
     * - dp 테이블을 이용해서 시작 위치에서 이동할 수 있는 점을 bottom-up 방식으로 탐색한다.
     * - 이동할 수 있는 위치에 대해서 카운트
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int mapSize;
    static int[][] map;
    static long[][] dp;

    static final int GOAL = 0;

    // 우, 하
    static final int[] dx = {0, 1};
    static final int[] dy = {1, 0};

    static void inputData() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];
        dp = new long[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solution() {

        dp[0][0] = 1;

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {

                // 도착점에 오면 검사하지 않는다.
                if (rowIdx == mapSize - 1 && colIdx == mapSize - 1) {
                    continue;
                }

                // 이동한 흔적이 있으면 이동한다.
                if (dp[rowIdx][colIdx] > 0) {
                    for (int dir = 0; dir < dx.length; dir++) {
                        int newRow = rowIdx + (dx[dir] * map[rowIdx][colIdx]);
                        int newCol = colIdx + (dy[dir] * map[rowIdx][colIdx]);

                        // 범위 체크
                        if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
                            continue;
                        }

                        // 이전 경로의 개수를 다음 이동 위치에 더한다.
                        dp[newRow][newCol] += dp[rowIdx][colIdx];
                    }

                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(dp[mapSize - 1][mapSize - 1]);
    }
}
