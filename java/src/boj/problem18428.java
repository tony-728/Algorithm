package boj;

import java.io.*;
import java.util.*;

public class problem18428 {
    /*
     * 선생님은 통과해서 학생을 볼 수 있다.
     * 장애물 뒤에 있는 학생은 볼 수 없다.
     * 
     * 3개의 장애물을 선택해서 모든 학생들이 선생님의 감시를 피하도록 만들자
     * 
     * 빈칸 조합을 통한 완탐
     */

    static class Location {
        int rowIdx;
        int colIdx;

        public Location(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int mapSize;
    static char[][] map;

    static Deque<Location> teacherList = new ArrayDeque<>();
    static Deque<Location> emptyList = new ArrayDeque<>();

    static final char TEACHER = 'T';
    static final char WALL = 'O';
    static final char STUDENT = 'S';
    static final char EMPTY = 'X';

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static String answer;

    static Location[] elementList;
    static Location[] selectList;

    static void inputData() throws IOException {

        answer = "NO";

        mapSize = Integer.parseInt(br.readLine().trim());

        map = new char[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                char val = st.nextToken().charAt(0);

                if (val == TEACHER) {
                    teacherList.addLast(new Location(rowIdx, colIdx));
                }

                if (val == EMPTY) {
                    emptyList.addLast(new Location(rowIdx, colIdx));
                }

                map[rowIdx][colIdx] = val;

            }
        }

        elementList = new Location[emptyList.size()];
        selectList = new Location[3];


        int idx = 0;
        for (Location loc : emptyList) {
            elementList[idx++] = loc;
        }
    }

    static boolean checkBoundary(int row, int col) {
        if (0 <= row && row < mapSize && 0 <= col && col < mapSize) {
            return true;
        } else {
            return false;
        }
    }

    static void check() {
        // 빈칸을 벽으로 바꾸기
        for (Location loc : selectList) {
            map[loc.rowIdx][loc.colIdx] = WALL;
        }

        // 모든 선생님에 대해서 감시 시작
        for (Location teacherLoc : teacherList) {

            int rowIdx = teacherLoc.rowIdx;
            int colIdx = teacherLoc.colIdx;

            for (int dir = 0; dir < dx.length; dir++) {
                int val = 1;
                while (true) {
                    int newRow = rowIdx + dx[dir] * val;
                    int newCol = colIdx + dy[dir] * val;

                    // 범위 확인
                    if (!checkBoundary(newRow, newCol)) {
                        break;
                    }

                    // 벽확인
                    if (map[newRow][newCol] == WALL) {
                        break;
                    }

                    // 학생 확인
                    if (map[newRow][newCol] == STUDENT) {
                        // 벽으로 바꾼 빈칸을 다시 빈칸으로 되돌리기
                        for (Location loc : selectList) {
                            map[loc.rowIdx][loc.colIdx] = EMPTY;
                        }
                        return;
                    }

                    val++;
                }

            }
        }

        // 모든 감시를 통과
        answer = "YES";

    }

    static void combination(int selectIdx, int elementIdx) {

        if (answer == "YES") {
            return;
        }

        if (selectIdx == 3) {
            if (answer == "NO") {
                check();
            }

            return;
        }

        if (elementIdx == emptyList.size()) {
            return;
        }

        // 현재 인덱스를 선택한다.
        selectList[selectIdx] = elementList[elementIdx];
        combination(selectIdx + 1, elementIdx + 1);

        // 현재 인덱스를 선택하지 않는다.
        combination(selectIdx, elementIdx + 1);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        combination(0, 0);

        System.out.println(answer);
    }
}
