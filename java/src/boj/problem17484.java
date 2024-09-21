package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem17484 {

    /*
     * NxM 행렬
     * 우주선은 우하, 하, 좌하로 내려갈 수 있다.
     * 우주선은 같은 방향으로 두번 연속으로 움직일 수 없다.
     * 연료를 아끼면서 달로 도착하기
     * 
     * dfs 백트래킹
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;

    static int result;

    static final int[] DX = {1, 1, 1};
    static final int[] DY = {-1, 0, 1};

    static final int LEFT_DOWN = 0;
    static final int DOWN = 1;
    static final int RIGHT_DOWN = 2;

    static void inputTestCase() throws IOException {

        result = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean checkRange(int rowIdx, int colIdx) {
        if (rowIdx < 0 || rowIdx >= rowSize || colIdx < 0 || colIdx >= colSize) {
            return true;
        }

        return false;
    }

    static void goMoon(int rowIdx, int colIdx, int prevDirection, int totalFuel) {

        // 이미 최대 값보다 크면 가지 않아도 된다.
        if (totalFuel >= result) {
            return;
        }

        // rowIdx가 0일 땐 모든 방향에 대해서 진행해야함
        if (rowIdx == 0) {

            for (int dir = 0; dir < DX.length; dir++) {
                int newRowIdx = rowIdx + DX[dir];
                int newColIdx = colIdx + DY[dir];

                // 범위 확인
                if (checkRange(newRowIdx, newColIdx)) {
                    continue;
                }

                // 다음 방향으로 진행
                goMoon(newRowIdx, newColIdx, dir, totalFuel + map[newRowIdx][newColIdx]);
            }
        } else {
            for (int dir = 0; dir < DX.length; dir++) {
                // 이전 방향과 같으면 안됨
                if (dir == prevDirection) {
                    continue;
                }

                int newRowIdx = rowIdx + DX[dir];
                int newColIdx = colIdx + DY[dir];

                // 마지막까지 왔으면 종료 및 갱신
                if (newRowIdx == rowSize) {
                    if (totalFuel < result) {
                        result = totalFuel;
                    }
                    continue;
                }

                // 범위 확인
                if (checkRange(newRowIdx, newColIdx)) {
                    continue;
                }

                // 다음 방향으로 진행
                goMoon(newRowIdx, newColIdx, dir, totalFuel + map[newRowIdx][newColIdx]);
            }
        }


    }


    public static void main(String[] args) throws IOException {

        inputTestCase();


        for (int colIdx = 0; colIdx < colSize; colIdx++) {

            goMoon(0, colIdx, 0, map[0][colIdx]);
        }

        System.out.println(result);

    }
}
