package boj;

import java.io.*;
import java.util.*;

public class problem2665 {

    /*
     * nxn 바둑판이 있다.
     * 
     * 일부분은 검은 방이고 나머지는 흰방이다. 
     * - 검은방은 들어갈 수 없다.
     * - 서로 붙어 있는 두 개의 흰 방은 지나갈 수 있다.
     * 
     * 시작방에서 끝방으로 가는 것이 목적이다. 
     * - 바꿔야하는 검은 방의 최소 개수를 구하라
     * 
     * bfs
     * - 검은색 방을 가장 적게 지나간 것을 확인하기 위해 pQ를 사용
     */

    static int mapSize;
    static int[][] map;
    static int answer;

    static final int BLACK = 0;
    static final int WHITE = 1;

    static void inputData() throws IOException {

        answer = 987654321;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        boolean[][] visited = new boolean[mapSize][mapSize];

        q.add(new int[] {0, 0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] loc = q.poll();

            if (loc[0] == mapSize - 1 && loc[1] == mapSize - 1) {
                answer = loc[2];
                break;
            }

            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = loc[0] + dx[dir];
                int newCol = loc[1] + dy[dir];

                // 범위 체크
                if (0 > newRow || newRow >= mapSize || 0 > newCol || newCol >= mapSize) {
                    continue;
                }

                // 방문체크
                if (visited[newRow][newCol]) {
                    continue;
                }

                if (map[newRow][newCol] == BLACK) {
                    q.add(new int[] {newRow, newCol, loc[2] + 1});
                } else {
                    q.add(new int[] {newRow, newCol, loc[2]});
                }
                visited[newRow][newCol] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
