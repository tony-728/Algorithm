package boj;

import java.io.*;
import java.util.*;

public class problem2573 {
    /*
     * 빙산의 각 부분별 높이 정보는 양의 정수로 채워져 있다.
     * - 빙신 이외에 바다는 0으로 해당됨
     * 
     * 바딧물이 많이 접해있는 부분에서 더 빨리 줄어든다.
     * - 빙산 칸에 인접해 있는 방향에 0 칸의 갯수만큼 높이가 줄어든다.
     * - 높이는 0보다 더 줄어들지 않는다.
     * 
     * 빙산이 두 덩어리 이상으로 분리되는 최초의 시간을 구하라
     * - 전부다 녹을 때까지 두 덩어리 이상으로 분리되지 않는다면 0을 출력하라
     * 
     * bfs
     * 
     */

    static class Location {
        int rowIdx;
        int colIdx;
        int ice;

        public Location(int rowIdx, int colIdx, int ice) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.ice = ice;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;

    static int answer;

    static final int EMPTY = 0;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean checkBoundary(int row, int col) {
        return 0 <= row && row < rowSize && 0 <= col && col < colSize;
    }

    static boolean melt() {

        // 전부 바닷물인지 확인
        boolean flag = true;

        ArrayList<Location> iceList = new ArrayList<>();

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (map[rowIdx][colIdx] != EMPTY) {
                    flag = false;

                    int count = 0;
                    for (int dir = 0; dir < dx.length; dir++) {
                        int newRow = rowIdx + dx[dir];
                        int newCol = colIdx + dy[dir];

                        // 범위 체크
                        if (!checkBoundary(newRow, newCol)) {
                            continue;
                        }

                        // 인접한 바다 개수 체크
                        if (map[newRow][newCol] == EMPTY) {
                            count++;
                        }
                    }

                    // 변하는 빙산을 리스트에 저장
                    // 바로 2차원 배열에 적용하면 안됨
                    iceList.add(new Location(rowIdx, colIdx,
                            (map[rowIdx][colIdx] - count) < 0 ? 0 : map[rowIdx][colIdx] - count));
                }
            }
        }
        
        // 빙산 갱신
        for (Location loc : iceList) {
            map[loc.rowIdx][loc.colIdx] = loc.ice;
        }

        return flag;
    }

    static int countIce() {

        boolean[][] visited = new boolean[rowSize][colSize];

        int count = 0;

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (map[rowIdx][colIdx] != EMPTY && !visited[rowIdx][colIdx]) {
                    count++;

                    Deque<Location> q = new ArrayDeque<>();

                    q.addLast(new Location(rowIdx, colIdx, map[rowIdx][colIdx]));
                    visited[rowIdx][colIdx] = true;

                    while (!q.isEmpty()) {

                        Location loc = q.pollFirst();

                        for (int dir = 0; dir < dx.length; dir++) {
                            int newRow = loc.rowIdx + dx[dir];
                            int newCol = loc.colIdx + dy[dir];

                            // 범위 체크
                            if (!checkBoundary(newRow, newCol)) {
                                continue;
                            }

                            // 인접한 바다인 경우 통과
                            if (map[newRow][newCol] == EMPTY) {
                                continue;
                            }

                            // 방문처리
                            if (visited[newRow][newCol]) {
                                continue;
                            }

                            q.addLast(new Location(newRow, newCol, map[newRow][newCol]));
                            visited[newRow][newCol] = true;

                        }
                    }
                }
            }
        }

        return count;

    }

    static void solution() {

        while (true) {

            // 빙산 녹이기
            if (melt()) {
                // 전부 바닷물이 되면 0을 출력
                answer = 0;
                break;
            }

            answer++;

            // 빙산의 덩어리 개수 구하기
            if (countIce() > 1) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
