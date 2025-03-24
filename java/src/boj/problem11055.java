package boj;

import java.io.*;
import java.util.*;

public class problem11055 {
    /*
     * 최장증가부분수열
     * 
     * 수열의 최대 길이 1000
     * n^2도 가능하다
     * 
     * 시간을 줄이기 위해서 이진탐색을 사용하면 nlogn으로도 풀이가 가능하다
     */

    static int sequenceSize;
    static int[] arrOfNumber;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sequenceSize = Integer.parseInt(br.readLine().trim());
        arrOfNumber = new int[sequenceSize];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < sequenceSize; idx++) {
            arrOfNumber[idx] = Integer.parseInt(st.nextToken());
        }
    }

    static void solution() {

        // 최장 증가 부분 수열의 합이 저장
        // 인덱스까지의 최장 증가 부분 수열의 최대 합이 저장된다.
        int[] LIS = new int[sequenceSize];

        int answer = 0;

        // 현재 수를 고정한 다음
        for (int idx = 0; idx < sequenceSize; idx++) {

            // 현재 수를 선택한다.
            LIS[idx] = arrOfNumber[idx];

            // 처음부터 현재 수 이전까지 수 중 크기가 작고 부분수열의 길이가 작다면 갱신
            // idx 숫자를 뒤에 추가했을 때 증가부분수열이 가능하고 그 길이가 길다
            for (int LISIdx = 0; LISIdx < idx; LISIdx++) {
                if (arrOfNumber[LISIdx] < arrOfNumber[idx]) {
                    LIS[idx] = Math.max(LIS[idx], LIS[LISIdx] + arrOfNumber[idx]);
                }
            }

            answer = Math.max(answer, LIS[idx]);
        }

        System.out.println(answer);

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();
    }
}
