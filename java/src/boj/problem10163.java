package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10163 {
    /*
     * 겹친 색종이의 면적을 구하는 문제
     * 
     * 입력
     * - 색종이 개수
     * - 왼쪽 아래 색종이 좌표
     * - 너비, 높이
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfPaper;

    static int rowIdx;
    static int colIdx;
    static int width;
    static int height;

    static int[] answer;

    static final int ROW_SIZE = 1001;
    static final int COL_SIZE = 1001;
    static final int[][] map = new int[ROW_SIZE][COL_SIZE];

    static void calArea() {
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int col = 0; col < COL_SIZE; col++) {
                if (map[row][col] > 0) {
                    answer[map[row][col]]++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        numOfPaper = Integer.parseInt(br.readLine().trim());

        answer = new int[numOfPaper + 1];

        for (int idx = 1; idx <= numOfPaper; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            rowIdx = Integer.parseInt(st.nextToken());
            colIdx = Integer.parseInt(st.nextToken());

            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());

            for (int row = rowIdx; row < rowIdx + width; row++) {
                for (int col = colIdx; col < colIdx + height; col++) {
                    map[row][col] = idx;
                }
            }
        }

        calArea();

        for (int idx = 1; idx < answer.length; idx++) {
            System.out.println(answer[idx]);
        }

    }

}
