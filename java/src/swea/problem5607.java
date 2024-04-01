package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem5607 {

    /*
     * 자연수 N과 R이 주어질 때
     * N combination R의 값을 1234567891로 나눈 나머지를 출력
     * 
     * 
     * 테스트케이스가 주어진다.
     * 정수 N, R이 주어진다.
     * - 1 <= N <= 1000000, 0 <= R <= N
     * 
     * 
     * N이 굉장히 크기 때문에 재귀함수로 조합을 구할 수 없다.
     * 또한 나머지 값으로 계산해야하기 때문에 페르마의 소정리를 사용해야한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MOD_NUMBER = 1234567891;
    static final int MAX_NUMBER = 1_000_000;

    static int testCase;
    static int N;
    static int R;

    static long answer;

    static long[] fatorialList = new long[MAX_NUMBER + 1];

    public static void fatorial() {
        fatorialList[0] = 1;
        for (int idx = 1; idx < MAX_NUMBER + 1; idx++) {
            fatorialList[idx] = fatorialList[idx - 1] * idx % MOD_NUMBER;
        }
    }

    public static long pow(long number, int exp) {
        if (exp == 1) {
            return number;
        }

        long result = pow(number, exp / 2) % MOD_NUMBER;

        // 지수가 짝수일 때
        if (exp % 2 == 0) {
            return result * result % MOD_NUMBER;
            // 지수가 홀수일 때
        } else {
            return (result * result) % MOD_NUMBER * number % MOD_NUMBER;
        }
    }

    public static void combination() {
        // 페르마 소정리를 이용하여 조합 값에 나머지를 구한다.
        // 조합의 식은 N! / ((N-R)! * R!) 이다.
        // 조합의 식에 mod 연산을 적용할 수 없다. 왜냐하면 나눗셈에서는 mod연산이 적용되지 않는다.
        // 따라서 분모를 분자로 올려 곱셈에 형태가 되어야 mod 연산을 적용할 수 있다.
        // N! * ((N-R)! * R!)^-1 의 곱셈으로 바꿀 수 있다.
        // ((N-R)! * R!) = A 라고 생각하고 페르마의 소정리를 적용하면 다음과 같다.
        // A^(p-1) = 1 (mod p)
        // A^(p-1) * (A^-1) = 1 (mod p) * (A^-1)
        // A^(p-2) = A^-1 (mod p)
        // A^-1 (mod p) 가 필요하기 때문에 A^(p-2)를 구하면 된다.
        long numerator = fatorialList[N] % MOD_NUMBER; // 분자의 값
        long denominator = (fatorialList[R] * fatorialList[N - R]) % MOD_NUMBER; // 분모의 값
        long inverseDenominator = pow(denominator, MOD_NUMBER - 2);

        answer = numerator * inverseDenominator % MOD_NUMBER;
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        // 조합식에 필요한 팩토리얼을 미리 계산
        fatorial();

        for (int tc = 1; tc < testCase + 1; tc++) {
            answer = 0;
            st = new StringTokenizer(br.readLine().trim());

            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            combination();

            sb.append(String.format("#%d %d\n", tc, answer));

        }

        System.out.println(sb);
    }
}
