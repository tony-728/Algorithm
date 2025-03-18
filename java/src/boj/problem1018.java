package boj;

import java.io.*;
import java.util.*;

public class problem1018 {
    /*
     * MxN 보드에 어떤 정사각형은 검은색, 나머지는 흰색
     * 이 보드를 잘라서 8x8 크기의 체스판으로 만들려고 한다.
     * 
     * 채스판이 되기 위해 맨 왼쪽 위칸이 흰색 또는 검은색인 경우
     * 8x8로 자른뒤 몇 개의 정사각형을 다시 칠한다.
     * 다시 칠하는 정사각형의 개수가 최소일 때를 구하라
     * 
     * 완탐
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static boolean[][] map;

    static final int CHESS_SIZE = 8;

    static int answer;

    static void inputData() throws IOException {
        answer = 987654321;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new boolean[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String row = br.readLine().trim();

            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = row.charAt(colIdx) == 'B' ? true : false;
            }
        }
    }

    static void countBlock(int row, int col) {
        // 시작 블록을 검정색, 흰색으로 칠하는 경우를 모두 체크해야함
        int count = 0;
        int reverseCount = 0;

        boolean originBlock = map[row][col];
        boolean reverseBlock = !map[row][col];

        for (int rowIdx = row; rowIdx < row + CHESS_SIZE; rowIdx++) {
            for (int colIdx = col; colIdx < col + CHESS_SIZE; colIdx++) {

                // 오리진 시작점은 통과
                // 리버스는 카운트
                if (rowIdx == row && colIdx == col) {
                    reverseCount++;
                    continue;
                }

                // 오리진 블록, 바둑판을 다시 색칠하는 경우
                if (originBlock == map[rowIdx][colIdx]) {
                    originBlock = !map[rowIdx][colIdx];
                    count++;
                } else {
                    originBlock = map[rowIdx][colIdx];
                }

                // 리버스 블록, 바둑판 다시 색칠
                if (reverseBlock == map[rowIdx][colIdx]) {
                    reverseBlock = !map[rowIdx][colIdx];
                    reverseCount++;
                } else {
                    reverseBlock = map[rowIdx][colIdx];
                }
            }

            // 줄바꿈할 때 블록 바꾸기
            originBlock = !originBlock;
            reverseBlock = !reverseBlock;
        }

        count = Math.min(count, reverseCount);
        answer = Math.min(answer, count);
    }

    static void solution() {

        for (int rowIdx = 0; rowIdx <= rowSize - CHESS_SIZE; rowIdx++) {
            for (int colIdx = 0; colIdx <= colSize - CHESS_SIZE; colIdx++) {
                countBlock(rowIdx, colIdx);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);

    }
}
