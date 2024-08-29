package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2851 {
    /*
     * 10개의 버섯
     * 점수의 합을 100에 가깝게 만들려고 한다.
     * 100에 가까운 수가 2개라면 더 큰 값을 선택한다.
     * 
     * 선택을 그만두면 이후에 버섯은 선택할 수 없다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int TEST_CASE = 10;
    static final int GOAL = 100;

    static int value;
    static int answer;

    static int[] sumList = new int[TEST_CASE];

    public static void main(String[] args) throws IOException {
        answer = Integer.MAX_VALUE;

        // 누적합 구하기
        for (int idx = 0; idx < TEST_CASE; idx++) {
            value = Integer.parseInt(br.readLine().trim());

            if (idx == 0) {
                sumList[idx] = value;
            } else {
                sumList[idx] = value + sumList[idx - 1];
            }
        }

        // 누적합 중 100에 가장 가까운 값 찾기
        for (Integer val : sumList) {
            if (Math.abs(val - GOAL) <= Math.abs(answer - GOAL)) {
                answer = val;
            }
        }

        System.out.println(answer);
    }
}
