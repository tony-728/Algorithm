package boj;

import java.util.*;
import java.io.*;

public class problem1269 {
    /*
     * 두 집합에 교집합 원소를 뺀 나머지 원소의 개수의 합
     */
    static int numOfA, numOfB;
    static Set<Integer> a = new HashSet<>();
    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfA = Integer.parseInt(st.nextToken());
        numOfB = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfA; idx++) {
            int value = Integer.parseInt(st.nextToken());
            a.add(value);
        }

        int overlap = 0;

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfB; idx++) {
            int value = Integer.parseInt(st.nextToken());
            if (a.contains(value)) {
                overlap++;
            }
        }

        answer = numOfA - overlap;
        answer += (numOfB - overlap);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        System.out.println(answer);

    }
}
