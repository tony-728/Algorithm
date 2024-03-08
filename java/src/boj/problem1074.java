package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1074 {
    /*
     * 2^N x 2^N 2차원 배열
     * N이 1인 경우 상좌, 상우, 하좌, 하우 순으로 z로 방문한다.
     * 
     * N>1인 경우 2^N-1 x 2^N-1로 4등훈 한 후에 z로 방문한다.
     * 
     * N이 주어졌을 때 (r,c)를 몇 번째로 방문하는지 구하라
     * 
     * 1 <= N <= 15
     * 
     * N이 1이 될 때까지 2차원 배열을 4등분으로 쪼갠다.
     * - 4등분으로 쪼개면서 쪼개진 영역을 가기 위해 지나갈 칸 수를 미리 계산한다.
     * 1이 되면 z 순으로 목표 행과 열을 만날때까지 방문한다.
     * - 방문하면서 카운트를 올린다.
     * 
     */
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int N;
    public static int targetRow, targetCol;

    public static final int Z_OFFSET = 2;

    /**
     * 4등분하여 지나왔다고 처리될 영역의 넓이를 구함
     * 
     * @param n 영역의 넓이를 구하기 위한 정사각형의 한 변의 길이
     * @return 영역의 넓이
     */
    public static int calPreviousMap(int n) {
        return (int) Math.pow(2, n + n);
    }

    /**
     * 
     * @param n          2^n x 2^n 영역을 확인하기 위한 n
     * @param visitCount 현재까지 지나온 칸
     * @param rowIdx     목표 행
     * @param colIdx     목표 열
     */
    public static void divideMap(int n, int visitCount, int rowIdx, int colIdx) {

        // n이 1일 땐 더 이상 나눌 영역이 없다
        if (n == 1) {
            // rowIdx, colIdx 부터 z로 방문
            for (int row = 0; row < Z_OFFSET; row++) {
                for (int col = 0; col < Z_OFFSET; col++) {
                    // 방문할 때 목표 행,열 이면 종료
                    if (row == rowIdx && col == colIdx) {
                        System.out.println(visitCount);
                        return;
                    }

                    // 방문하면서 카운트 증가
                    visitCount++;
                }
            }
            return;
        }

        // 기존 영역을 4등분하면서 확인할 영역이 반으로 줄어듬
        int halfN = (int) Math.pow(2, n - 1);
        int originN = (int) Math.pow(2, n);

        // z로 순회할 수 있도록
        // 1사분면
        if (0 <= rowIdx && rowIdx < halfN && 0 <= colIdx && colIdx < halfN) {
            divideMap(n - 1, visitCount, rowIdx, colIdx);

            // 2사분면
        } else if (0 <= rowIdx && rowIdx < halfN
                && halfN <= colIdx && colIdx < originN) {
            divideMap(n - 1, visitCount + calPreviousMap(n - 1), rowIdx, colIdx % halfN);

            // 3사분면
        } else if (halfN <= rowIdx && rowIdx < originN
                && 0 <= colIdx && colIdx < halfN) {
            divideMap(n - 1, visitCount + calPreviousMap(n - 1) * 2, rowIdx % halfN, colIdx);

            // 4사분면
        } else if (halfN <= rowIdx && rowIdx < originN
                && halfN <= colIdx && colIdx < originN) {
            divideMap(n - 1, visitCount + calPreviousMap(n - 1) * 3, rowIdx % halfN,
                    colIdx % halfN);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        targetRow = Integer.parseInt(st.nextToken());
        targetCol = Integer.parseInt(st.nextToken());

        divideMap(N, 0, targetRow, targetCol);
    }
}