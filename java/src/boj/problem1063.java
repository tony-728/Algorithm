package boj;

import java.io.*;
import java.util.*;

public class problem1063 {

    /*
     * 
     * 8*8 체스판이 있다.
     * 킹의 위치가 주어진다.
     * 8방으로 말이 움직일 수 있다.
     * 알파벳은 열, 숫자는 행을 의미한다.
     * - 열의 가장 왼쪽은 A, 오른쪽은 H
     * - 행의 가장 아래가 1, 위가 8
     * - 왼쪽 아래 코너는 A1, 오른쪽칸은 B1
     * 
     * 
     * 돌이 하나 있는데 돌과 같은 곳으로 이동할 때에는 
     * 돌을 킹이 움직인 방향과 같은 방향으로 한 칸 이동시킨다.
     * 
     * 입력으로 킹이 어떻게 움직여야 하는지 주어진다.
     * 입력으로 주어진 대로 움직여서 킹이나 돌이 체스판 밖으로 나갈 경우에는
     * 그 이동은 건너 뛰고 다음 이동을 한다.
     * 
     * 킹과 돌의 마지막 위치를 구하라
     * 
     */

    static class Location {
        int rowIdx;
        int colIdx;

        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }

        public void update(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }

        public boolean checkLoc(int rowIdx, int colIdx) {
            if (this.rowIdx == rowIdx && this.colIdx == colIdx) {
                return true;
            }
            return false;
        }
    }


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[][] map = new int[8][8];

    static int moveCount;
    static Location kingLoc;
    static Location stoneLoc;

    static String[] moveList;

    static final int MAP_SIZE = 8;

    // 우, 좌, 하, 상, 우상, 좌상, 우하, 좌하
    static final int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    static final int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    static final String[] mList = {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};
    static final int[] INDEX = {7, 6, 5, 4, 3, 2, 1, 0};

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        String kLoc = st.nextToken();
        String sLoc = st.nextToken();

        // 위치 설정
        // 원래 인덱스로
        kingLoc = new Location(INDEX[kLoc.charAt(1) - '1'], kLoc.charAt(0) - 'A');
        stoneLoc = new Location(INDEX[sLoc.charAt(1) - '1'], sLoc.charAt(0) - 'A');

        moveCount = Integer.parseInt(st.nextToken());

        moveList = new String[moveCount];

        for (int idx = 0; idx < moveCount; idx++) {
            String move = br.readLine().trim();

            moveList[idx] = move;
        }
    }

    static boolean checkBoundary(int row, int col) {
        if (row < 0 || row >= MAP_SIZE || col < 0 || col >= MAP_SIZE) {
            return false;
        }

        return true;
    }

    static void solution() {

        for (int idx = 0; idx < moveCount; idx++) {
            String move = moveList[idx];

            for (int dir = 0; dir < MAP_SIZE; dir++) {
                if (move.equals(mList[dir])) {

                    int newRow = kingLoc.rowIdx + dx[dir];
                    int newCol = kingLoc.colIdx + dy[dir];

                    // 범위를 벗어나기 때문에 현재 이동은 건너 뛴다.
                    if (!checkBoundary(newRow, newCol)) {
                        continue;
                    }

                    // 왕이 이동하는 위치에 돌이 있다.
                    if (stoneLoc.checkLoc(newRow, newCol)) {

                        int sNewRow = stoneLoc.rowIdx + dx[dir];
                        int sNewCol = stoneLoc.colIdx + dy[dir];


                        // 범위를 벗어나기 때문에 현재 이동은 건너 뛴다.
                        if (!checkBoundary(sNewRow, sNewCol)) {
                            continue;
                        }

                        stoneLoc.update(sNewRow, sNewCol);
                    }

                    kingLoc.update(newRow, newCol);
                }
            }
        }

        int row = kingLoc.rowIdx;
        int col = kingLoc.colIdx;

        sb.append((char) (col + 'A')).append(INDEX[row] + 1).append("\n");

        row = stoneLoc.rowIdx;
        col = stoneLoc.colIdx;

        sb.append((char) (col + 'A')).append(INDEX[row] + 1);

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
