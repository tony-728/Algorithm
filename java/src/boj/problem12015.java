package boj;

import java.io.*;
import java.util.*;

public class problem12015 {
    /*
     * LIS
     * 
     * 수열의 길이는 최대 100만
     * n^2 풀이는 안된다.
     * 이진탐색으로 nlogn으로 풀어야 함
     * 
     * 이진탐색의 lower bound, upper bound 개념 적용하기
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        int[] arrOfNumber = new int[size];
        int[] LIS = new int[size + 1];

        for (int idx = 0; idx < size; idx++) {
            arrOfNumber[idx] = Integer.parseInt(st.nextToken());
        }

        int LISIdx = 1;

        LIS[LISIdx] = arrOfNumber[0];

        for (int idx = 1; idx < size; idx++) {
            if (LIS[LISIdx] < arrOfNumber[idx]) {
                LIS[++LISIdx] = arrOfNumber[idx];

            } else if (LIS[LISIdx] > arrOfNumber[idx]) {

                int left = 1;
                int right = LISIdx;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (LIS[mid] < arrOfNumber[idx]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                LIS[left] = arrOfNumber[idx];
            }
        }

        // System.out.println(Arrays.toString(LIS));
        System.out.println(LISIdx);

    }
}
