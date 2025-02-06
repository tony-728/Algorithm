package boj;

import java.io.*;
import java.util.*;

public class problem3254 {
    /*
     * 가로 6줄, 세로 7줄 모양의 게임판(6x7)
     * 
     * 두사람이 번갈아가면서 김밥을 게임판에 던진다.
     * 김밥이 있는 칸에서 아래로 비어있는 칸이 없을 때까지 떨어진다.
     * 
     * 김밥을 두 칸 또는 그 이상 칸에 걸치게 던지는 경우는 없다.
     * 항상 비어있는 칸에 김밥을 던진다.
     * - 김밥은 1칸을 차지한다.
     * 
     * 두 사람은 김밥 21개를 가지고 게임을 시작한다.
     * - 항상 상근이가 먼저 시작한다.
     * - 상근: 참치, 정인: 김치
     * 
     * 김밥 4개를 가로, 세로, 대각선으로 연속하게 만드는 것
     * 
     * 상근과 정인이 맞춘 칸(열)을 차례대로 기록했다
     * - 게임의 승자가 누구인지
     * - 몇 번째 김밥에서 게임의 승자가 정해졌는지(처음으로 등장하는 김밥 4개의 연속)를 구하라
     * 
     * 게임의 승자가 없을 수도 있다.
     * 
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int ROW_SIZE = 7;
    static final int COL_SIZE = 8;
    static final int INPUT_CASE = 21;
    static final int S = 1;
    static final int J = 2;
    static final int EMPTY = 0;

    static int[][] map = new int[ROW_SIZE][COL_SIZE];

    static int sIndex, jIndex;

    static boolean checkBoundary(int row, int col) {

        if (0 < row && row < ROW_SIZE && 0 < col && col < COL_SIZE) {
            return true;
        } else {
            return false;
        }
    }

    static boolean checkColumn(int row, int col, int kimbab) {
        // 가로 확인
        int count = 0;
        for (int idx = 0; idx < 4; idx++) {

            if (!checkBoundary(row, col + idx)) {
                break;
            }

            if (map[row][col + idx] != kimbab) {
                break;
            }

            count++;

        }

        if (count == 4) {
            return true;
        }


        count = 0;
        for (int idx = 0; idx < 4; idx++) {
            if (!checkBoundary(row, col - idx)) {
                break;
            }

            if (map[row][col - idx] != kimbab) {
                break;
            }

            count++;
        }

        if (count == 4) {
            return true;
        }

        if (col - 1 > 0 && map[row][col - 1] == kimbab && col + 1 < COL_SIZE
                && map[row][col + 1] == kimbab) {

            if (col - 2 > 0 && map[row][col - 2] == kimbab) {
                return true;
            }

            if (col + 2 < COL_SIZE && map[row][col + 2] == kimbab) {
                return true;
            }
        }

        return false;

    }

    static boolean checkRow(int row, int col, int kimbab) {
        // 세로 확인
        int count = 0;
        for (int idx = 0; idx < 4; idx++) {

            if (!checkBoundary(row + idx, col)) {
                break;
            }

            if (map[row + idx][col] != kimbab) {
                break;
            }

            count++;

        }

        if (count == 4) {
            return true;
        }

        count = 0;
        for (int idx = 0; idx < 4; idx++) {
            if (!checkBoundary(row - idx, col)) {
                break;
            }

            if (map[row - idx][col] != kimbab) {
                break;
            }

            count++;
        }

        if (count == 4) {
            return true;
        }

        if (row - 1 > 0 && map[row - 1][col] == kimbab && row + 1 < ROW_SIZE
                && map[row + 1][col] == kimbab) {

            if (row - 2 > 0 && map[row - 2][col] == kimbab) {
                return true;
            }

            if (row + 2 < ROW_SIZE && map[row + 2][col] == kimbab) {
                return true;
            }
        }

        return false;

    }

    static boolean checkDiagonal(int row, int col, int kimbab) {

        // 좌상
        int count = 0;
        for (int idx = 0; idx < 4; idx++) {
            if (!checkBoundary(row - idx, col - idx)) {
                break;
            }

            if (map[row - idx][col - idx] != kimbab) {
                break;
            }

            count++;
        }

        if (count == 4) {
            return true;
        }

        // 좌하
        count = 0;
        for (int idx = 0; idx < 4; idx++) {
            if (!checkBoundary(row + idx, col - idx)) {
                break;
            }

            if (map[row + idx][col - idx] != kimbab) {
                break;
            }

            count++;
        }


        if (count == 4) {
            return true;
        }

        // 우상
        count = 0;
        for (int idx = 0; idx < 4; idx++) {
            if (!checkBoundary(row - idx, col + idx)) {
                break;
            }

            if (map[row - idx][col + idx] != kimbab) {
                break;
            }

            count++;
        }

        if (count == 4) {
            return true;
        }

        // 우하
        count = 0;
        for (int idx = 0; idx < 4; idx++) {
            if (!checkBoundary(row + idx, col + idx)) {
                break;
            }

            if (map[row + idx][col + idx] != kimbab) {
                break;
            }

            count++;
        }

        if (count == 4) {
            return true;
        }

        // 우하향
        if (row - 1 > 0 && col - 1 > 0 && map[row - 1][col - 1] == kimbab && row + 1 < ROW_SIZE
                && col + 1 < COL_SIZE && map[row + 1][col + 1] == kimbab) {

            if (row - 2 > 0 && col - 2 > 0 && map[row - 2][col - 2] == kimbab) {
                return true;
            }

            if (row + 2 < ROW_SIZE && col + 2 < COL_SIZE && map[row + 2][col + 2] == kimbab) {
                return true;
            }
        }

        // 우상향
        if (row + 1 < ROW_SIZE && col - 1 > 0 && map[row + 1][col - 1] == kimbab && row - 1 > 0
                && col + 1 < COL_SIZE && map[row - 1][col + 1] == kimbab) {

            if (row + 2 < ROW_SIZE && col - 2 > 0 && map[row + 2][col - 2] == kimbab) {
                return true;
            }

            if (row - 2 > 0 && col + 2 < COL_SIZE && map[row - 2][col + 2] == kimbab) {
                return true;
            }
        }



        return false;
    }

    static boolean checkMap(int row, int col, int kimbab) {
        // 가로, 세로, 대각선에 연속된 4개의 김밥이 있는지 확인

        // 가로 확인
        if (checkColumn(row, col, kimbab)) {
            return true;
        }

        // 세로 확인
        if (checkRow(row, col, kimbab)) {
            return true;
        }


        // 대각선 확인
        if (checkDiagonal(row, col, kimbab)) {
            return true;
        }

        return false;
    }

    static boolean checkMapYC(int row, int col, int kimbab) {

        // 우, 우하, 하, 좌하, 좌, 좌상, 상, 우상
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

        int count;
        int newRow, newCol;

        for (int dir = 0; dir < 4; dir++) {
            count = 1;

            for (int val = 1; val < 4; val++) {
                newRow = row + dx[dir] * val;
                newCol = col + dy[dir] * val;

                if (!checkBoundary(newRow, newCol)) {
                    break;
                }

                if (map[newRow][newCol] != kimbab) {
                    break;
                }

                count++;
            }


            for (int val = 1; val < 4; val++) {
                newRow = row + dx[dir + 4] * val;
                newCol = col + dy[dir + 4] * val;

                if (!checkBoundary(newRow, newCol)) {
                    break;
                }

                if (map[newRow][newCol] != kimbab) {
                    break;
                }

                count++;
            }

            // 정확히 연속된 4개가 아니기 때문에 가능
            if (count >= 4) {
                return true;
            }
        }

        return false;
    }

    static boolean setMap(int colIdx, int kimbab) {

        int rowIdx = 0;

        for (rowIdx = 1; rowIdx < ROW_SIZE; rowIdx++) {

            if (map[rowIdx][colIdx] == EMPTY) {

                if (rowIdx == ROW_SIZE - 1) {
                    map[rowIdx][colIdx] = kimbab;
                }
                continue;
            }

            map[rowIdx - 1][colIdx] = kimbab;
            break;
        }

        // return checkMap(rowIdx - 1, colIdx, kimbab);
        return checkMapYC(rowIdx - 1, colIdx, kimbab);

    }

    static void solution() throws IOException {
        boolean flag = false;

        for (int idx = 1; idx <= INPUT_CASE; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            sIndex = Integer.parseInt(st.nextToken());

            if (!flag && setMap(sIndex, S)) {
                sb.append("sk ").append(idx);
                flag = true;
            }

            jIndex = Integer.parseInt(st.nextToken());

            if (!flag && setMap(jIndex, J)) {
                sb.append("ji ").append(idx);
                flag = true;
            }
        }

        if (!flag) {
            sb.append("ss");
        }
    }

    public static void main(String[] args) throws IOException {
        solution();

        System.out.println(sb);
    }
}
