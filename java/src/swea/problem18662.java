package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem18662 {
    /*
     * 등차수열 만들기
     * 
     * 세개의 실수 x, y, z가 등차수열을 이루면
     * y-x = z-y이다
     * 
     * 3개의 실수가 주어질 때
     * 세 정수 중 하나에 x만큼 더하거나 뺄 수 있다.
     * 이러한 작업을 한번할 때 3개의 정수가 등차수열을 이루도록 한다.
     * 가능한 작은 x를 구하라
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;

    static int a, b, c;

    static double answer;

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    public static void calAP() {
        double firstCase = Math.abs(c - (b * 2.0) + a);
        double secondCase = Math.abs(((2 * b) - a - c) / 2.0);
        double thridCase = Math.abs(c - (b * 2.0) + a);

        answer = Math.min(Math.min(firstCase, secondCase), thridCase);
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            calAP();

            sb.append(String.format("#%d %.1f\n", tc, answer));
        }

        System.out.println(sb);
    }
}