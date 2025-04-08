package boj;

import java.io.*;
import java.util.*;

public class problem1103 {
    /*
     * 1-9까지 숫자와 구멍이 있는 직사각형 보드가 있다.
     * 
     * 게임
     * - 보드의 가장 왼쪽 위에 동전을 하나 놓는다.
     * 1. 동전이 있는 곳에 쓰여 있는 숫자에 대해서
     * 2. 상하좌우 중 한가지를 고른다.
     * 3. 동전을 고른 방향으로 숫자만큼 움직인다. 중간에 있는 구멍은 무시한다.
     * 
     * 동전이 구멍에 빠지거나 보드의 바깥으로 나가면 게임은 종료된다.
     * 
     * 게임을 최대한 오래할 때 최대 몇 번 동전을 움직일 수 있는지 구하라
     * 
     * bfs -> 틀림
     * dfs + dp
     * - 상하좌우 중 하나를 선택하기 때문에 dfs
     * - 메모이제이션으로 최대이동 유지
     */

    static int rowSize, colSize;
    static int[][] board;
    static int[][] dp;
    static boolean[][] visited;

    static int answer;

    static boolean isCycle;

    static final int EMPTY = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 사이클 확인
        isCycle = false;

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        board = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        dp = new int[rowSize][colSize];
        dp[0][0] = 1;
        visited[0][0] = true;

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                char value = row.charAt(colIdx);

                if (Character.isDigit(value)) {
                    board[rowIdx][colIdx] = row.charAt(colIdx) - '0';
                }
            }
        }

    }

    static void solution(int rowIdx, int colIdx, int count) {
        if (isCycle) {
            return;
        }

        // 최대값 갱신
        if (count > answer) {
            answer = count;
        }

        dp[rowIdx][colIdx] = count;

        for (int dir = 0; dir < dx.length; dir++) {
            int move = board[rowIdx][colIdx];

            int newRow = rowIdx + dx[dir] * move;
            int newCol = colIdx + dy[dir] * move;

            // 범위 체크
            if (0 > newRow || newRow >= rowSize || 0 > newCol || newCol >= colSize) {
                continue;
            }

            // 구멍을 밟는 경우
            if (board[newRow][newCol] == EMPTY) {
                continue;
            }

            // 이미 더 많은 움직임으로 탐색한 위치는 다시 탐색하지 않는다.
            if (dp[newRow][newCol] > count) {
                continue;
            }

            // 방문한 곳을 탐색하면 사이클 발생
            if (visited[newRow][newCol]) {
                isCycle = true;
                return;
            }

            visited[newRow][newCol] = true;
            solution(newRow, newCol, count + 1);
            visited[newRow][newCol] = false;
        }


    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution(0, 0, 1);

        System.out.println(isCycle ? -1 : answer);
    }
}
