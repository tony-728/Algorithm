package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2999 {
    /*
     * N 글자 메시지
     * 
     * R <= C, R*C=N인 R과 C를 고른다.
     * 경우가 여러개인 경우 R이 큰 값을 선택한다.
     * 행이 R, 열이 C
     * 
     * 암호방법
     * 메시지를 행렬에 옮긴다.
     * 첫번째 행의 첫번째 열부터 C번째 열까지 
     * 
     * 읽을땐 첫번째 열의 첫번째 행부터 R번째 행까지
     * 
     * 복호화방법
     * 첫번째 열의 첫번째 행부터 R번째 행까지 작성
     * 첫번째 행의 첫번째 열부터 C번째 열까지 읽기
     * 
     * 풀이
     * - 주어진 문자열의 길이를 구한다.
     * - 길이로 R,C를 구한다.
     * - 복호화방법으로 원문을 찾기
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String inputString;
    static int lengthOfInput;

    static int rowSize = 0;
    static int colSize = 0;

    static char[][] map;

    static void findRowCol() {
        // 약수 찾기
        for (int idx = 1; idx < (lengthOfInput << 1); idx++) {
            if (lengthOfInput % idx == 0) { // 약수
                if (idx <= lengthOfInput / idx && rowSize < idx) { // row <= col
                    rowSize = idx;
                    colSize = lengthOfInput / idx;
                }
            }
        }

        map = new char[rowSize][colSize];
    }

    static void inputTestCase() throws IOException {
        inputString = br.readLine().trim();

        lengthOfInput = inputString.length();

        findRowCol();
    }

    static void decoding() {
        // write
        // 열을 기준으로 행으로 

        int idx = 0;

        for (int colIdx = 0; colIdx < colSize; colIdx++) {
            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                map[rowIdx][colIdx] = inputString.charAt(idx++);
            }
        }

        // read
        // 행을 기준으로 열로

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                sb.append(map[rowIdx][colIdx]);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        decoding();

        System.out.println(sb);


    }
}
