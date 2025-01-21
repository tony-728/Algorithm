package boj;

import java.util.*;
import java.io.*;

public class problem2169 {

    /*
     * NxM 배열
     * 
     * 로봇은 왼, 오, 아래쪽으로 이동할 수 있다.
     * 한 번 탐사한 지역은 탐사하지 않는다.
     * 
     * 로봇을 (1,1)에서 출발해서 (N,M)으로 보내려고 한다.
     * 
     * 위 조건을 만족하면서 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램
     * 
     * bfs
     * 방문 배열을 3차원으로 관리해야함
     * 위로는 갈 수 없으므로 아래는 신경쓰지 않아도 된다.
     * 오른쪽과 왼쪽은 서로 이동할 수 있으므로 방문관리를 해주어야 한다.
     * - 왼쪽에서 왔을 때는 왼쪽으로 갈 수 없다.(이동방향은 오른쪽)
     * - 오른쪽에서 왔을 때는 오른쪽으로 갈 수 없다.(이동방향은 왼쪽)
     * - 맵의 비용이 모두 동일할 때 때 중복해서 들어가는 좌표가 발생한다. => N이 커질 수록 비용이 비슷한 값이 많을 수록 중복 좌표가 pq에 많이 들어가기 때문에 시간 초과가 발생한다.
     * 
     * dp
     * - 위로 가는 경우가 없기 때문에 비교적 쉽게 dp로 최고 비용을 계산할 수 있다.
     * - 오른쪽으로 이동, 왼쪽으로 이동한 누적 비용을 계산한 큰 값을 dp 배열에 넣어준다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;
    static int[][][] dp;
    static int answer;

    static void inputData() throws IOException {
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize + 1][colSize + 1];

        // 0이 오른쪽, 1이 왼쪽
        dp = new int[rowSize + 1][colSize + 2][2];

        for (int rowIdx = 1; rowIdx <= rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 1; colIdx <= colSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열 초기화
        for (int rowIdx = 0; rowIdx <= rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx <= colSize + 1; colIdx++) {
                // dp 값 초기화를 -101로 하면 안된다. 할꺼면 -100 * 1000 -1 이하
                dp[rowIdx][colIdx][0] = -987654321;
                dp[rowIdx][colIdx][1] = -987654321;
            }
        }

        // 초기값 세팅
        dp[1][1][0] = map[1][1];
        for (int colIdx = 2; colIdx <= colSize; colIdx++) {
            dp[1][colIdx][0] = dp[1][colIdx - 1][0] + map[1][colIdx];
        }
    }

    static void solution() {

        int temp = 0;
        int temp2 = 0;

        for (int rowIdx = 2; rowIdx <= rowSize; rowIdx++) {
            // 오른쪽으로 누적합
            for (int colIdx = 1; colIdx <= colSize; colIdx++) {
                temp = Math.max(dp[rowIdx - 1][colIdx][0], dp[rowIdx - 1][colIdx][1])
                        + map[rowIdx][colIdx];

                temp2 = dp[rowIdx][colIdx - 1][0] + map[rowIdx][colIdx];

                dp[rowIdx][colIdx][0] = Math.max(temp, temp2);

            }

            // 왼쪽으로 누적합
            for (int colIdx = colSize; colIdx > 0; colIdx--) {

                temp = Math.max(dp[rowIdx - 1][colIdx][0], dp[rowIdx - 1][colIdx][1])
                        + map[rowIdx][colIdx];

                temp2 = dp[rowIdx][colIdx + 1][1] + map[rowIdx][colIdx];

                dp[rowIdx][colIdx][1] = Math.max(temp, temp2);

            }
        }

        answer = Math.max(dp[rowSize][colSize][0], dp[rowSize][colSize][1]);
    }


    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
