package boj;

import java.util.*;
import java.io.*;

public class problem21736 {
    /*
     * 캠퍼스는 nxm
     * 
     * 이동 방법은 벽이 아닌 곳을 상하좌우로 이동할 수 있다.
     * - O: 빈공간
     * - X: 벽
     * - I: 도연이
     * - P: 사람
     * 
     * 캠퍼스에서 도연이가 만날 수 있는 사람의 수를 구하라
     * 
     * bfs
     * 
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

    static Location doyeon;
    static int rowSize, colSize;
    static char[][] map;
    static boolean[][] visited;
    static int answer;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};

    static final char EMPTY = 'O';
    static final char PERSON = 'P';
    static final char WALL = 'X';
    static final char DOYEON = 'I';

    static void inputData() throws IOException {
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                char val = row.charAt(colIdx);

                if (val == DOYEON) {
                    doyeon = new Location(rowIdx, colIdx);
                    visited[rowIdx][colIdx] = true;
                }

                map[rowIdx][colIdx] = val;
            }
        }
    }

    static boolean checkBoundary(int row, int col) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
            return false;
        }

        return true;
    }



    static void solution() {
        Deque<Location> q = new ArrayDeque<>();

        q.addLast(doyeon);

        while (!q.isEmpty()) {

            Location loc = q.pollFirst();

            int rowIdx = loc.rowIdx;
            int colIdx = loc.colIdx;

            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = rowIdx + dx[dir];
                int newCol = colIdx + dy[dir];

                // 범위 체크
                if (!checkBoundary(newRow, newCol)) {
                    continue;
                }

                // 벽체크
                if (map[newRow][newCol] == WALL) {
                    continue;
                }

                // 방문체크
                if (visited[newRow][newCol]) {
                    continue;
                }

                // 사람을 만나면 카운트
                if (map[newRow][newCol] == PERSON) {
                    answer++;
                }

                visited[newRow][newCol] = true;
                q.addLast(new Location(newRow, newCol));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer == 0 ? "TT" : answer);


    }
}
