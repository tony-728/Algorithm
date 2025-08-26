package boj;

import java.io.*;
import java.util.*;

public class problem2294 {
    /*
     * n가지 동전이 있다. 
     * 이 동전을 적당히 사용해서 그 가치의 합이 k원이 되도록 한다.
     * 그러면서 동전의 갯수가 최소가 되도록 한다. 
     * 각각의 동전은 몇 개라도 사용할 수 있다.
     * 
     * 1<=n<=100, 1<=k<=10,000
     * 
     * 동전의 가치는 100,000보다 작거나 같은 자연수
     * 가치가 같은 동전이 여러 번 주어질 수 있다.
     * 
     * dp
     * dp[만드는 동전의 값] = 만드는 동전의 값에 사용되는 최소 동전 갯수
     * dp 배열은 K까지 커버할 수 있도록 1차원 배열로 만든다.
     * 주어진 동전을 이용해서 만드는 동전의 값의 최소 갯수를 갱신해 나간다.
     * dp[만드는 동전의 값] = min(dp[만드는 동전의 값], dp[만드는 동전의 값 - 현재 동전] +1)
     * 
     * 
     * bfs, 시간초과가 발생할 수 있어서 최적화가 필요함
     * 
     * 
     */
    static int numOfCoin;
    static int[] arrayOfCoin;
    static int target;
    static int answer;
    static int[] dp;

    static void inputData() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfCoin = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        arrayOfCoin = new int[numOfCoin];
        dp = new int[target + 1];

        Arrays.fill(dp, 987654321);

        for (int idx = 0; idx < numOfCoin; idx++) {
            int coin = Integer.parseInt(br.readLine().trim());

            arrayOfCoin[idx] = coin;
        }
    }

    static void solution() {

        dp[0] = 0;

        for (int idx = 0; idx < numOfCoin; idx++) {
            for (int dpIdx = arrayOfCoin[idx]; dpIdx <= target; dpIdx++) {
                dp[dpIdx] = Math.min(dp[dpIdx], dp[dpIdx - arrayOfCoin[idx]] + 1);
            }
        }

        if (dp[target] == 987654321) {
            dp[target] = -1;
        }

        answer = dp[target];

    }

    public static void main(String[] args) throws IOException {
        inputData();
        solution();
        System.out.println(answer);
    }
}
