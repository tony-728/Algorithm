package boj;

import java.io.*;
import java.util.*;

public class problem14425 {
    /*
     * N개의 문자열 집합
     * 
     * M개 문자열 중 집합에 포함되어 있는 것이 몇개인지 구하라
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int numOfString = Integer.parseInt(st.nextToken());
        int testCase = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int idx = 0; idx < numOfString; idx++) {
            String string = br.readLine().trim();

            map.put(string, 1);
        }

        int answer = 0;
        for (int idx = 0; idx < testCase; idx++) {
            String string = br.readLine().trim();

            if (map.containsKey(string)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
