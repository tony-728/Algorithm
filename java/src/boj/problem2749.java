package boj;

import java.io.*;

public class problem2749 {

    /**
     * 
     * 피보나치 수
     * 
     * dp로 하려고 했는데 N이 매우 큼 10^18승...
     * 
     * O(n) 풀이도 안될 가능성이 있다.
     * 
     * 문제에서 요구한 풀이는 피사노 주기를 이용하는 것
     * 
     * 피사노 주기
     * - 피보나치 수를 K로 나눈 나머지는 항상 주기를 가지게 된다.
     * - 예를 들어 피보나치 수를 3으로 나누었을 때 주기는 8이다.
     * 
     * 즉, 주기의 길이가 P일 때 피보나치 수를 M으로 나눈 나머지는 N%P번째 피보나치 수를 M으로 나눈 나머지와 같다.
     * M = 10^k 일 때 K > 2 라면 주기는 항상 15 x 10 ^(k-1)이다.
     * 
     * 문제에서 1,000,000으로 나눈 나머지를 구하는 것이므로 
     * 주기는 15x10^(5) = 1,500,000이다.
     * 
     */

    static int getPisoanPeriod(int m) {
        long a = 0;
        long b = 1;

        // 이론적으로 6 * m까지만 반복해도 된다.
        for (long idx = 0; idx < 6 * m; idx++) {
            long c = (a + b) % m;
            a = b;
            b = c;

            if (a == 0 && b == 1) {
                return (int) idx + 1;
            }
        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine().trim());

        // int pisano = 15 * 100_000;
        int pisano = getPisoanPeriod(1_000_000);

        N %= pisano;

        long[] fibo = new long[(int) N + 1];

        fibo[0] = 0;
        fibo[1] = 1;

        for (int idx = 2; idx < N + 1; idx++) {
            fibo[idx] = (fibo[idx - 1] + fibo[idx - 2]) % 1_000_000;
        }

        System.out.println(fibo[(int) N]);
    }
}
