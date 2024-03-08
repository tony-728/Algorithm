package boj;

import java.util.Scanner;

class problem14888 {
    /*
     * N개의 수열, N-1개의 연산자
     * 연산자는 +, -, *, / 으로만 이뤄져있다.
     * 
     * 주어진 수열의 순서는 고정
     * 연산자 우선 순위를 무시하고 앞에서 진행한다.
     * 
     * 1. 입력받기
     * 1-1 N개의 수
     * 1-2 N개의 수열
     * 1-3 +, -, *, / 의 개수를 입력(N-1)개
     * 
     * 주어진 수열의 순서는 고정 -> 수열 순서대로 진행하면 된다.
     * 연산자 우선순위를 생각하지 않는다
     * 입력받은 수열 순서대로 연산자의 모든 경우의 수를 찾는다. -> DFS
     * 연산자는 4개이므로 4개의 가지가 생긴다.
     * - 이때 주어진 연산자가 없을 수 있으므로 사용가능한 연산자가 있는지 확인
     * - 하나의 경우의 수를 확인한 이후에 다시 연산자를 사용할 수 있도록 백트래킹해준다.
     * 
     */

    static int N;
    static int[] numbers;
    static int[] numOfOperator = new int[4];
    static int MaxValue = Integer.MIN_VALUE;
    static int MinValue = Integer.MAX_VALUE;

    public static void dfs(int idx, int result) {
        // DFS 종료조건 수열의 개수만큼 확인했으면 종료
        // 최대값, 최소값 확인
        if (idx >= N) {
            MaxValue = Math.max(MaxValue, result);
            MinValue = Math.min(MinValue, result);
            return;
        }

        // +, -, *, / 연산자가 남았는지 확인 후 DFS, 연산자를 사용했으면 개수 차감
        // 확인했으면 다시 연산자를 사용할 수 있도록 개수 증가
        if (numOfOperator[0] > 0) {
            numOfOperator[0]--;
            dfs(idx + 1, result + numbers[idx]);
            numOfOperator[0]++;
        }
        if (numOfOperator[1] > 0) {
            numOfOperator[1]--;
            dfs(idx + 1, result - numbers[idx]);
            numOfOperator[1]++;
        }
        if (numOfOperator[2] > 0) {
            numOfOperator[2]--;
            dfs(idx + 1, result * numbers[idx]);
            numOfOperator[2]++;
        }
        if (numOfOperator[3] > 0) {
            numOfOperator[3]--;
            dfs(idx + 1, result / numbers[idx]);
            numOfOperator[3]++;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        numbers = new int[N];

        for (int idx = 0; idx < N; idx++) {
            numbers[idx] = sc.nextInt();
        }

        for (int idx = 0; idx < 4; idx++) {
            numOfOperator[idx] = sc.nextInt();
        }

        // dfs 인덱스(다음 인덱스), 수열의 현재 수(시작은 첫번째 수)
        dfs(1, numbers[0]);

        System.out.println(MaxValue);
        System.out.println(MinValue);
        sc.close();
    }
}