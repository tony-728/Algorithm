package boj;

import java.io.*;
import java.util.*;

public class problem16956 {
    /*
     * RxC 목장이 있다.
     * - 비어있거나, 양 또는 늑대가 있다.
     * - 양은 이동하지 않는다.
     * - 늑대는 인접한 한 칸을 자유롭게 이동할 수 있다.
     * 
     * 목장에 울타리를 설치해 늑대가 양이 있는 칸으로 갈 수 없게 하려고 한다.
     * 늑대는 울타리가 있는 칸으로 이동할 수 없다.
     * 
     * 완탐
     */

    static class Location {
        int rowIdx;
        int colIdx;

        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int rowSize, colSize;
    static char[][] map;
    static int answer;

    static final char EMPTY = '.';
    static final char SHEEP = 'S';
    static final char WOLF = 'W';
    static final char FENCE = 'D';

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static Deque<Location> sheepList = new ArrayDeque<>();
    static Deque<Location> wolfList = new ArrayDeque<>();

    static void inputData() throws IOException {

        answer = 1;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String v = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                char loc = v.charAt(colIdx);

                if (loc == SHEEP) {
                    sheepList.addLast(new Location(rowIdx, colIdx));
                }

                if (loc == WOLF) {
                    wolfList.addLast(new Location(rowIdx, colIdx));
                }

                map[rowIdx][colIdx] = loc;
            }
        }
    }

    static boolean checkBoundary(int row, int col) {
        if (0 <= row && row < rowSize && 0 <= col && col < colSize) {
            return true;
        }

        return false;
    }

    static void solution() {

        outLoop: while (!sheepList.isEmpty()) {
            Location sheep = sheepList.pollFirst();

            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = sheep.rowIdx + dx[dir];
                int newCol = sheep.colIdx + dy[dir];

                // 범위 체크
                if (!checkBoundary(newRow, newCol)) {
                    continue;
                }

                // 다른 양이 있는지 체크
                if (map[newRow][newCol] == SHEEP) {
                    continue;
                }

                // 이미 울타리를 쳤는지 체크
                if (map[newRow][newCol] == FENCE) {
                    continue;
                }

                // 늑대가 있는지 체크
                if (map[newRow][newCol] == WOLF) {
                    answer = 0;
                    break outLoop;
                }

                map[newRow][newCol] = FENCE;
            }
        }

        if (answer == 0) {
            return;
        }

        // 늑대로 확인
        outLoop: while (!wolfList.isEmpty()) {

            boolean[][] visited = new boolean[rowSize][colSize];
            Location start = wolfList.pollFirst();

            Deque<Location> q = new ArrayDeque<>();

            q.addLast(start);
            visited[start.rowIdx][start.colIdx] = true;

            while (!q.isEmpty()) {
                Location wolf = q.pollFirst();

                for (int dir = 0; dir < dx.length; dir++) {
                    int newRow = wolf.rowIdx + dx[dir];
                    int newCol = wolf.colIdx + dy[dir];

                    // 범위 체크
                    if (!checkBoundary(newRow, newCol)) {
                        continue;
                    }

                    // 방문처리
                    if (visited[newRow][newCol]) {
                        continue;
                    }

                    // 늑대가 있는지 체크
                    if (map[newRow][newCol] == WOLF) {
                        continue;
                    }

                    // 울타리가 있는지 체크
                    if (map[newRow][newCol] == FENCE) {
                        continue;
                    }

                    // 다른 양이 있는지 체크
                    if (map[newRow][newCol] == SHEEP) {
                        answer = 0;
                        break outLoop;
                    }

                    visited[newRow][newCol] = true;
                    q.addLast(new Location(newRow, newCol));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);

        if (answer == 1) {
            // 목장 출력하기
            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                for (int colIdx = 0; colIdx < colSize; colIdx++) {
                    sb.append(map[rowIdx][colIdx]);
                }
                sb.append("\n");
            }

            System.out.println(sb);
        }
    }
}
