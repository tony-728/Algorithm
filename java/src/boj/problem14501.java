package boj;

import java.util.Scanner;

public class problem14501 {

    public static int answer = 0;
    static int N;
    static int[] T; // 필요한 상담일수
    static int[] P; // 상담 후 받는 금액

    public static void dfs(int idx, int pay) {

        if (idx >= N) {
            answer = Math.max(answer, pay);
            return;
        }

        // 상담을 완료할 수 있을 때 다음 상담을 하러 이동
        if (idx + T[idx] <= N) {
            dfs(idx + T[idx], pay + P[idx]);
        } else { // 상담을 완료할 수 없으면 상담이 끝난 날로 이동(종료할 수 있는 조건)
            dfs(idx + T[idx], pay);
        }

        // 현재 상담을 하지 않고 다음 상담 확인
        // 바로 상담할 수 있다고 해서 상담하는 것보다 다른 날 상담하는 것이 최선일 수 있다. == 모든 경우를 확인할 수 있다.
        dfs(idx + 1, pay);
    }

    public static void main(String[] args) {
        /*
         * N일의 상담일이 주어진다.
         * 각 일에 걸리는 상담일과 받을 수 있는 금액이 주어진다.
         * 상담을 할 때 상담에 필요한 일 만큼 이후 날짜에 상담을 할 수 없다.
         * 상담을 할 수 있는 N일을 넘어서 상담을 할 수 없다.
         * 
         * 상담을 했을 때 최대 수익을 구하라
         * 
         * 1. 상담할 수 있는 상담일 N과 각 상담일에 걸리는 상담기간, 금액을 입력받는다.
         * 2. 각 일별로 할 수 있는 상담할 수 있는 날짜를 찾고 금액을 계산한다.
         * 2-1. 모든 경우를 탐색해야한다.
         * 3. 가장 많은 금액을 찾는다.
         */
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        T = new int[N];
        P = new int[N];

        for (int idx = 0; idx < N; idx++) {
            T[idx] = sc.nextInt();
            P[idx] = sc.nextInt();
        }

        // 모든 상담일을 확인하면서 할 수 있는 상담을 찾고 금액을 계산
        // for (int idx = 0; idx < N; idx++)
        dfs(0, 0);

        System.out.print(answer);

        sc.close();
    }
}