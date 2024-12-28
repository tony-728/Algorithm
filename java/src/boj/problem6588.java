package boj;

import java.io.*;
import java.util.*;

public class problem6588 {

    /*
     * 4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다.
     * 
     * 10_000_000 이하의 모든 짝수에 대해서 
     * 검증하는 프로그램
     * 
     * 마지막 입력이 0이면 종료한다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int inputData = -1;

    static Deque<Integer> primeNumber = new ArrayDeque<>();
    static boolean[] visited = new boolean[1_000_001];

    static void getPrime() {

        boolean flag = false;

        for (int number = 3; number <= 1_000_000; number++) {
            flag = false;
            for (int idx = 2; idx <= Math.sqrt(number); idx++) {

                // 나눠지는 수가 있으면 소수가 아님
                if (number % idx == 0) {
                    flag = true;
                    break;
                }
            }

            if (flag == false) {
                primeNumber.addLast(number);
                visited[number] = true;
            }
        }
    }

    static void solution(int value) {

        for (Integer prime : primeNumber) {
        // for (int prime = 3; prime <= 1_000_000; prime++) {
            if (visited[prime] == false) {
                continue;
            }

            int temp = value - prime;

            if (temp <= 0) {
                break;
            }

            // visited[temp] 소수이면서 홀수임, 2보다 큰 짝수는 소수가 될 수 없다.
            if (visited[temp]) {
                sb.append(value).append(" = ").append(prime).append(" + ").append(temp)
                        .append("\n");
                return;
            }
        }

        sb.append("Goldbach's conjecture is wrong").append("\n");
    }

    public static void main(String[] args) throws IOException {

        getPrime();

        while (true) {
            inputData = Integer.parseInt(br.readLine().trim());

            if (inputData == 0) {
                break;
            }
            solution(inputData);
        }

        System.out.println(sb);

    }
}
