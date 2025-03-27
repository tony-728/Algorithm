package boj;

import java.io.*;
import java.util.*;

public class problem2342 {
    /*
     * 하나의 중점을 기준으로 위, 아래, 왼쪽, 오른쪽으로 연결
     * - 중심: 0, 위: 1, 왼: 2, 아래: 3, 오: 4
     * 
     * 두발을 중앙에 위치, 지시에 따라 왼쪽, 오른쪽 발을 움직인다.
     * - 두 발을 동시에 움직이지 않는다.
     * - 두 발이 같은 지점에 있으면 안된다.
     * 
     * 위치에 따른 힘이 다르다
     * - 중앙에 있던 발이 다른 지점으로 움직일 때 2의 힘을 사용
     * - 다른 지점에서 인접한 지점으로 움직일 때 3의 힘을 사용
     *  - 예를 들어 왼쪽에서 위나 아래오 이동할 때
     * - 반대편으로 움직일 때는 4의 힘을 사용
     *  - 왼 -> 오 / 위 -> 아래
     * - 같은 지점을 한번더 누른다면 1의 힘을 사용
     * 
     * 지시 사항이 주어졌을 때 최소의 힘으로 해결하는 값을 구하라
     * 
     * dp
     * 현재 스텝에 대해서 
     * 왼쪽 발을 위치별로 움직이는 비용
     * 오른쪽 발을 위치별로 움직이는 비용
     * 을 모두 계산해서 그 중 최소값을 구한다.
     * 
     */

    static int[] arrOfCommand;
    static int numOfCommand;
    static int answer;
    static int[][][] dp;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 987654321;

        st = new StringTokenizer(br.readLine().trim());

        numOfCommand = st.countTokens();
        arrOfCommand = new int[numOfCommand];

        // [현재 스텝][왼발 위치][오른발 위치]
        // 위치는 중심, 위, 왼, 아래, 오
        dp = new int[numOfCommand][5][5];

        for (int idx = 0; idx < numOfCommand; idx++) {
            arrOfCommand[idx] = Integer.parseInt(st.nextToken());

            // 비용을 더미값으로 초기화 bottom-up 방식에서는 필요함
            // for (int left = 0; left < 5; left++) {
            //     Arrays.fill(dp[idx][left], 987654321);
            // }
        }
    }

    static int calCost(int footIdx, int step) {
        int cost = 0;

        // 처음 발이 중심에 있을 때
        if (footIdx == 0) {
            cost = 2;

            // 발과 스텝이 같은 위치에 있을 때
        } else if (footIdx == step) {
            cost = 1;

            // 발과 스텝이 반대에 있을 때
        } else if (footIdx % 2 == step % 2) {
            cost = 4;

            // 발과 스텝이 인접할 때
        } else {
            cost = 3;
        }

        return cost;
    }

    static int search(int idx, int left, int right) {
        if (idx == numOfCommand - 1) {
            return 0;
        }

        // 이미 밟아본 발판
        if (dp[idx][left][right] != 0) {
            return dp[idx][left][right];
        }

        int step = arrOfCommand[idx];

        // 현재 발판의 비용은
        // (다음 인덱스 비용 + 왼발로 밟았을 때 비용, 다음 인덱스 비용 + 오른발로 밟았을 때 비용) 중 최소가 된다.
        dp[idx][left][right] = Math.min(search(idx + 1, step, right) + calCost(left, step),
                search(idx + 1, left, step) + calCost(right, step));

        return dp[idx][left][right];
    }

    static void solution() {

        // 초기 위치는 왼쪽, 오른쪽이 0, 0에 위치한다.
        dp[0][0][0] = 0;


        // 왼발로 스텝을 움직인다. = min(왼발로 스텝을 움직인다., 현재 발 위치까지의 비용 + 현재 왼발로 스텝을 움직일 때 비용)
        // 오른발로 스텝을 움직인다. = min(오른발로 스텝을 움직인다., 현재 발 위치까지의 비용 + 현재 오른발로 스텝을 움직일 때 비용)

        for (int idx = 0; idx < numOfCommand - 1; idx++) {

            // 현재 스텝
            int step = arrOfCommand[idx];

            // 왼쪽발을 위치(중심, 위, 왼, 아래, 오)별로 움직일 때 비용을 계산
            for (int left = 0; left < 5; left++) {
                // 오른쪽발을 위치별로 움직일 때 비용을 계산
                for (int right = 0; right < 5; right++) {
                    // 왼발을 현재 스텝으로 움직일 때
                    dp[idx + 1][step][right] = Math.min(dp[idx + 1][step][right],
                            dp[idx][left][right] + calCost(left, step));

                    // 오른발을 현재 스텝으로 움직일 때
                    dp[idx + 1][left][step] = Math.min(dp[idx + 1][left][step],
                            dp[idx][left][right] + calCost(right, step));
                }
            }
        }

        for (int left = 0; left < 5; left++) {
            for (int right = 0; right < 5; right++) {
                answer = Math.min(answer, dp[numOfCommand - 1][left][right]);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputData();

        // solution();
        answer = search(0, 0, 0);

        System.out.println(answer);

    }
}
