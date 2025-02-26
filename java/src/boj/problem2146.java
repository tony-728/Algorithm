package boj;

import java.io.*;
import java.util.*;

public class problem2146 {
    /*
     * NxN 2차원 배열에 섬이 있다.
     * - 상하좌우로 연결되어 있는 육지 덩어리
     * 
     * 육지에서 가장 짧은 다리를 놓아 두 대륙을 연결하고자 한다.
     * - 다리가 격자에서 차지하는 칸의 수가 가장 작은 다리가 가장 짧은 다리이다.
     * 
     * 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 구하라
     * 
     * bfs
     * 
     * 1. 연결된 하나의 대륙을 찾는다. bfs
     * 1-1. 대륙의 가장자리 육지만 따로 큐에 저장함
     * 2. 대륙의 가장자리에서 또 다른 대륙을 만날 때까지 bfs
     * 2-1. 이 때 구한 거리가 가장 짧은 것이 답이다.
     */

    static class Location {
        int rowIdx;
        int colIdx;
        int distance;

        public Location(int rowIdx, int colIdx, int distance) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.distance = distance;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int mapSize;
    static int[][] map;
    static boolean[][] visited;
    static int answer;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static final int SEA = 0;
    static final int LAND = 1;

    static void inputData() throws IOException {
        answer = 987654321;
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];
        visited = new boolean[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean checkBoundary(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    static void makeBridge(Deque<Location> edgeQ, boolean[][] landVisited) {

        for (Location land : edgeQ) {
            Deque<Location> q = new ArrayDeque<>();
            boolean[][] seaVisited = new boolean[mapSize][mapSize];
            q.addLast(land);

            out: while (!q.isEmpty()) {

                Location loc = q.pollFirst();

                for (int dir = 0; dir < dx.length; dir++) {
                    int newRow = loc.rowIdx + dx[dir];
                    int newCol = loc.colIdx + dy[dir];

                    // 범위 체크
                    if (!checkBoundary(newRow, newCol)) {
                        continue;
                    }

                    // 내가 속한 대륙으로 간 것임
                    if (landVisited[newRow][newCol]) {
                        continue;
                    }

                    // 바다에 대한 방문처리
                    if (seaVisited[newRow][newCol]) {
                        continue;
                    }

                    // 처음 방문한 육지에 도착했을 때 종료한다.
                    if (!landVisited[newRow][newCol] && map[newRow][newCol] == LAND) {
                        answer = Math.min(answer, loc.distance);
                        break out;
                    }

                    q.addLast(new Location(newRow, newCol, loc.distance + 1));
                    seaVisited[newRow][newCol] = true;
                }
            }
        }
    }

    static void solution() {

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                // 육지를 만났다면 연결된 육지를 찾고 가장 짧은 다리를 구한다.
                if (map[rowIdx][colIdx] == LAND && !visited[rowIdx][colIdx]) {
                    Deque<Location> q = new ArrayDeque<>(); // 대륙을 찾기 위한 큐
                    Deque<Location> edgeQ = new ArrayDeque<>(); // 대륙의 가장자리에 위치한 육지를 찾기 위한 큐
                    boolean[][] landVisited = new boolean[mapSize][mapSize]; // 본인이 속한 대륙으로 다리를 연결하지 않도록 방문처리

                    q.addLast(new Location(rowIdx, colIdx, 0));
                    visited[rowIdx][colIdx] = true;
                    landVisited[rowIdx][colIdx] = true;

                    while (!q.isEmpty()) {

                        Location loc = q.pollFirst();
                        boolean check = true;

                        for (int dir = 0; dir < dx.length; dir++) {
                            int newRow = loc.rowIdx + dx[dir];
                            int newCol = loc.colIdx + dy[dir];

                            // 범위 체크
                            if (!checkBoundary(newRow, newCol)) {
                                continue;
                            }

                            // 대륙 방문처리
                            if (landVisited[newRow][newCol]) {
                                continue;
                            }

                            // 바다인지 확인
                            // 인접한 위치에 바다가 있다면 가장자리인 육지이다.
                            if (map[newRow][newCol] == SEA) {
                                // 가장 자리가 중복되어 들어가지 않도록 처리
                                if (check) {
                                    edgeQ.addLast(new Location(loc.rowIdx, loc.colIdx, 0));
                                    check = false;
                                }

                                continue;
                            }

                            q.addLast(new Location(newRow, newCol, 0));
                            visited[newRow][newCol] = true;
                            landVisited[newRow][newCol] = true;
                        }
                    }

                    // 가장자리에 위치한 대륙으로 다리를 연결하러 감
                    makeBridge(edgeQ, landVisited);
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
