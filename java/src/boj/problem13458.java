package boj;

import java.util.Scanner;

public class problem13458 {
    static int N; // 총 시험장
    static int[] A; // 시험장의 응시자
    static int totalRef, subRef; // 총감독관, 부감독관이 커버하는 응시자
    static long answer;

    public static void main(String[] args) {
        /*
         * N개의 시험장
         * i번 시험장에 응시자 Ai명
         * 총감독관 한 시험장에서 감시할 수 있는 응시자 B명
         * 부감독관 한 시험장에서 감시할 수 있는 응시자 C명
         * 시험장에 총감독관은 1명 부감독관은 여러명
         * 
         * 필요한 감독관의 수의 최소값
         * 
         * N개의 시험장 수
         * 각 시험장에 응시자 수 Ai
         * B, C
         * 
         * 1. 각 응시자 수마다 B는 무조건 있어야 한다.
         * 2. 응시자 수에서 B를 제외한 나머지 인원을 C가 모두 커버해야한다.
         * 3.
         */
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        A = new int[N];

        for (int idx = 0; idx < N; idx++) {
            A[idx] = sc.nextInt();
        }

        totalRef = sc.nextInt();
        subRef = sc.nextInt();

        // 총감독관은 시험장 수 만큼 있어야 한다.
        answer = N;

        // 부감독관은 총감독관이 커버하고 남은 응시자를 모두 커버해야한다.
        for (int idx = 0; idx < N; idx++) {
            int temp = A[idx] - totalRef; // 총감독관이 커버

            if (temp > 0) {
                answer += temp / subRef;
                if (temp % subRef > 0) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
        sc.close();
    }
}
