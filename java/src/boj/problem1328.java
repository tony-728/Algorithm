package boj;

import java.io.*;
import java.util.*;

public class problem1328 {
    /*
     * 빌딩이 N개 한줄로 있다.
     * 
     * 빌딩의 높이는 1보다 크거나 같고 N보다 작거나 같다.
     * - 같은 높이를 갖는 빌딩은 없다.
     * 
     * 가장 왼쪽에서 빌딩을 몇 개 볼 수 있는지 보았다.
     * 가장 오른쪽에서 빌딩을 몇 개 볼 수 있는지 보았다.
     * 
     * 이 때 가능한 빌딩 순서의 경우의 수를 구하라
     * 
     * dp
     * - dp 아이디어를 생각하지 못해서 힌트 참고
     * 
     * dp가 될 수 있는 근거
     * 1. 빌딩이 한개 있을 때 가능한 경우의 수가 빌딩이 두개 있을 때 가능한 경우의 수에 영향을 미친다.
     * 2. 빌딩의 높이가 1 높아진다고 해서 이전 높이과 경우의 수가 달리지지 않는다.
     * - 예를 들어 높이가 1 2 건물이 있을 때 높이가 2 3로 변했다고 해서 경우의 수가 달리지지 않는다.
     * 3. 1, 2번과 함께 높이가 1인 건물의 위치에 따라서 경우의 수가 결정된다.
     * - 3번으로 점화식을 생각해보자
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfBuilding;
    static int leftCount;
    static int rightCount;

    static final long DIVIDE = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfBuilding = Integer.parseInt(st.nextToken());
        leftCount = Integer.parseInt(st.nextToken());
        rightCount = Integer.parseInt(st.nextToken());

        // 높이, 왼쪽 위치에서 봤을 때 보이는 건물 수, 오른쪽 위치에서 봤을 때 건물 수
        long[][][] dp = new long[101][101][101];


        dp[1][1][1] = 1;


        for (int height = 2; height <= numOfBuilding; height++) {
            for (int leftIdx = 1; leftIdx <= numOfBuilding; leftIdx++) {
                for (int rightIdx = 1; rightIdx <= numOfBuilding; rightIdx++) {
                    dp[height][leftIdx][rightIdx] = (dp[height - 1][leftIdx - 1][rightIdx]
                            + dp[height - 1][leftIdx][rightIdx - 1]
                            + dp[height - 1][leftIdx][rightIdx] * (height - 2)) % DIVIDE;
                }
            }
        }

        System.out.println(dp[numOfBuilding][leftCount][rightCount]);

    }
}
