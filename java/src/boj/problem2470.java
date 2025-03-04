package boj;

import java.io.*;
import java.util.*;

public class problem2470 {
    /*
     * 산성 용액의 특성값 1부터 10억까지 양의 정수
     * 알카리성 용액의 특성값은 -1부터 -10억까지 음의 정수
     * 
     * 
     * 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용한 각 용액의 특성값의 합으로 정의한다.
     * 
     * 같은 양의 두 용액을 혼합하여 특성값이 0에 가까운 용액을 만들려고 한다.
     * - 같은 용액으로도 가능하다(산성, 산성), (알카리성, 알카리성)
     * 
     * 서로 다른 용액을 혼합하여 특성값이 0에 가까운 용액을 만들어내는 두 용액을 찾아라
     * 
     * 용액의 전체 개수는 최대 10만
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfLiquid;
    static Integer[] arrOfLiquid;
    static int[] answer = new int[2];

    static void inputData() throws IOException {
        numOfLiquid = Integer.parseInt(br.readLine().trim());
        arrOfLiquid = new Integer[numOfLiquid];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfLiquid; idx++) {
            arrOfLiquid[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrOfLiquid, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1) - Math.abs(o2);
            }
        });
    }

    static void solution() {

        int miniumDiff = Integer.MAX_VALUE;

        for (int idx = 0; idx < numOfLiquid - 1; idx++) {
            int target = arrOfLiquid[idx];
            int next = arrOfLiquid[idx + 1];

            int diff = Math.abs(target + next);

            if (diff < miniumDiff) {
                miniumDiff = diff;
                answer[0] = target;
                answer[1] = next;
            }


        }

    }


    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        Arrays.sort(answer);

        for (Integer value : answer) {
            System.out.print(value + " ");
        }
    }
}
