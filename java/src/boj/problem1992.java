package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1992 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int mapSize;

    public static char[][] map;

    public static void divideMap(int n, int rowIdx, int colIdx) {

        // 동일
        if (n == 1) {
            sb.append(map[rowIdx][colIdx]);
            return;
        }

        // 확인하는 영역이 모두 동일한지 확인
        char target = map[rowIdx][colIdx];
        boolean divideFlag = false;

        outLoop: for (int row = rowIdx; row < rowIdx + n; row++) {
            for (int col = colIdx; col < colIdx + n; col++) {
                // 확인하는 문자와 다르면 4등분하러 가야지
                if (target != map[row][col]) {
                    divideFlag = true;
                    break outLoop;
                }
            }
        }

        // 동일하지 않으면 4등분
        if (divideFlag) {
            int halfN = n / 2;
            sb.append("(");

            // 1사분면
            divideMap(halfN, rowIdx, colIdx);
            // 2사분면
            divideMap(halfN, rowIdx, colIdx + (halfN));
            // 3사분면
            divideMap(halfN, rowIdx + (halfN), colIdx);
            // 4사분면
            divideMap(halfN, rowIdx + (halfN), colIdx + (halfN));

            sb.append(")");
        } else { // 동일하면 target으로 압축 가능함
            sb.append(target);
        }
    }

    public static void main(String[] args) throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new char[mapSize][mapSize];

        // 맵 입력받기
        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            map[rowIdx] = br.readLine().trim().toCharArray();
        }

        divideMap(mapSize, 0, 0);

        System.out.println(sb);
    }
}