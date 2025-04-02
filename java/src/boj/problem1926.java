package boj;

import java.io.*;
import java.util.*;

public class problem1926 {
    /*
     * 그림의 갯수, 그림 중 넓이가 가장 넓은 것의 넓이
     * 
     * 1로 연결된 것을 그림
     * - 4방
     * 
     * 그림의 넓이 그림의 포함된 1의 갯수
     * 
     * bfs
     */
    static int rowSize, colSize;
    static int[][] map;
    static boolean[][] visited;

    static int numOfPainting;
    static int sizeOfPainting;

    static final int PAINTING = 1;
    static final int EMPTY = 0;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solution() {
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (map[rowIdx][colIdx] == PAINTING && !visited[rowIdx][colIdx]) {
                    numOfPainting++;

                    Deque<int[]> q = new ArrayDeque<>();

                    q.addLast(new int[] {rowIdx, colIdx});
                    visited[rowIdx][colIdx] = true;
                    int size = 1;

                    while (!q.isEmpty()) {
                        int[] loc = q.pollFirst();

                        for (int dir = 0; dir < dx.length; dir++) {
                            int newRow = loc[0] + dx[dir];
                            int newCol = loc[1] + dy[dir];

                            if (0 > newRow || newRow >= rowSize || 0 > newCol || newCol >= colSize
                                    || visited[newRow][newCol]) {
                                continue;
                            }

                            if (map[newRow][newCol] == EMPTY) {
                                continue;
                            }

                            q.addLast(new int[] {newRow, newCol});
                            visited[newRow][newCol] = true;
                            size++;
                        }
                    }

                    sizeOfPainting = Math.max(sizeOfPainting, size);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(numOfPainting);
        System.out.println(sizeOfPainting);
    }
}
