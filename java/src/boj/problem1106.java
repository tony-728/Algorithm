package boj;

import java.io.*;
import java.util.*;

public class problem1106 {
    /*
     * 도시가 주어지고 도시 별로 홍보하는데 드는 비용과 그 때 몇 명의 호텔 고객이 늘어나는지에 대한 정보
     * 각 도시에는 무한 명의 잠재 고객이 있다. 호텔의 고객을 적어도 C명 늘이기 위해 투자해야하는 돈의 최소값
     * 
     * 어떤 도시에서 9원을 들여서 홍보하면 3명의 고객이 늘어난다.
     * - 이 정보에 나타난 돈에 정수배 만큼 투자할 수 있다.
     * - 18원에 6명, 27원에 12명
     * 
     * dp
     * knapsack
     * 
     * 적어도 C명을 늘이기 위함이므로 딱 맞출 필요는 없다.
     */

    static int target;
    static int numOfNode;
    static int[][] city;
    static int answer;
    static final int MAX_VALUE = 987654321;

    static void inputData() throws IOException {

        answer = MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        target = Integer.parseInt(st.nextToken());
        numOfNode = Integer.parseInt(st.nextToken());

        city = new int[numOfNode][2];

        for (int idx = 0; idx < numOfNode; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            city[idx][0] = cost;
            city[idx][1] = people;
        }
    }

    static void solution() {
        // 101을 더하는 이유
        // 한번의 홍보로 얻을 수 있는 고객 수는 100보다 작거나 같은 자연수이므로 최대 목표 고객수 1000에 얻을 수 있는 최대 고객수 100이므로 101을 더함
        // dp[index]에는 index 고객을 얻기 위한 최소 금액이 저장됨
        int[] dp = new int[target + 101];

        Arrays.fill(dp, MAX_VALUE);

        dp[0] = 0;

        // 모든 도시에 대해서 확인
        for (int idx = 0; idx < numOfNode; idx++) {

            int cost = city[idx][0];
            int people = city[idx][1];

            // 목표 손님
            for (int peopleIdx = people; peopleIdx < target + 101; peopleIdx++) {
                if (dp[peopleIdx - people] != MAX_VALUE) {
                    dp[peopleIdx] = Math.min(dp[peopleIdx - people] + cost, dp[peopleIdx]);
                }
            }
        }

        for (int idx = target; idx < target + 101; idx++) {
            answer = Math.min(answer, dp[idx]);
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
