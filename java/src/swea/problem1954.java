package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1954 {

    /*
     * 테스트 케이스의 개수를 입력받는다.
     * 
     * 달팽이를 그릴 N을 입력받는다.
     * 
     * 
     */

    public static BufferedReader br;
    public static int testCase;

    public static int N;
    public static int[][] map;

    public static int[] deltaX = { 0, 1, 0, -1 };
    public static int[] deltaY = { 1, 0, -1, 0 };

    public static int goHead;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            map = new int[N][N];

            int number = 1;

            int rowIdx = 0;
            int colIdx = 0;

            // 0,0 부터 시작
            // (0, 1), (1, 0), (0, -1), (-1, 0)
            // 방향 순서로 바꾼다.
            // 방향을 바꾸는 기준은 범위를 벗어나거나, 0이 아닌 값이 들어있거나

            goHead = 0;

            map[rowIdx][colIdx] = number++;

            while (number <= N * N) {

                rowIdx = rowIdx + deltaX[goHead % 4];
                colIdx = colIdx + deltaY[goHead % 4];

                if (rowIdx < 0 || rowIdx >= N || colIdx < 0 || colIdx >= N) {
                    // 넘어갔으면 다시 돌려야함
                    rowIdx = rowIdx - deltaX[goHead % 4];
                    colIdx = colIdx - deltaY[goHead % 4];

                    goHead++;
                    continue;
                }

                if (map[rowIdx][colIdx] != 0) {
                    rowIdx = rowIdx - deltaX[goHead % 4];
                    colIdx = colIdx - deltaY[goHead % 4];

                    goHead++;
                    continue;
                }

                map[rowIdx][colIdx] = number++;
            }

            System.out.println("#" + tc);

            for (int rIdx = 0; rIdx < N; rIdx++) {
                for (int cIdx = 0; cIdx < N; cIdx++) {
                    System.out.print(map[rIdx][cIdx] + " ");
                }
                System.out.println();
            }
        }

    }

}