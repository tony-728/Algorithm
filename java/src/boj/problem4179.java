package boj;

import java.io.*;
import java.util.*;

public class problem4179 {
    /*
     * 지훈이 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지 여부
     * 얼마나 빨리 탈출할 수 있는지를 결정해야 한다.
     * - 지훈이와 불은 매 분마다 한칸씩 상하좌우로 이동한다.
     * - 불은 각 지점에서 네 방향으로 확산된다.
     * - 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
     * 
     * - bfs
     * - 시뮬레이션
     * 
     * - 불과 지훈이의 사이클 구분을 신경써야 한다.
     * 
     */

    static class Location {
        int rowIdx;
        int colIdx;
        boolean cycle;

        public Location(int rowIdx, int colIdx, boolean cycle) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.cycle = cycle;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static char[][] map;
    static boolean[][] jihunVisited;
    static boolean[][] fireVisited;

    static Deque<Location> fireQ = new ArrayDeque<>();
    static Deque<Location> jihunQ = new ArrayDeque<>();

    static int answer;

    static final char WALL = '#';
    static final char EMPTY = '.';
    static final char FIRE = 'F';
    static final char JIHUN = 'J';

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];
        jihunVisited = new boolean[rowSize][colSize];
        fireVisited = new boolean[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                char value = row.charAt(colIdx);
                if (value == JIHUN) {
                    jihunQ.addLast(new Location(rowIdx, colIdx, true));
                    jihunVisited[rowIdx][colIdx] = true;
                    jihunQ.addLast(new Location(0, 0, false));
                }

                if (value == FIRE) {
                    fireQ.addLast(new Location(rowIdx, colIdx, true));
                    fireVisited[rowIdx][colIdx] = true;
                }

                map[rowIdx][colIdx] = value;
            }
        }

        fireQ.addLast(new Location(0, 0, false));
    }

    static boolean checkBoundary(int row, int col) {
        return 0 <= row && row < rowSize && 0 <= col && col < colSize;
    }

    static void solution() {

        int count = 0;

        out: while (!jihunQ.isEmpty()) {
            count++;

            // 불이 움직인다.
            while (!fireQ.isEmpty()) {

                Location fire = fireQ.pollFirst();

                // 불 사이클이 끝났으므로 다시 사이클 종료 추가 후 탈출
                if (!fire.cycle) {
                    fireQ.addLast(new Location(0, 0, false));
                    break;
                }

                for (int dir = 0; dir < dx.length; dir++) {
                    int newRow = fire.rowIdx + dx[dir];
                    int newCol = fire.colIdx + dy[dir];

                    // 범위 체크
                    if (!checkBoundary(newRow, newCol)) {
                        continue;
                    }

                    // 벽으로 갈 수 없다.
                    if (map[newRow][newCol] == WALL) {
                        continue;
                    }

                    // 방문체크
                    if (fireVisited[newRow][newCol]) {
                        continue;
                    }

                    fireQ.addLast(new Location(newRow, newCol, true));
                    fireVisited[newRow][newCol] = true;
                    map[newRow][newCol] = FIRE;
                }
            }

            // 지훈이가 움직인다.
            while (!jihunQ.isEmpty()) {

                Location jihun = jihunQ.pollFirst();

                // 지훈 사이클이 끝났으므로 다시 사이클 종료 추가 후 탈출
                if (!jihun.cycle) {
                    jihunQ.addLast(new Location(0, 0, false));
                    break;
                }

                for (int dir = 0; dir < dx.length; dir++) {
                    int newRow = jihun.rowIdx + dx[dir];
                    int newCol = jihun.colIdx + dy[dir];

                    // 범위 체크
                    // 범위로 나갈 수 있다면 지훈이는 탈출한 것이다.
                    if (!checkBoundary(newRow, newCol)) {
                        answer = count;
                        break out;
                    }

                    // 불로 갈 수 없다.
                    if (map[newRow][newCol] == FIRE) {
                        continue;
                    }

                    // 벽으로 갈 수 없다.
                    if (map[newRow][newCol] == WALL) {
                        continue;
                    }

                    // 방문체크
                    if (jihunVisited[newRow][newCol]) {
                        continue;
                    }

                    jihunQ.addLast(new Location(newRow, newCol, true));
                    jihunVisited[newRow][newCol] = true;
                }
            }

            // 지훈이가 하나 남았을 때 사이클 구분을 위한 값이라면 더이상의 진행이 불가능
            if (jihunQ.size() == 1 && !jihunQ.peek().cycle) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer > 0 ? answer : "IMPOSSIBLE");
    }
}
