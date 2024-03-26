package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2001 {

    /*
     * NxN 영역에 MxM 영역에 해당하는 값의 합 중 최대를 구하라
     * 
     * 5<=N<=15
     * 2<=M<=N
     * 
     * NxN 영역안에 MxM 영역을모두 확인해야한다
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;

    public static int testCase;
    public static int[][] map;
    public static int mapSize;
    public static int catchSize;
    public static int answer;

    public static int getFly(int x, int y) {
        int result = 0;
        for (int rowIdx = 0; rowIdx < catchSize; rowIdx++) {
            for (int colIdx = 0; colIdx < catchSize; colIdx++) {
                result += map[x + rowIdx][y + colIdx];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            mapSize = Integer.parseInt(st.nextToken());
            catchSize = Integer.parseInt(st.nextToken());
            answer = 0;

            map = new int[mapSize][mapSize];

            // map 입력 받기
            for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
                st = new StringTokenizer(br.readLine().trim());

                for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                    map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                }
            }

            for (int rowIdx = 0; rowIdx <= mapSize - catchSize; rowIdx++) {
                for (int colIdx = 0; colIdx <= mapSize - catchSize; colIdx++) {
                    answer = Math.max(answer, getFly(rowIdx, colIdx));
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

    }

}