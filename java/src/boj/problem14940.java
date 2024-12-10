package boj;

import java.io.*;
import java.util.*;

public class problem14940 {
    /*
     * 모든 지점에 대해서 목표지점까지의 거리를 구하라
     * 
     * 가로와 세로로만 움직일 수 있다.
     * 
     * 지도의 크기 n(세로), m(가로)이 주어진다.
     * - 0은 갈 수 없는 땅
     * - 1은 갈 수 있는 땅
     * - 2는 목표지점, 단 한개
     * 
     * bfs
     * 
     * 도달할 수 없는 위치는 -1
     */

    static class Location {
        int rowIdx;
        int colIdx;
        int cost;

        public Location(int rowIdx, int colIdx, int cost) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.cost = cost;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int rowSize, colSize;
    static int[][] map;
    static int[][] answer;
    static boolean[][] visited;

    static Deque<Location> q = new ArrayDeque<>();

    static final int GOAL = 2;
    static final int CAN_GO = 1;
    static final int CANT_GO = 0;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        answer = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {

            st = new StringTokenizer(br.readLine().trim());

            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                int val = Integer.parseInt(st.nextToken());

                map[rowIdx][colIdx] = val;
                answer[rowIdx][colIdx] = -1;

                if (val == GOAL) {
                    q.addLast(new Location(rowIdx, colIdx, 0));
                    visited[rowIdx][colIdx] = true;
                    answer[rowIdx][colIdx] = 0;
                }

                if (val == CANT_GO) {
                    answer[rowIdx][colIdx] = CANT_GO;
                }
            }
        }
    }

    static boolean checkBoundary(int row, int col) {
        if (row > -1 && row < rowSize && col > -1 && col < colSize) {
            return true;
        } else {
            return false;
        }
    }

    static void solution() {

        while (!q.isEmpty()) {
            Location loc = q.pollFirst();

            int rowIdx = loc.rowIdx;
            int colIdx = loc.colIdx;
            int cost = loc.cost;

            for (int idx = 0; idx < dx.length; idx++) {
                int newRow = rowIdx + dx[idx];
                int newCol = colIdx + dy[idx];


                // 범위 안 , 갈 수 있음, 방문하지 않음
                if (checkBoundary(newRow, newCol) && map[newRow][newCol] == CAN_GO
                        && visited[newRow][newCol] == false) {

                    // 큐에 추가
                    q.addLast(new Location(newRow, newCol, cost + 1));
                    // 방문처리
                    visited[newRow][newCol] = true;
                    // 목표지점까지의 거리 
                    answer[newRow][newCol] = cost + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                sb.append(answer[rowIdx][colIdx]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
