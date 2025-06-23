package boj;

import java.io.*;
import java.util.*;

public class problem24313 {
    /*
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        int a1 = Integer.parseInt(st.nextToken());
        int a0 = Integer.parseInt(st.nextToken());

        int c = Integer.parseInt(br.readLine().trim());
        int n0 = Integer.parseInt(br.readLine().trim());

        int f = a1 * n0 + a0;
        int g = c * n0;

        // f <= g는 문제의 공식 유도
        // a1 <= c는 두 일차함수의 기울기를 비교할 때 함수 g는 함수 f보다 기울기가 크거나 같아야지 식이 항상 참이된다.
        if (f <= g && a1 <= c) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }
}
