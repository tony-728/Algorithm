package boj;

import java.io.*;
import java.util.*;

public class problem2473 {
    /*
     * 산성 용액(1부터 10억), 알칼리성 용액(-10억부터 -1)이 있다.
     * 
     * 세가지 용액을 혼합한 용액의 특성값은 세 용액 특성값의 합으로 정의한다.
     * - 세가지 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
     * 
     * 매개변수 탐색
     * 모든 경우를 모두 찾아야 한다.
     * 특성값을 더하는 과정에서 오버플로우가 발생할 수 있으므로 더하는 과정에서 신경써야한다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int numOfLiquid;
    static int[] arrOfLiquid;

    static int[] answer = new int[3];

    static void inputData() throws IOException {
        numOfLiquid = Integer.parseInt(br.readLine().trim());

        arrOfLiquid = new int[numOfLiquid];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfLiquid; idx++) {
            arrOfLiquid[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrOfLiquid);

    }

    static void solution() {
        long min = Long.MAX_VALUE;

        // 세용액 중 하나를 고정한 상태에서 최소값을 찾기 위한 left, right 탐색
        for (int idx = 0; idx < numOfLiquid - 2; idx++) {

            int left = idx + 1;
            int right = numOfLiquid - 1;

            while (left < right) {
                // 더하는 과정에서 오버플로가 발생하지 않도록 0L을 먼저 더함
                long sumOfLiquid = 0L + arrOfLiquid[left] + arrOfLiquid[right] + arrOfLiquid[idx];

                if (min > Math.abs(sumOfLiquid)) {
                    min = Math.abs(sumOfLiquid);

                    // System.out.println("left " + arrOfLiquid[left] + " right " + arrOfLiquid[right]
                    //         + " idx " + arrOfLiquid[idx] + " sum " + sumOfLiquid);

                    answer[0] = arrOfLiquid[left];
                    answer[1] = arrOfLiquid[idx];
                    answer[2] = arrOfLiquid[right];
                }

                // 최소값이 음수
                if (sumOfLiquid > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        Arrays.sort(answer);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        for (Integer liquid : answer) {
            System.out.print(liquid + " ");
        }
    }
}
