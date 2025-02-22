package boj;

import java.io.*;
import java.util.*;

public class problem1261 {
    /*
     * NxM 크기 미로
     * - 미로는 빈 방 또는 벽으로 이루어져 있다.
     * - 벽은 부수지 않으면 이동할 수 없다.
     * 
     * 현재 (1,1)에 있을 때 (N, M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 구하라
     * 
     * bfs
     * - 벽을 부순 횟수를 기준으로 우선순위 큐를 생성
     */

    static class Location implements Comparable<Location> {
        int rowIdx;
        int colIdx;
        int count;

        public Location(int rowIdx, int colIdx, int count) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.count = count;
        }

        public int compareTo(Location o) {
            return this.count - o.count;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;

    static final int WALL = 1;
    static final int EMPTY = 0;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String value = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = value.charAt(colIdx) - '0';
            }
        }
    }

    static void solution() {

        boolean[][] visited = new boolean[rowSize][colSize];

        PriorityQueue<Location> pQ = new PriorityQueue<>();

        pQ.add(new Location(0, 0, 0));
        visited[0][0] = true;

        while (!pQ.isEmpty()) {
            Location loc = pQ.poll();

            if (loc.rowIdx == rowSize - 1 && loc.colIdx == colSize - 1) {
                answer = loc.count;
                break;
            }

            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = loc.rowIdx + dx[dir];
                int newCol = loc.colIdx + dy[dir];

                // 범위 체크
                if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                    continue;
                }

                // 방문체크
                if (visited[newRow][newCol]) {
                    continue;
                }

                if (map[newRow][newCol] == WALL) {
                    pQ.add(new Location(newRow, newCol, loc.count + 1));
                    visited[newRow][newCol] = true;
                } else {
                    pQ.add(new Location(newRow, newCol, loc.count));
                    visited[newRow][newCol] = true;
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
