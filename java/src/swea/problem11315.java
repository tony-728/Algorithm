package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem11315 {

    /*
     * 
     * NxN 크기의 판이 있다.
     * 돌이 있는 경우 가로, 세로, 대각선 중 하나의 방향으로 다섯 개 이상 연속한 부분이 있는지 확인
     * 
     * 돌이 있는 경우 O, 없는 경우 .
     * 
     * 연속한 부분이 있으면 YES 없으면 NO
     * 
     * 5<= N <=20
     * 
     * 
     */

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;

    public static int testCase;
    public static int mapSize;
    public static char[][] map;
    public static String row;

    public static final int OMOK = 5;
    public static final char BLANK = '.';
    public static final char DOL = 'o';
    // 상, 상우, 상좌, 좌, 우, 좌하, 하, 우하
    public static final int[] deltaX = { -1, -1, -1, 0, 0, 1, 1, 1 };
    public static final int[] deltaY = { 0, 1, -1, -1, 1, -1, 0, 1 };

    public static boolean isOmok;
    public static String answer;

    public static void checkOmok(int rowIdx, int colIdx) {
        // 상하좌우 / 대각선을 확인하여 5개 이상 돌이 연속한지 확인한다.

        for (int dir = 0; dir < deltaX.length; dir++) {
            int count = 1;

            int newRow = rowIdx + deltaX[dir];
            int newCol = colIdx + deltaY[dir];

            // 범위를 벗어나는 경우
            if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
                continue;
            }

            // 빈곳인 경우
            if (map[newRow][newCol] == BLANK) {
                continue;
            }

            count++;
            while (true) {
                newRow = newRow + deltaX[dir];
                newCol = newCol + deltaY[dir];

                // 범위를 벗어나는 경우
                if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
                    break;
                }

                // 빈곳인 경우
                if (map[newRow][newCol] == BLANK) {
                    break;
                }

                ++count;

                if (count >= OMOK) { // 5개 이상이면 오목이 됨
                    isOmok = true;
                    return;
                }

            }

        }

    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= testCase; tc++) {
            isOmok = false;
            answer = "NO";

            mapSize = Integer.parseInt(br.readLine().trim());

            map = new char[mapSize][mapSize];

            // 오목판 입력
            for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
                map[rowIdx] = br.readLine().toCharArray();

            }

            // 반복문마다 별칭을 만들고
            // 해당 별칭에 반복문을 탈출하도록 할 수 있다.
            loop1: for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
                for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                    if (map[rowIdx][colIdx] == DOL) {
                        // 오목 검사
                        checkOmok(rowIdx, colIdx);
                    }

                    if (isOmok) {
                        break loop1;
                    }
                }
            }

            if (isOmok) {
                answer = "YES";
            }

            sb.append(String.format("#%d %s\n", tc, answer));

        }

        System.out.println(sb);
    }
}