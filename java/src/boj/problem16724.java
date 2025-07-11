package boj;

import java.io.*;
import java.util.*;

public class problem16724 {
    /*
     * 정해놓은 방향으로 이동한다.(4방향)
     * 이동하지 않는 safe zone을 만든다.
     * - 이 때 지도 어느 곳에 있더라도 safe zone으로 들어갈 수 있게 하는 영역의 최소 개수를 구하라
     * 
     * 순환이 발생하는 갯수를 구하기
     */

    static final char DOWN = 'D';
    static final char UP = 'U';
    static final char LEFT = 'L';
    static final char RIGHT = 'R';
    static final char SAFE = 'S';

    // r, d, l, u
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int rowSize, colSize;
    static char[][] map;

    static boolean[][] visited;
    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

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

    static void solution() {
        visited = new boolean[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (map[rowIdx][colIdx] != SAFE && !visited[rowIdx][colIdx]) {
                    // printMap();

                    Deque<int[]> q = new ArrayDeque<>();
                    Deque<int[]> safeQ = new ArrayDeque<>();

                    visited[rowIdx][colIdx] = true;

                    q.offer(new int[] {rowIdx, colIdx});
                    safeQ.offer(new int[] {rowIdx, colIdx});
                    while (!q.isEmpty()) {
                        int[] loc = q.poll();

                        int newRow = 0;
                        int newCol = 0;

                        switch (map[loc[0]][loc[1]]) {
                            case DOWN:
                                newRow = loc[0] + dx[1];
                                newCol = loc[1] + dy[1];
                                break;

                            case UP:
                                newRow = loc[0] + dx[3];
                                newCol = loc[1] + dy[3];
                                break;

                            case LEFT:
                                newRow = loc[0] + dx[2];
                                newCol = loc[1] + dy[2];
                                break;

                            case RIGHT:
                                newRow = loc[0] + dx[0];
                                newCol = loc[1] + dy[0];
                                break;
                        }

                        // 범위 체크
                        if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                            continue;
                        }

                        // safe 사이클인 곳을 만나면 이전 경로를 safe로 변경
                        if (map[newRow][newCol] == SAFE) {
                            map[loc[0]][loc[1]] = SAFE;
                            while (!safeQ.isEmpty()) {
                                int[] safeLoc = safeQ.poll();
                                map[safeLoc[0]][safeLoc[1]] = SAFE;
                            }
                            break;
                        }

                        // 방문체크
                        // 방문한 곳을 방문하면 사이클 발생이므로 이전 경로를 safe로 변경
                        if (visited[newRow][newCol]) {
                            answer++;
                            map[loc[0]][loc[1]] = SAFE;

                            while (!safeQ.isEmpty()) {
                                int[] safeLoc = safeQ.poll();
                                map[safeLoc[0]][safeLoc[1]] = SAFE;
                            }
                            break;
                        }

                        visited[newRow][newCol] = true;
                        q.offer(new int[] {newRow, newCol});
                        safeQ.offer(new int[] {newRow, newCol});

                    }
                }
            }
        }

    }

    static void printMap() {
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            // System.out.println(Arrays.toString(visited[rowIdx]));
            System.out.println(Arrays.toString(map[rowIdx]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
