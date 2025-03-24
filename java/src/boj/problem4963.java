package boj;

import java.io.*;
import java.util.*;

public class problem4963 {
    /*
     * 8방으로 이어진 섬은 갈 수 있다.
     * 
     * 섬의 갯수를 구하라
     */



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[] dx = {0, 1, 0, -1, 1, -1, 1, -1};
        int[] dy = {1, 0, -1, 0, -1, 1, 1, -1};

        int LAND = 1;
        int SEA = 0;

        while (true) {

            int count = 0;

            st = new StringTokenizer(br.readLine().trim());

            int colSize = Integer.parseInt(st.nextToken());
            int rowSize = Integer.parseInt(st.nextToken());

            // 종료 조건
            if (colSize == 0 && rowSize == 0) {
                break;
            }

            // 맵 입력
            int[][] map = new int[rowSize][colSize];
            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                st = new StringTokenizer(br.readLine());

                for (int colIdx = 0; colIdx < colSize; colIdx++) {
                    map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                }
            }

            // 섬연결 갯수 확인
            boolean[][] visited = new boolean[rowSize][colSize];

            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                for (int colIdx = 0; colIdx < colSize; colIdx++) {
                    if (map[rowIdx][colIdx] == LAND && !visited[rowIdx][colIdx]) {

                        Deque<int[]> q = new ArrayDeque<>();

                        visited[rowIdx][colIdx] = true;

                        q.addLast(new int[] {rowIdx, colIdx});
                        count++;

                        while (!q.isEmpty()) {

                            int[] land = q.pollFirst();

                            for (int dir = 0; dir < dx.length; dir++) {
                                int newRow = land[0] + dx[dir];
                                int newCol = land[1] + dy[dir];

                                if (!(0 <= newRow && newRow < rowSize && 0 <= newCol
                                        && newCol < colSize)) {
                                    continue;
                                }

                                if (visited[newRow][newCol]) {
                                    continue;
                                }

                                if (map[newRow][newCol] == SEA) {
                                    continue;
                                }

                                q.add(new int[] {newRow, newCol});
                                visited[newRow][newCol] = true;
                            }
                        }
                    }
                }
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
