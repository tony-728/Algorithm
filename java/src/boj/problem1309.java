package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1309 {
    /*
     * 가로로 두칸 세로로 n칸인 우리가 있다.
     * 
     * 사자들을 우리에 가둘 때, 가로로도, 세로로도 붙어 있게 배치할 수 없다.
     * 
     * 2*n 배열에 사자를 배치하는 경우의 수가 몇가지인지 알아내는 프로그램
     * - 사자를 한 마리도 배치하지 않는 경우도 하나의 경우의 수로 친다
     * 
     * 사자를 배치하는 경우의 수를 9901로 나눈 나머지를 출력
     * 
     * dp
     * 위, 아래, 없음을 순서대로 이전값을 누적
     * 위일 때는 이전 인덱스에 아래,없음
     * 아래일 때는 이전 인덱스에 위,없음
     * 없음일 때는 이전 인덱스에 위,아래,없음
     * 
     * 위   1   1+1     2+3     5+7
     * 아래 1   1+1     2+3     5+7
     * 없음 1   1+1+1   2+2+3   5+5+7
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int STANDARD = 9901;

    static final int EXIST = 0;
    static final int EMPTY = 1;

    static int cageSize;
    static int answer;

    static void solution() {

        int[][] dp = new int[2][cageSize + 1];

        dp[EXIST][1] = 1;
        dp[EMPTY][1] = 1;

        for (int idx = 2; idx < cageSize + 1; idx++) {
            dp[EXIST][idx] = (dp[EXIST][idx - 1] + dp[EMPTY][idx - 1]) % STANDARD;
            dp[EMPTY][idx] = (dp[EXIST][idx - 1] * 2 + dp[EMPTY][idx - 1]) % STANDARD;
        }

        answer = (dp[EXIST][cageSize] * 2 + dp[EMPTY][cageSize]) % STANDARD;
    }

    public static void main(String[] args) throws IOException {

        cageSize = Integer.parseInt(br.readLine().trim());

        solution();

        System.out.println(answer);
    }
}
