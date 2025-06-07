package boj;

import java.io.*;
import java.util.*;

public class problem17836 {
    /*
     * N, M 크기의 성이 있다. 
     * 입구는 (1,1), 공주님은 (N, M)에 위치해있다.
     * 
     * 벽으로는 갈 수 없다.
     * 중간에 검을 찾으면 무제한으로 벽을 부실 수 있다.
     * 
     * 공주를 구하기 위해서는 T시간 안에 구출해야한다.
     * 
     * 얼마나 빨리 공주님을 구출할 수 있는지 구하라
     * - 구할 수 없다면 Fail
     * 
     * bfs
     * - 방문배열을 3으로 한 이유는 그람 2인 값을 그대로 사용하기 위함
     */

    static final int WALL = 1;
    static final int EMPTY = 0;
    static final int GRAM = 2;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int rowSize, colSize;
    static int timer;

    static int[][] map;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        timer = Integer.parseInt(st.nextToken());

        map = new int[rowSize + 1][colSize + 1];

        for (int rowIdx = 1; rowIdx < rowSize + 1; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 1; colIdx < colSize + 1; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solution() {
        boolean[][][] visited = new boolean[rowSize + 1][colSize + 1][3];

        Deque<int[]> q = new ArrayDeque<>();
        boolean flag = false;

        StringBuilder sb = new StringBuilder();

        q.addLast(new int[] {1, 1, 0, 0});
        visited[1][1][0] = true;

        while (!q.isEmpty()) {

            int[] loc = q.pollFirst();

            if (loc[0] == rowSize && loc[1] == colSize) {
                if (loc[3] <= timer) {
                    sb.append(loc[3]);
                    flag = true;
                }
                break;
            }

            for (int dir = 0; dir < dx.length; dir++) {
                int newRow = loc[0] + dx[dir];
                int newCol = loc[1] + dy[dir];
                int newTimer = loc[3] + 1;

                // 범위 체크
                if (0 >= newRow || newRow > rowSize || 0 >= newCol || newCol > colSize) {
                    continue;
                }

                // 방문체크
                if (visited[newRow][newCol][loc[2]]) {
                    continue;
                }

                // 벽체크
                if (loc[2] != GRAM && map[newRow][newCol] == WALL) {
                    continue;
                }

                if (map[newRow][newCol] == GRAM) {
                    visited[newRow][newCol][GRAM] = true;
                    q.addLast(new int[] {newRow, newCol, GRAM, newTimer});
                } else {
                    visited[newRow][newCol][loc[2]] = true;
                    q.addLast(new int[] {newRow, newCol, loc[2], newTimer});
                }
            }
        }

        if (!flag) {
            sb.append("Fail");
        }

        System.out.println(sb);

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();
    }
}
