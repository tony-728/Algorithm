package boj;

import java.io.*;
import java.util.*;

public class problem2230 {
    /*
     * N개의 정수로 이루어진 수열이 있다.
     * 
     * 이 수열에서 두 수를 골랐을 때(같은 수일 수도 있다.) 그 차이가 M이상이면서 제일 작은 경우를 구하는 프로그램
     * 
     * N은 최대 10만
     * 수열 요소는 -10억 ~ 10억
     * M은 0부터 20억
     * 
     * 투포인터
     * 이진탐색
     * 
     */

    static int sizeOfSeq;
    static int standard;
    static int[] arrOfNumber;

    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());

        sizeOfSeq = Integer.parseInt(st.nextToken());
        standard = Integer.parseInt(st.nextToken());

        arrOfNumber = new int[sizeOfSeq];

        for (int idx = 0; idx < sizeOfSeq; idx++) {
            int number = Integer.parseInt(br.readLine().trim());

            arrOfNumber[idx] = number;
        }

        Arrays.sort(arrOfNumber);
    }

    static void solution() {

        out: for (int idx = 0; idx < sizeOfSeq - 1; idx++) {

            int number = arrOfNumber[idx];

            int left = idx + 1;
            int right = sizeOfSeq - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int midValue = arrOfNumber[mid];

                // 선택한 두 수의 차이
                int diff = Math.abs(number - midValue);

                // System.out.println("number "+ number + " mid " + midValue + " diff " + diff);

                if (diff < standard) {
                    left = mid + 1;
                } else if (diff > standard) {
                    right = mid - 1;
                    answer = Math.min(answer, diff);
                } else if(diff == standard){
                    answer = diff;
                    break out;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
