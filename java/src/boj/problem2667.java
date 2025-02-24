package boj;

import java.util.*;
import java.io.*;

public class problem2667 {
    /*
     * 정사각형 2차원 배열
     * - 1은 집이 있는 곳
     * - 0은 집이 없는 곳
     * 
     * 연결된 집의 모임인 단지를 정의하고 단지에 번호를 붙이려고 한다.
     * - 연결은 인접한 위치(상하좌우)
     * 
     * 단지수를 출력하고 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하라
     * 
     * bfs
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int mapSize;
    static int[][] map;

    static final int EMPTY = 0;
    static final int HOME = 1;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int home;
    static ArrayList<Integer> homeCountList = new ArrayList<>();

    static void inputData() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                map[rowIdx][colIdx] = row.charAt(colIdx) - '0';
            }
        }
    }

    static void solution() {

        boolean[][] visited = new boolean[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                if (map[rowIdx][colIdx] == HOME && !visited[rowIdx][colIdx]) {

                    // 단지 개수 카운트
                    home++;

                    // 단지에 집 갯수 확인
                    int homeCount = 0;
                    Deque<int[]> q = new ArrayDeque<>();

                    q.addLast(new int[] {rowIdx, colIdx});
                    visited[rowIdx][colIdx] = true;
                    homeCount++;

                    while (!q.isEmpty()) {
                        int[] loc = q.pollFirst();


                        for (int dir = 0; dir < dx.length; dir++) {
                            int newRow = loc[0] + dx[dir];
                            int newCol = loc[1] + dy[dir];

                            // 범위 체크
                            if (0 > newRow || newRow >= mapSize || 0 > newCol
                                    || newCol >= mapSize) {
                                continue;
                            }

                            // 방문확인
                            if (visited[newRow][newCol]) {
                                continue;
                            }

                            // 빈칸 확인
                            if (map[newRow][newCol] == EMPTY) {
                                continue;
                            }

                            q.addLast(new int[] {newRow, newCol});
                            visited[newRow][newCol] = true;
                            homeCount++;
                        }
                    }

                    homeCountList.add(homeCount);
                }
            }
        }


        Collections.sort(homeCountList);

    }

    public static void main(String[] main) throws IOException {
        inputData();

        solution();

        System.out.println(home);
        for (Integer homeCount : homeCountList) {
            System.out.println(homeCount);
        }
    }
}
