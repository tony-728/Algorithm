package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2005 {

    /*
     * 파스칼 삼각형
     * 
     * 가장 첫번째 인덱스는 항상 1이다.
     * (가로, 세로 공통)
     * 첫번째 인덱스가 아닌 인덱스는 다음과 같이 값을 구한다.
     * - 이전 row의 중 자신의 col 인덱스-1의 값 + col 인덱스+1의 값
     * - 만약 인덱스를 넘어간다면 그 값은 0으로 한다.
     */

    public static BufferedReader br;
    public static StringBuilder sb;
    public static int N;
    public static int[] pastnumbers = new int[10];
    public static int[] currnumbers = new int[10];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            sb.append("#").append(tc).append("\n");

            // 파스칼 삼각형 그리기
            for (int rowIdx = 0; rowIdx < N; rowIdx++) {
                for (int colIdx = 0; colIdx <= rowIdx; colIdx++) {
                    if (colIdx == 0) {
                        sb.append(1).append(" ");
                        currnumbers[colIdx] = 1;
                    } else if (colIdx == rowIdx) {
                        sb.append(1).append(" ");
                        currnumbers[colIdx] = 1;
                    } else { // 이전 행에서 요소는 현재 인덱스-1 + 현재 인덱스
                        currnumbers[colIdx] = pastnumbers[colIdx - 1] + pastnumbers[colIdx];
                        sb.append(currnumbers[colIdx]).append(" ");
                    }

                }
                for (int idx = 0; idx < 10; idx++) {
                    if (currnumbers[idx] > 0) {
                        pastnumbers[idx] = currnumbers[idx];
                    }
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }

}