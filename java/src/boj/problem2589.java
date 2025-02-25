package boj;

import java.io.*;
import java.util.*;

public class problem2589 {
    /*
     * 2차원 배열에 육지(L), 바다(W)가 표시되어 있다.
     * - 이동은 상하좌우로 이웃한 육지로만 가능하다
     * - 한 칸을 이동하는데 한 시간이 걸린다.
     * - 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다.
     * - 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나 멀리 돌아가서는 안된다.
     * 
     * 보물이 묻혀 있는 두 곳의 최단 거리로 이동하는 시간을 구하라
     * 
     * bfs
     * 
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
    static char[][] map;

    static final char LAND = 'L';
    static final char SEA = 'W';
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int rowSize, colSize;

    static int answer;

    static void inputData() throws IOException {

        answer = 0;
        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = row.charAt(colIdx);
            }
        }
    }

    static boolean checkBoundary(int row, int col) {
        return 0 <= row && row < rowSize && 0 <= col && col < colSize;
    }

    static void solution() {

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {

                // 육지면 보물 탐색
                // 보물마다 가장 긴 거리를 모두 확인해야 한다.
                if (map[rowIdx][colIdx] == LAND) {
                    boolean[][] visited = new boolean[rowSize][colSize];

                    // 현재 위치에서 가장 먼 육지를 찾는다.
                    Deque<Location> q = new ArrayDeque<>();

                    visited[rowIdx][colIdx] = true;
                    q.addLast(new Location(rowIdx, colIdx, 0));

                    while (!q.isEmpty()) {
                        Location loc = q.pollFirst();

                        // 가장 긴 거리를 저장
                        answer = Math.max(answer, loc.distance);

                        for (int dir = 0; dir < dx.length; dir++) {
                            int newRow = loc.rowIdx + dx[dir];
                            int newCol = loc.colIdx + dy[dir];

                            // 범위 체크
                            if (!checkBoundary(newRow, newCol)) {
                                continue;
                            }

                            // 방문 체크
                            if (visited[newRow][newCol]) {
                                continue;
                            }

                            // 바다면 갈 수 없다.
                            if(map[newRow][newCol] == SEA){
                                continue;
                            }

                            visited[newRow][newCol] = true;
                            q.addLast(new Location(newRow, newCol, loc.distance + 1));
                        }
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
