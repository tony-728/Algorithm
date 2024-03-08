package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1284 {

    static int P, Q, R, S, W;

    static int answer = 0;

    static int A, B;

    /*
     * A사 1리터당 P원
     * B사 기본요금 Q, 월간 사용량이 R리터 초과인 경우 1리터당 S원
     * 
     * 1달간 W리터를 사용할 때
     * 
     * A, B 중 적은 요금을 구하라
     * 
     * 1. A사는 W * P 원이 한달 요금
     * 2. B사는 W <= R 인경우 Q원이 한달 요금, W > R인경우 Q+ (W-R)*S
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase <= T; testCase++) {
            // String[] input = br.readLine().trim().split(" ");
            StringTokenizer input = new StringTokenizer(br.readLine().trim(), " ");

            // P = Integer.parseInt(input[0]);
            // Q = Integer.parseInt(input[1]);
            // R = Integer.parseInt(input[2]);
            // S = Integer.parseInt(input[3]);
            // W = Integer.parseInt(input[4]);

            P = Integer.parseInt(input.nextToken());
            Q = Integer.parseInt(input.nextToken());
            R = Integer.parseInt(input.nextToken());
            S = Integer.parseInt(input.nextToken());
            W = Integer.parseInt(input.nextToken());

            A = P * W;
            B = Q;

            if (W > R) {
                B += (W - R) * S;
            }

            answer = Math.min(A, B);

            System.out.println("#" + testCase + " " + answer);

        }
    }
}
