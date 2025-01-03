package boj;

import java.io.*;
import java.util.*;

public class problem14500 {
    /*
     * 정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 한다.
     * - 5가지 종류가 있다.
     * 
     * n x m 종이 위에 테트로미노 하나를 놓으려고 한다.
     * 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합 최대를 구하라
     * 
     * 테트로미노는 회전이나 대칭을 해도 된다.
     * 
     * 모든 경우를 확인
     * 
     * 1. 직선(가로, 세로)
     * 2. 3칸(가로, 세로)
     * - 3칸을 기준으로 첫번째, 두번째, 세번째 칸 위아래로 하나씩 
     * 3. 2칸(가로, 세로)
     * - 2칸을 기준으로 위, 아래
     * 4. 네모
     * 
     * dfs를 사용하면 코드가 간결해진다.
     * 
     */

    static class Location {
        int rowIdx;
        int colIdx;

        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }

        public String toString() {
            return "rowIdx: " + rowIdx + " colIdx: " + colIdx;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;
    static int answer;

    static final int[] dThree = {1, -1};
    static Deque<Location> q = new ArrayDeque<>();

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static boolean[][] visited;


    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        for (int idx = 0; idx < rowSize; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[idx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean checkBoundary(int r, int c) {
        if (r < 0 || r >= rowSize || c < 0 || c >= colSize) {
            return false;
        }

        return true;
    }

    static void checkStraight() {


        /*
        * 직선(4칸), 가로, 세로 확인
        */
        // 가로 4칸
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            q = new ArrayDeque<>();
            int count = 0;
            int sum = 0;

            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (count < 3) {
                    count++;
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));

                } else {
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));

                    if (answer < sum) {
                        answer = sum;
                    }

                    // 첫번째 인덱스 제거
                    Location loc = q.pollFirst();
                    sum -= map[loc.rowIdx][loc.colIdx];
                }
            }
        }


        // 세로 4칸
        for (int colIdx = 0; colIdx < colSize; colIdx++) {
            q = new ArrayDeque<>();
            int count = 0;
            int sum = 0;

            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                if (count < 3) {
                    count++;
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));

                } else {
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));

                    if (answer < sum) {
                        answer = sum;
                    }

                    // 첫번째 인덱스 제거
                    Location loc = q.pollFirst();
                    sum -= map[loc.rowIdx][loc.colIdx];

                }
            }
        }
    }

    static void checkThree() {
        // 3칸을 만들고
        // 가로기준 위,아래에 한 칸씩 더 붙여서 확인한다.
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            q = new ArrayDeque<>();
            int count = 0;
            int sum = 0;

            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (count < 2) {
                    count++;
                    sum += map[rowIdx][colIdx];

                    q.addLast(new Location(rowIdx, colIdx));
                } else {
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));
                    count++;

                    // 새로운 칸을 넣기 전에 
                    // 3칸 직선으로 경우에 수 확인
                    for (Location loc : q) {

                        int row = loc.rowIdx;
                        int col = loc.colIdx;

                        for (int idx = 0; idx < dThree.length; idx++) {
                            int newRow = row + dThree[idx];

                            if (checkBoundary(newRow, col)) {

                                int temp = sum + map[newRow][col];

                                if (answer < temp) {
                                    answer = temp;
                                }
                            }
                        }
                    }

                    // 첫번째 인덱스 제거
                    Location loc = q.pollFirst();
                    sum -= map[loc.rowIdx][loc.colIdx];
                    count--;
                }
            }
        }


        // 3칸을 만들고
        // 세로기준 위,아래에 한 칸씩 더 붙여서 확인한다.
        for (int colIdx = 0; colIdx < colSize; colIdx++) {
            q = new ArrayDeque<>();
            int count = 0;
            int sum = 0;

            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {


                if (count < 2) {
                    count++;
                    sum += map[rowIdx][colIdx];

                    q.addLast(new Location(rowIdx, colIdx));
                } else {
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));
                    count++;

                    // 새로운 칸을 넣기 전에 
                    // 3칸 직선으로 경우에 수 확인
                    for (Location loc : q) {

                        int row = loc.rowIdx;
                        int col = loc.colIdx;

                        for (int idx = 0; idx < dThree.length; idx++) {
                            int newCol = col + dThree[idx];

                            if (checkBoundary(row, newCol)) {

                                int temp = sum + map[row][newCol];

                                if (answer < temp) {
                                    answer = temp;
                                }
                            }
                        }
                    }

                    // 첫번째 인덱스 제거
                    Location loc = q.pollFirst();
                    sum -= map[loc.rowIdx][loc.colIdx];
                    count--;
                }
            }
        }

    }

    static void checkTwo() {
        // 2칸을 만들고
        // 가로기준 위,아래에 한 칸씩 더 붙여서 확인한다.
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            q = new ArrayDeque<>();
            int count = 0;
            int sum = 0;

            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (count < 1) {
                    count++;
                    sum += map[rowIdx][colIdx];

                    q.addLast(new Location(rowIdx, colIdx));
                } else {
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));
                    count++;

                    // 새로운 칸을 넣기 전에 
                    // 2칸 직선으로 경우에 수 확인
                    for (int idx = 0; idx < 2; idx++) {

                        Location first = q.getFirst();
                        Location last = q.getLast();

                        int newFirst = first.rowIdx + dThree[idx];

                        if (!checkBoundary(newFirst, first.colIdx)) {
                            continue;
                        }

                        int newLast = last.rowIdx - dThree[idx];

                        if (!checkBoundary(newLast, last.colIdx)) {
                            continue;
                        }

                        int temp = sum + map[newFirst][first.colIdx] + map[newLast][last.colIdx];

                        if (answer < temp) {
                            answer = temp;
                        }
                    }

                    // 첫번째 인덱스 제거
                    Location loc = q.pollFirst();
                    sum -= map[loc.rowIdx][loc.colIdx];
                    count--;
                }
            }
        }


        // 2칸을 만들고
        // 세로기준 위,아래에 한 칸씩 더 붙여서 확인한다.
        for (int colIdx = 0; colIdx < colSize; colIdx++) {
            q = new ArrayDeque<>();
            int count = 0;
            int sum = 0;

            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                if (count < 1) {
                    count++;
                    sum += map[rowIdx][colIdx];

                    q.addLast(new Location(rowIdx, colIdx));
                } else {
                    sum += map[rowIdx][colIdx];
                    q.addLast(new Location(rowIdx, colIdx));
                    count++;

                    // 새로운 칸을 넣기 전에 
                    // 2칸 직선으로 경우에 수 확인
                    for (int idx = 0; idx < 2; idx++) {

                        Location first = q.getFirst();
                        Location last = q.getLast();

                        int newFirst = first.colIdx + dThree[idx];

                        if (!checkBoundary(first.rowIdx, newFirst)) {
                            continue;
                        }

                        int newLast = last.colIdx - dThree[idx];

                        if (!checkBoundary(last.rowIdx, newLast)) {
                            continue;
                        }

                        int temp = sum + map[first.rowIdx][newFirst] + map[last.rowIdx][newLast];

                        if (answer < temp) {
                            answer = temp;
                        }
                    }

                    // 첫번째 인덱스 제거
                    Location loc = q.pollFirst();
                    sum -= map[loc.rowIdx][loc.colIdx];
                    count--;
                }
            }
        }

    }

    static void checkFour() {

        int sum = 0;

        for (int rowIdx = 0; rowIdx < rowSize - 1; rowIdx++) {

            for (int colIdx = 0; colIdx < colSize - 1; colIdx++) {

                sum = 0;

                for (int r = rowIdx; r < rowIdx + 2; r++) {
                    for (int c = colIdx; c < colIdx + 2; c++) {
                        sum += map[r][c];
                    }
                }

                if (answer < sum) {
                    answer = sum;
                }

            }
        }

    }

    static void dfs(int rowIdx, int colIdx, int length, int cost) {
        if (length == 4) {
            answer = Math.max(answer, cost);
            return;
        }

        // ㅜ, ㅗ는 dfs만으로 처리할 수 없기 때문에 길이가 3인 ㅡ 단계에서 처리해야함
        if (length == 3) {
            for (int idx = 0; idx < 4; idx++) {

                // ㅡ 확인, 2칸을 이동했을 때 이미 선택한 위치면 ㅡ 모양임
                int newRow = rowIdx + 2 * dx[idx];
                int newCol = colIdx + 2 * dy[idx];

                if (!checkBoundary(newRow, newCol)) {
                    continue;
                }

                if (visited[newRow][newCol]) {
                    for (int checkIdx = 0; checkIdx < 2; checkIdx++) {
                        int tempRow = (rowIdx + newRow) / 2 + dx[(idx + 2 * checkIdx + 1) % 4];
                        int tempCol = (colIdx + newCol) / 2 + dy[(idx + 2 * checkIdx + 1) % 4];

                        if (!checkBoundary(tempRow, tempCol)) {
                            continue;
                        }

                        answer = Math.max(answer, cost + map[tempRow][tempCol]);
                    }
                }

            }
        }


        for (int idx = 0; idx < 4; idx++) {
            int newRow = rowIdx + dx[idx];
            int newCol = colIdx + dy[idx];

            if (!checkBoundary(newRow, newCol)) {
                continue;
            }

            if (!visited[newRow][newCol]) {
                visited[newRow][newCol] = true;
                dfs(newRow, newCol, length + 1, cost + map[newRow][newCol]);
                visited[newRow][newCol] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputData();

        // checkStraight();
        // checkThree();
        // checkTwo();
        // checkFour();

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                visited[rowIdx][colIdx] = true;
                dfs(rowIdx, colIdx, 1, map[rowIdx][colIdx]);
                visited[rowIdx][colIdx] = false;
            }
        }

        System.out.println(answer);

    }
}
