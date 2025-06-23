package boj;

import java.io.*;
import java.util.*;

public class problem13241 {

    /*
     * 최소공배수 구하기
     * 
     * 최소공배수 = (a * b) / 최대공약수
     * 
     * 최대공약수 구하기
     * a % b = b`
     * b % b` = b가 0이 될 때까지 반복
     * 0이 될 때 b`이 최대공약수
     */

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long answer = (a * b) / gcd(a, b);

        System.out.println(answer);

    }
}
