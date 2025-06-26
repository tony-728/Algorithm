package boj;

import java.io.*;
import java.util.*;

public class problem1644 {
    /*
     * 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다.
     * 자연수가 주어졌을 때 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하라
     * 
     * 자연수 (1 <= n <= 4_000_000)
     * 
     * 모든 소수를 구하고 주어진 값에 대해서 거꾸로 확인한다.
     * 
     * 에라토스테네스의 채
     * 큐
     */

    static final int MAX_VALUE = 4_000_000 + 1;

    static boolean[] arrOfPrime = new boolean[MAX_VALUE];

    static void setPrime() {

        arrOfPrime[1] = true;

        for (int prime = 2; prime < MAX_VALUE; prime++) {
            for (int start = prime + prime; start < MAX_VALUE; start = start + prime) {
                arrOfPrime[start] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        setPrime();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine().trim());

        Deque<Integer> q = new ArrayDeque<>();

        int answer = 0;
        int sum = 0;

        for (int idx = number; idx > 0; idx--) {
            // 소수가 아님
            if (arrOfPrime[idx]) {
                continue;
            }

            sum += idx;
            q.addLast(idx);

            if (sum > number) {
                sum -= q.pollFirst();
            } else if (sum == number) {
                answer++;
                sum -= q.pollFirst();
            }
        }

        System.out.println(answer);
    }
}
