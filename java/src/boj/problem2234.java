package boj;

import java.io.*;
import java.util.*;

public class problem2234 {
    /*
     * 굵은 선은 벽, 점선은 통로인 지도가 있다.
     * 
     * 서쪽에 벽이 있을 때는 1을, 
     * 북쪽에 벽이 있을 때는 2를,
     * 동쪽에 벽이 있을 때는 4를, 
     * 남쪽에 벽이 있을 때는 8을 더한 값이 주어진다. 
     * 
     * 계산할 것
     * 1. 이 성에 있는 방의 갯수
     * 2. 가장 넓은 방의 넓이
     * 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
     * 
     * bfs
     */

    static int rowSize, colSize;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    static final int WEST = 1;
    static final int NORTH = 1 << 1;
    static final int EAST = 1 << 2;
    static final int SOUTH = 1 << 3;

    static final int[] arrOfDirection = {WEST, NORTH, EAST, SOUTH};

    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solution() {

        boolean[][] visited = new boolean[rowSize][colSize];

        int[][] roomMap = new int[rowSize][colSize];
        int[] arrOfRoomSize = new int[50 * 50 + 1];

        int roomIdx = 1;

        int numOfRoom = 0;
        int oneOfMaxSize = 0;

        // 지도에서 각 방의 넓이를 구한다.
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (!visited[rowIdx][colIdx]) {

                    numOfRoom++;

                    Deque<int[]> q = new ArrayDeque<>();

                    q.addLast(new int[] {rowIdx, colIdx});
                    visited[rowIdx][colIdx] = true;
                    roomMap[rowIdx][colIdx] = roomIdx;

                    int size = 1;

                    while (!q.isEmpty()) {

                        int[] loc = q.pollFirst();

                        // 위치에 있는 값
                        int locVal = map[loc[0]][loc[1]];

                        for (int dir = 0; dir < arrOfDirection.length; dir++) {

                            int wall = arrOfDirection[dir];

                            // 벽이 있다.
                            // 
                            if ((locVal & wall) == wall) {
                                continue;
                            }

                            int newRow = loc[0] + dx[dir];
                            int newCol = loc[1] + dy[dir];

                            // 범위체크
                            if (0 > newRow || newRow >= rowSize || 0 > newCol
                                    || newCol >= colSize) {
                                continue;
                            }

                            // 방문체크
                            if (visited[newRow][newCol]) {
                                continue;
                            }

                            visited[newRow][newCol] = true;
                            roomMap[newRow][newCol] = roomIdx;
                            q.addLast(new int[] {newRow, newCol});
                            size++;

                        }
                    }

                    arrOfRoomSize[roomIdx] = size;
                    roomIdx++;
                    oneOfMaxSize = Math.max(oneOfMaxSize, size);
                }
            }
        }

        // 지도에서 벽을 하나 허물었을 때 얻을 수 있는 최대 방크기
        int maxSize = 0;
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {

                roomIdx = roomMap[rowIdx][colIdx];

                for (int dir = 0; dir < dx.length; dir++) {

                    int newRow = rowIdx + dx[dir];
                    int newCol = colIdx + dy[dir];

                    // 범위체크
                    if (0 > newRow || newRow >= rowSize || 0 > newCol || newCol >= colSize) {
                        continue;
                    }

                    // 같은 방 확인
                    if (roomMap[newRow][newCol] == roomIdx) {
                        continue;
                    }

                    maxSize = Math.max(maxSize,
                            arrOfRoomSize[roomIdx] + arrOfRoomSize[roomMap[newRow][newCol]]);
                }
            }
        }

        sb.append(numOfRoom).append("\n").append(oneOfMaxSize).append("\n").append(maxSize);

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
