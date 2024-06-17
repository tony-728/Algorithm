package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem4613 {
    /*
     * N행 M열로 나뉘어 있다
     * 
     * 각 칸은 흰색, 파란색, 빨간색 중 하나로 칠해져 있다.
     * 
     * 몇 개의 칸에 있는 색을 다시 칠해서 이 깃발을 러시아 국기처럼 만들려고 한다.
     * 
     * 위에서 몇줄(하나 이상)은 모두 흰색으로 칠해져 있어야 한다.
     * 다음 몇줄(하나 이상)은 모두 파란색으로 칠해져 있어야 한다.
     * 다음 몇줄(하나 이상)은 모두 빨간색으로 칠해져 있어야 한다.
     * 
     * 새로 칠해야 하는 칸의 개수의 최솟값을 구하여라
     * 
     * 테스트케이스가 주어진다.
     * 행과 열이 각가 주어진다.
     * 행과 열에 대응되는 색이 주어진다.
     * 
     * 첫번째 줄은 무조건 흰색, 마지막줄은 무조건 빨간색
     * 
     * 첫번째 깊이는 흰색
     * 마지막 깊이는 빨간색
     * 흰, 파, 빨 순으로 dfs
     * 중간에 파란색이 무조건 들어가야함
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;

    static int numOfRow;
    static int numOfCol;
    static int[][] flag;

    static final char CHAR_WHITE = 'W';
    static final char CHAR_RED = 'R';
    static final char CHAR_BLUE = 'B';

    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;

    static int blueCheck;
    static int redCheck;

    static int answer;

    static void inputTestCase() throws IOException {

        answer = Integer.MAX_VALUE;

        blueCheck = 0;
        redCheck = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfRow = Integer.parseInt(st.nextToken());
        numOfCol = Integer.parseInt(st.nextToken());

        flag = new int[numOfRow][numOfCol];

        // 국기 입력
        for (int rowIdx = 0; rowIdx < numOfRow; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < numOfCol; colIdx++) {
                char val = row.charAt(colIdx);

                if (val == CHAR_WHITE) {
                    flag[rowIdx][colIdx] = WHITE;
                } else if (val == CHAR_RED) {
                    flag[rowIdx][colIdx] = RED;
                } else if (val == CHAR_BLUE) {
                    flag[rowIdx][colIdx] = BLUE;
                }
            }
        }
    }

    static void makeFlag(int depth, int color, int count) {

        // 가지치기 정답보다 지금까지의 값이 크면 더이상 확인하지 않는다.
        if (answer < count) {
            return;
        }

        int localCount = 0;
        // 무조건 흰색
        if (depth == 0) {
            for (int colIdx = 0; colIdx < numOfCol; colIdx++) {
                if (flag[depth][colIdx] != WHITE) {
                    localCount++;
                }
            }
        }
        // 무조건 빨간색
        // 기저조건
        else if (depth == numOfRow - 1) {
            for (int colIdx = 0; colIdx < numOfCol; colIdx++) {
                if (flag[depth][colIdx] != RED) {
                    localCount++;
                }
            }

            if (answer > count + localCount) {
                answer = count + localCount;
            }

            return;
        } else {

            for (int colIdx = 0; colIdx < numOfCol; colIdx++) {
                if (flag[depth][colIdx] != color) {
                    localCount++;
                }
            }
        }


        // 현재 색이 흰색이고 마지막-1 행이라면 무조건 파랑을 넣어야함
        if (depth + 1 == numOfRow - 2 && color == WHITE) {
            makeFlag(depth + 1, BLUE, count + localCount);
            return;
        }

        // 현재 흰색이면 다음 색상은 흰색과 파란색
        if (color == WHITE) {
            makeFlag(depth + 1, WHITE, count + localCount);

            makeFlag(depth + 1, BLUE, count + localCount);

            // 현재 파랑색이면 다음 색상은 파란색과 빨간색
        } else if (color == BLUE) {
            makeFlag(depth + 1, BLUE, count + localCount);

            makeFlag(depth + 1, RED, count + localCount);

            // 현재 빨간색이면 다음 색상은 무조건 빨간색
        } else if (color == RED) {
            makeFlag(depth + 1, RED, count + localCount);
        }

    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            makeFlag(0, 0, 0);

            sb.append(String.format("#%d %d\n", tc, answer));
        }

        System.out.println(sb);

    }
}